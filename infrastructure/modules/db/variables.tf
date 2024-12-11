variable "db_name" {
  description = "Name of the database"
  type        = string
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
  description = "Tags to be applied to the resources"
  type        = map(string)
}

variable "sg_id" {
  description = "ID of the security group"
  type        = string
}

variable "database_subnet_group" {
  description = "Name of the database subnet group"
  type        = string
}
