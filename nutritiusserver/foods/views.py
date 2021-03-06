from django.shortcuts import render
from django.contrib.auth.mixins import LoginRequiredMixin
from django.contrib.auth.decorators import login_required
from rest_framework import status, viewsets
from django.shortcuts import get_object_or_404
from .models import RecommendedFood, FoodsToAvoid
from rest_framework.views import APIView
from .serializers import NutrientsSerializer, FoodsToAvoidSerializer
from rest_framework.response import Response
from django.views.generic.edit import CreateView, UpdateView, DeleteView
from django.views.generic import DetailView, ListView
# Create your views here.
import json
from django.shortcuts import redirect

def delete_food(request, pk):
    food = FoodsToAvoid.objects.get(pk=pk)
    food.delete()
    return redirect('ftafoods')
class FoodListView(LoginRequiredMixin, ListView):
    model = RecommendedFood
    template_name = 'foods/home.html'
    context_object_name = 'data'
    ordering = ['name']


class FoodDetail(LoginRequiredMixin, DetailView):
    model = RecommendedFood


class FoodDelete(LoginRequiredMixin, DeleteView):
    model = RecommendedFood
    success_url = '/'


@login_required()
def create(request):
    return render(request, 'foods/new.html')


class NutrientsList(APIView):
    def get(self, request):
        nutrients = RecommendedFood.objects.all()
        serializer = NutrientsSerializer(nutrients, many=True)
        return Response(serializer.data)

    def post(self, request):
        data = {
            'name': request.data.get('name'),
            'description': request.data.get('description'),
            'nutrients': request.data.get('nutrients')
        }
        serializer = NutrientsSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        else:
            return Response(serializer.errors,
                            status=status.HTTP_400_BAD_REQUEST)


class NutrientsDetail(APIView):
    def get(self, request, pk):
        nutrients = get_object_or_404(RecommendedFood, pk=pk)
        data = NutrientsSerializer(nutrients).data
        return Response(data)


class FoodCreate(CreateView):
    model = RecommendedFood
    fields = '__all__'


class FoodToAvoidCreate(CreateView):
    model = FoodsToAvoid
    fields = '__all__'
    success_url = '/ftafoods'


class FoodsToAvoidView(APIView):
    class meta:
        fields = '__all__'
        model = FoodsToAvoid

    def get(self, request):
        foods = FoodsToAvoid.objects.all()
        serializer = FoodsToAvoidSerializer(foods, many=True)
        return Response(serializer.data)

    def post(self, request):
        data = {
            'name': request.data.get('name'),
            'reason': request.data.get('reason')
        }
        serializer = FoodsToAvoidSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        else:
            return Response(serializer.errors,
                            status=status.HTTP_400_BAD_REQUEST)


class FoodToAvoidView(APIView):

    def get(self, request, pk):
        food = get_object_or_404(FoodsToAvoid, pk=pk)
        data = FoodsToAvoidSerializer(food).data
        return Response(data)


class FoodToAvoidDelete(LoginRequiredMixin, DeleteView):
    model = FoodsToAvoid
    success_url = '/'

class FoodsToAvoidSiteView(LoginRequiredMixin, ListView):
    model = FoodsToAvoid
    template_name = 'foods/foodstoavoid.html'
    context_object_name = 'data'
    ordering = ['name']
