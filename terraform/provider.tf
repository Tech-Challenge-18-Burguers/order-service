provider "aws" {
  
  region = var.regionDefault
}

terraform {  
  required_providers {  
    aws = {  
      source  = "hashicorp/aws"        
    }  
  }  

  required_version = ">= 1.0"  
}


