terraform {
  backend "s3"  {
    bucket = "18burguerstfstate"
    key = "orderService/terraform.tfstate"
    region = "us-east-1"
  }
}