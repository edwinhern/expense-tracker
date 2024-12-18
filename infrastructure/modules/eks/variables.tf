variable "vpc_id" {
  description = "ID of the VPC"
  type        = string
}

variable "private_subnet_ids" {
  description = "List of Ids for private subnets"
  type        = list(string)
}

variable "environment" {
  description = "Environment (dev/prod)"
  type        = string
}

variable "cluster_name" {
  description = "Name of the EKS cluster"
  type        = string
}

variable "tags" {
  description = "Tags"
  type        = map(string)
}
