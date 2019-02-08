from django.contrib import admin
from django.urls import path


admin.site.site_header = "Nutritius App Adminstration"
admin.site.site_title = "Nutritius App Portal"
admin.site.index_title = "Welcome to Nutritius App"

urlpatterns = [
    path('admin/', admin.site.urls),
]
