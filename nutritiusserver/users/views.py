from django.shortcuts import render

# Create your views here.
def index(request):
    return render(request,'users/profile.html')
def login(request):
    return render(request,'users/login.html')