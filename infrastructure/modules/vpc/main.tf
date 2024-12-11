module "vpc" {
  source  = "terraform-aws-modules/vpc/aws"
  version = "5.16.0"

  name = "${var.vpc_name}-${var.environment}-vpc"
  cidr = var.vpc_cidr

  azs              = var.azs
  public_subnets   = [for k, v in var.azs : cidrsubnet(var.vpc_cidr, 8, k)]
  private_subnets  = [for k, v in var.azs : cidrsubnet(var.vpc_cidr, 8, k + 3)]
  database_subnets = [for k, v in var.azs : cidrsubnet(var.vpc_cidr, 8, k + 6)]

  create_database_subnet_group = true

  # Tags required for EKS
  private_subnet_tags = {
    "kubernetes.io/cluster/${var.cluster_name}" = "shared"
    "kubernetes.io/role/internal-elb"           = "1"
  }

  public_subnet_tags = {
    "kubernetes.io/cluster/${var.cluster_name}" = "shared"
    "kubernetes.io/role/elb"                    = "1"
  }

  tags = var.tags
}
