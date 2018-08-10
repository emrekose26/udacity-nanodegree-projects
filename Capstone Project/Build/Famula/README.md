# Famula

This is Capstone Project of Udacity's Android Developer Nanodegree.


## Screenshots

<img src="https://raw.githubusercontent.com/emrekose26/udacity-nanodegree-projects/master/Capstone%20Project/Build/Famula/art/famula1.jpg" width="350"> <img src="https://raw.githubusercontent.com/emrekose26/udacity-nanodegree-projects/master/Capstone%20Project/Build/Famula/art/famula2.jpg" width="350"> 
<img src="https://raw.githubusercontent.com/emrekose26/udacity-nanodegree-projects/master/Capstone%20Project/Build/Famula/art/famula3.jpg" width="350"> <img src="https://raw.githubusercontent.com/emrekose26/udacity-nanodegree-projects/master/Capstone%20Project/Build/Famula/art/famula4.jpg" width="350">
<img src="https://raw.githubusercontent.com/emrekose26/udacity-nanodegree-projects/master/Capstone%20Project/Build/Famula/art/famula5.jpg" width="350"> <img src="https://raw.githubusercontent.com/emrekose26/udacity-nanodegree-projects/master/Capstone%20Project/Build/Famula/art/famula6.jpg" width="350">
<img src="https://raw.githubusercontent.com/emrekose26/udacity-nanodegree-projects/master/Capstone%20Project/Build/Famula/art/famula7.jpg" width="350"> <img src="https://raw.githubusercontent.com/emrekose26/udacity-nanodegree-projects/master/Capstone%20Project/Build/Famula/art/famula8.jpg" width="350">
<img src="https://raw.githubusercontent.com/emrekose26/udacity-nanodegree-projects/master/Capstone%20Project/Build/Famula/art/famula9.jpg" width="350">


## App Description
Famula is an application which allows users search and discover restaurants by location around of them. It provides restaurant adress, call number, rate, other user reviews

### App Documentation is [here](https://github.com/emrekose26/udacity-nanodegree-projects/blob/master/Capstone%20Project/Design/Capstone_Stage1.pdf)

## Rubric

### Common Project Requirements

- [x] App conforms to common standards found in the [Android Nanodegree General Project Guidelines](http://udacity.github.io/android-nanodegree-guidelines/core.html)
- [x] App is written solely in the Java Programming Language 
- [x] App utilizes stable release versions of all libraries, Gradle, and Android Studio.

### Core Platform Development

- [x] App integrates a third-party library.
- [x] App validates all input from servers and users. If data does not exist or is in the wrong format, the app logs this fact and does not crash.
- [x] App includes support for accessibility. That includes content descriptions, navigation using a D-pad, and, if applicable, non-audio versions of audio cues.
- [x] App keeps all strings in a strings.xml file and enables RTL layout switching on all layouts.
- [x] App provides a widget to provide relevant information to the user on the home screen.

### Google Play Services

- [x] App integrates two or more Google services. Google service integrations can be a part of Google Play Services or Firebase.
- [x] Each service imported in the build.gradle is used in the app.
- [x] If Admob is used, the app displays test ads. If Admob was not used, student meets specifications.
- [x] If Maps is used, the map provides relevant information to the user. If Maps was not used, student meets specifications.

### Material Design

- [x] App theme extends AppCompat.
- [x] App uses an app bar and associated toolbars.
- [x] App uses standard and simple transitions between activities.

### Building

- [x] App builds from a clean repository checkout with no additional configuration
- [x] App builds and deploys using the installRelease Gradle task.
- [x] App is equipped with a signing configuration, and the keystore and passwords are included in the repository. Keystore is referred to by a relative path.
- [x] All app dependencies are managed by Gradle.

### Data Persistence

- [x] App stores data locally either by implementing a ContentProvider OR using Firebase Realtime Database OR using Room. No third party frameworks nor Persistence Libraries may be used.
- [x] Must implement at least one of the three
If it regularly pulls or sends data to/from a web service or API, app updates data in its cache at regular intervals using a SyncAdapter or JobDispacter.
OR
If it needs to pull or send data to/from a web service or API only once, or on a per request basis (such as a search application), app uses an IntentService to do so.
OR
It it performs short duration, on-demand requests(such as search), app uses an AsyncTask.
- [x] If Room is used then LiveData and ViewModel are used when required and no unnecessary calls to the database are made.


## Technologies used

* MVVM Pattern
* [Architecture Components (LiveData, ViewModel, Room)](https://developer.android.com/topic/libraries/architecture/) for creating robust, testable, maintable app
* [Dagger2](https://github.com/google/dagger) for dependency injection
* [RxJava](https://github.com/ReactiveX/RxJava) & [RxAndroid](https://github.com/ReactiveX/RxAndroid) for composing asynchronus and event-based programming
* [Retrofit](https://github.com/square/retrofit) for a type safe HTTP client
* [OkHttp](https://github.com/square/okhttp) for HTTP and HTTP/2 client
* [GSON](https://github.com/google/gson) for JSON serialization
* [EasyPermissions](https://github.com/googlesamples/easypermissions) for easy way runtime permission
* [Stetho](https://github.com/facebook/stetho) for network inspection
* [Glide](https://github.com/bumptech/glide) for image loading and caching
* [Circle ImageView](https://github.com/hdodenhof/CircleImageView) for circular ImageView
* [Timber](https://github.com/JakeWharton/timber) ​for logging


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