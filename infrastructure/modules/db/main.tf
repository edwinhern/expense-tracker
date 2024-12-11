module "db" {
  source  = "terraform-aws-modules/rds/aws"
  version = "6.10.0"

  identifier = "${var.db_name}-${var.environment}-db"

  engine               = "postgres"
  engine_version       = "15"
  family               = "postgres15"
  major_engine_version = "15"
  instance_class       = "db.t3.micro"

  allocated_storage     = 20
  max_allocated_storage = 100

  db_name  = "expense_tracker"
  username = "postgres"
  port     = 5432

  multi_az               = false
  db_subnet_group_name   = var.database_subnet_group
  create_db_subnet_group = false
  vpc_security_group_ids = [var.sg_id]

  # Performance monitoring
  performance_insights_enabled          = true
  performance_insights_retention_period = 7
  create_monitoring_role                = true
  monitoring_interval                   = 60

  maintenance_window = "Mon:00:00-Mon:03:00"
  backup_window      = "03:00-06:00"

  # Cloudwatch logs
  enabled_cloudwatch_logs_exports = ["postgresql", "upgrade"]
  create_cloudwatch_log_group     = true

  backup_retention_period = 7

  parameters = [
    { name = "autovacuum", value = "1" },
    { name = "client_encoding", value = "utf8" }
  ]

  tags = var.tags
}
