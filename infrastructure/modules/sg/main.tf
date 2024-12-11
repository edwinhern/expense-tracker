module "security_group" {
  source  = "terraform-aws-modules/security-group/aws"
  version = "~> 5.0"

  name        = "${var.sg_name}-${var.environment}-sg"
  description = "Security group for ${var.sg_name} ${var.environment}"
  vpc_id      = var.vpc_id

  # Ingress rules
  ingress_with_cidr_blocks = [
    {
      from_port   = 5432
      to_port     = 5432
      protocol    = "tcp"
      description = "PostgreSQL access from within VPC"
      cidr_blocks = var.vpc_cidr
    }
  ]

  ingress_with_source_security_group_id = [
    {
      from_port                = 5432
      to_port                  = 5432
      protocol                 = "tcp"
      description              = "PostgreSQL access from EKS cluster"
      source_security_group_id = var.eks_sg_id
    }
  ]

  egress_rules = ["all-all"] # Allow all egress traffic

  tags = var.tags
}
