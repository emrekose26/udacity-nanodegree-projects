# Baking App

This is Project 3 of Udacity's Android Developer Nanodegree.


## Screenshots

<img src="https://raw.githubusercontent.com/emrekose26/udacity-nanodegree-projects/master/BakingApp/art/phone1.png" width="350"> <img src="https://raw.githubusercontent.com/emrekose26/udacity-nanodegree-projects/master/BakingApp/art/phone2.png" width="350"> 
<img src="https://raw.githubusercontent.com/emrekose26/udacity-nanodegree-projects/master/BakingApp/art/phone3.png" width="350">

<img src="https://raw.githubusercontent.com/emrekose26/udacity-nanodegree-projects/master/BakingApp/art/tablet1.png" width="700"> <img src="https://raw.githubusercontent.com/emrekose26/udacity-nanodegree-projects/master/BakingApp/art/tablet2.png" width="700">


## Project Overview
You will productionize an app, taking it from a functional state to a production-ready state. This will involve finding and handling error cases, adding accessibility features, allowing for localization, adding a widget, and adding a library.

## Why this Project?
As a working Android developer, you often have to create and implement apps where you are responsible for designing and planning the steps you need to take to create a production-ready app. Unlike Popular Movies where we gave you an implementation guide, it will be up to you to figure things out for the Baking App.

## What Will I Learn?
In this project you will:

* Use MediaPlayer/Exoplayer to display videos.
* Handle error cases in Android.
* Add a widget to your app experience.
* Leverage a third-party library in your app.
* Use Fragments to create a responsive design that works on phones and tablets.

## App Description
Your task is to create a Android Baking App that will allow Udacity’s resident baker-in-chief, Miriam, to share her recipes with the world. You will create an app that will allow a user to select a recipe and see video-guided steps for how to complete it.



## Rubric

### Common Project Requirements

- [x] App is written solely in the Java Programming Language 
- [x] App utilizes stable release versions of all libraries, Gradle, and Android Studio.

### General App Usage

- [x] App should display recipes from provided network resource.
- [x] App should allow navigation between individual recipes and recipe steps.
- [x] App uses RecyclerView and can handle recipe steps that include videos or images.
- [x] App conforms to common standards found in the [Android Nanodegree General Project Guidelines](http://udacity.github.io/android-nanodegree-guidelines/core.html)


### Components and Libraries

- [x] Application uses Master Detail Flow to display recipe steps and navigation between them.
- [x] Application uses Exoplayer to display videos.
- [x] Application properly initializes and releases video assets when appropriate.
- [x] Application should properly retrieve media assets from the provided network links. It should properly handle network requests.
- [x] Application makes use of Espresso to test aspects of the UI.
- [x] Application sensibly utilizes a third-party library to enhance the app's features. That could be helper library to interface with ContentProviders if you choose to store the recipes, a UI binding library to avoid writing findViewById a bunch of times, or something similar.

### Homescreen Widget

- [x] Application has a companion homescreen widget.
- [x] Widget displays ingredient list for desired recipe.


## Technologies used

* MVP Pattern
* [ButterKnife](https://github.com/JakeWharton/butterknife) 
* [Dagger2](https://github.com/google/dagger) 
* [RxJava](https://github.com/ReactiveX/RxJava) & [RxAndroid](https://github.com/ReactiveX/RxAndroid) 
* [Retrofit](https://github.com/square/retrofit) 
* [OkHttp](https://github.com/square/okhttp) 
* [GSON](https://github.com/google/gson)
* [ExoPlayer](https://github.com/google/ExoPlayer)
* [Schematic](https://github.com/SimonVT/schematic) 
* [MaterialStepperView](https://github.com/fython/MaterialStepperView)
* [Stetho](https://github.com/facebook/stetho) 
* [Glide](https://github.com/bumptech/glide) 
* [Timber](https://github.com/JakeWharton/timber) 


## License
    Copyright 2018 Emre Köse

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.