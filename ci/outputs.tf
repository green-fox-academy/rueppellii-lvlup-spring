output "production_ip" {
  value = "${aws_instance.lvlup_prod.public_ip}"
}

output "staging_ip" {
  value = "${aws_instance.lvlup_stage.public_ip}"
}
