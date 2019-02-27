variable "key_name" {}

variable "key_path" {}

variable "aws_access_key" {}

variable "aws_secret_key" {}

variable "region" {
  default = "eu-north-1"
}

variable "aws_ami" {
  default = "ami-86fe70f8"
}

variable "aws_instance_type" {
  default = "t3.micro"
}

variable "aws_security_group_name" {
  default = "terraform-lvlup-sg"
}

variable "prod_db_url" {}

variable "prod_db_user" {}

variable "prod_db_password" {}

variable "client_id" {}

variable "client_secret" {}

variable "secret" {}
