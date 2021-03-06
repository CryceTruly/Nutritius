from django.urls import reverse
from rest_framework.urlpatterns import format_suffix_patterns
from django.urls import path, include
from .views import NutrientsList, NutrientsDetail, create
from .views import FoodCreate, FoodDetail, FoodListView, FoodDelete,FoodToAvoidCreate, FoodsToAvoidSiteView,delete_food, FoodToAvoidDelete, FoodsToAvoidView, FoodToAvoidView


urlpatterns = [
          path('', FoodListView.as_view(), name='home'),
          path('fcreate', FoodCreate.as_view(), name='fcreate'),
          path('ftacreate', FoodToAvoidCreate.as_view(), name='ftacreate'),
          path('ftafoods', FoodsToAvoidSiteView.as_view(), name='ftafoods'),
          path("nutrients/<int:pk>/", FoodDetail.as_view(), name="detail"),
          path("nutrients/<int:pk>/delete/", FoodDelete.as_view(), name="del-food"),
          path("ftafoods/<int:pk>/delete/",delete_food, name="del-foodtoavoid"),


          path('api/nutrients/', NutrientsList.as_view()),
          path("api/nutrients/<int:pk>/",NutrientsDetail.as_view(), name="nutrient_detail"),

          path("api/foodstoavoid/", FoodsToAvoidView.as_view(), name='foodstoavoid'),
          path("api/foodstoavoid/<int:pk>/",FoodToAvoidView.as_view(), name='foodtoavoid'),


]
