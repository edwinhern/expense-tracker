variable "sg_name" {
  description = "Name of the security group"
  type        = string
}

variable "environment" {
  description = "Environment (dev/prod)"
  type        = string
}

variable "vpc_id" {
  description = "ID of the VPC"
  type        = string
}

variable "vpc_cidr" {
  description = "CIDR block for VPC"
  type        = string
}

variable "tags" {
  description = "Tags"
  type        = map(string)
}

variable "eks_sg_id" {
  description = "ID of the EKS security group"
  type        = string
}
