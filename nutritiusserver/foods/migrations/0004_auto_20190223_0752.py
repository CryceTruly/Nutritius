# Generated by Django 2.1.7 on 2019-02-23 07:52

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('foods', '0003_foodtoavoid'),
    ]

    operations = [
        migrations.RenameModel(
            old_name='FoodToAvoid',
            new_name='FoodsToAvoid',
        ),
    ]
