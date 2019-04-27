from django.db import models
from django.db.models.signals import post_save
from .signals import food_post_save_receiver


class Nutrients(models.Model):


    CHOICES=(
        ('ONE','ONE'),
        ('TWO','TWO'),
        ('THREE','THREE')
    )



    name = models.CharField(max_length=255, null=True)
    description = models.CharField(max_length=500)
    nutrients = models.CharField(max_length=500)
    agegroup = models.CharField(max_length=255,choices=CHOICES,default='ONE')
    recommended = models.BooleanField()

    def get_absolute_url(self):
        return u'/'

    def __str__(self):
        return self.name+" "+self.nutrients


class FoodsToAvoid(models.Model):
    name = models.CharField(max_length=255)
    reason = models.CharField(max_length=500)

    nutrients = models.ManyToManyField("self")

    @property
    def nutrients_list(self):
        # Watch for large querysets: it loads everything in memory
        return list(self.nutrients_list.all())

    def __str__(self):
        return self.name

    post_save.connect(food_post_save_receiver, sender=None)
