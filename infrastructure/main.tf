provider "aws" {
  region = local.region
}

data "aws_availability_zones" "available" {}

locals {
  name   = "expense-tracker"
  region = "us-east-1"

  vpc_cidr = "10.0.0.0/16"
  azs      = slice(data.aws_availability_zones.available.names, 0, 3)

  environment  = "dev"
  cluster_name = "${local.name}-cluster"
  tags = {
    Name        = local.name
    Environment = "dev"
    ManagedBy   = "terraform"
  }
}

module "vpc" {
  source = "./modules/vpc"

  vpc_name = local.name
  vpc_cidr = local.vpc_cidr

  environment  = local.environment
  cluster_name = local.cluster_name
  azs          = local.azs
  tags         = local.tags
}

module "security_group" {
  source = "./modules/sg"

  sg_name     = local.name
  environment = local.environment
  vpc_id      = module.vpc.vpc_id
  vpc_cidr    = local.vpc_cidr
  tags        = local.tags
  eks_sg_id   = module.eks.sg_id
}

module "eks" {
  source = "./modules/eks"

  vpc_id             = module.vpc.vpc_id
  private_subnet_ids = module.vpc.private_subnet_ids

  environment  = local.environment
  cluster_name = local.cluster_name
  tags         = local.tags
}

module "db" {
  source = "./modules/db"

  db_name               = local.name
  environment           = local.environment
  cluster_name          = local.cluster_name
  tags                  = local.tags
  sg_id                 = module.security_group.sg_id
  database_subnet_group = module.vpc.database_subnet_group
}

module "alb" {
  source = "./modules/alb"

  cluster_name      = local.cluster_name
  oidc_provider_arn = module.eks.oidc_provider_arn
  oidc_provider     = module.eks.oidc_provider
  vpc_id            = module.vpc.vpc_id
  tags              = local.tags
}
