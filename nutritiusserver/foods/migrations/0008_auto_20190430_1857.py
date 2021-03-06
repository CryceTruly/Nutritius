# Generated by Django 2.1.7 on 2019-04-30 18:57

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('foods', '0007_foodstoavoid_nutrients'),
    ]

    operations = [
        migrations.CreateModel(
            name='RecommendedFood',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=255, null=True)),
                ('description', models.CharField(max_length=500)),
                ('nutrients', models.CharField(max_length=500)),
                ('agegroup', models.CharField(choices=[('1', '0-12 Months'), ('2', '1-2 Years'), ('3', '2-3 Years')], default='1', max_length=255)),
            ],
        ),
        migrations.DeleteModel(
            name='Nutrients',
        ),
        migrations.RemoveField(
            model_name='foodstoavoid',
            name='nutrients',
        ),
    ]
