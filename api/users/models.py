from django.db import models
from django.db import models
# Create your models here.


class UserModel(models.Model):
    username=models.CharField(max_length=255,min_length=255)

    email=models.CharField(max_length=255,min_length=255)

    FullName=models.CharField(max_length=255,min_length=255)

    password=models.CharField(max_length=255,min_length=255)
