provider "aws" {
  access_key = "${var.aws_access_key}"
  secret_key = "${var.aws_secret_key}"
  region     = "${var.region}"
}

resource "aws_security_group" "security_group" {
  name = "${var.aws_security_group_name}"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

data "template_file" "init_staging" {
  template = "${file("${path.module}/init.tpl")}"
  vars = {
    profile = "dev"
    image = "staging"
  }
}

data "template_file" "init_prod" {
  template = "${file("${path.module}/init.tpl")}"
  vars = {
    profile = "production"
    image = "latest"
  }
}

resource "aws_instance" "lvlup_prod" {
  ami           = "${var.aws_ami}"
  instance_type = "${var.aws_instance_type}"

  security_groups = [
    "${var.aws_security_group_name}",
  ]

  connection {
    type        = "ssh"
    user        = "ec2-user"
    private_key = "${file(var.key_path)}"
  }

  key_name = "${var.key_name}"

  provisioner "remote-exec" {
    inline = [
      "sudo yum update -y",
      "sudo yum install docker -y",
      "sudo service docker start",
    ]
  }

  provisioner "remote-exec" {
  inline = [
    "cat <<FILE > /init.sh ${template_file.init_prod.rendered}} FILE"
    "chmod +x /init.sh",
    ]
  }

  tags {
    Name = "SpringLvlUp_Prod"
  }
}



resource "aws_instance" "lvlup_stage" {
  ami           = "${var.aws_ami}"
  instance_type = "${var.aws_instance_type}"

  security_groups = [
    "${var.aws_security_group_name}",
  ]

  connection {
    type        = "ssh"
    user        = "ec2-user"
    private_key = "${file(var.key_path)}"
  }

  key_name = "${var.key_name}"

  provisioner "remote-exec" {
    inline = [
      "sudo yum update -y",
      "sudo yum install docker -y",
      "sudo service docker start",
    ]
  }

  provisioner "remote-exec" {
  inline = [
    "cat <<FILE > /init.sh ${template_file.init_staging.rendered}} FILE"
    "chmod +x /init.sh",
    ]
  }

  tags {
    Name = "SpringLvlUp_Stage"
  }
}
