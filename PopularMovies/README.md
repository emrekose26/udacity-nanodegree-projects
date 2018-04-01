# Popular Movies 

This is Project 1 & Project 2 of Udacity's Android Developer Nanodegree.

## Screenshots

<img src="https://raw.githubusercontent.com/emrekose26/udacity-nanodegree-projects/master/PopularMovies/art/pmss1.png" width="350"> <img src="https://raw.githubusercontent.com/emrekose26/udacity-nanodegree-projects/master/PopularMovies/art/pmss2.png" width="350">

<img src="https://raw.githubusercontent.com/emrekose26/udacity-nanodegree-projects/master/PopularMovies/art/pmss3.png" width="350"> <img src="https://github.com/emrekose26/udacity-nanodegree-projects/tree/master/PopularMovies/art/pmss4.png" width="350">


## Rubric


| **Common Project Requirements**|
| ------------- |
| App is written solely in the Java Programming Language|
| App conforms to common standards found in the [Android Nanodegree General Project Guidelines](http://udacity.github.io/android-nanodegree-guidelines/core.html)|

|  **User Interface - Layout**  |
| ------------- |
| UI contains an element (e.g., a spinner or settings menu) to toggle the sort order of the movies by: most popular, highest rated|
|Movies are displayed in the main layout via a grid of their corresponding movie poster thumbnails|
|UI contains a screen for displaying the details for a selected movie|
|Movie Details layout contains title, release date, movie poster, vote average, and plot synopsis|
|Movie Details layout contains a section for displaying trailer videos and user reviews|

|  **User Interface - Function**  |
| ------------- |
|When a user changes the sort criteria (most popular, highest rated, and favorites) the main view gets updated correctly|
|When a movie poster thumbnail is selected, the movie details screen is launched|
|When a trailer is selected, app uses an Intent to launch the trailer|
|In the movies detail screen, a user can tap a button(for example, a star) to mark it as a Favorite|

|  **Network API Implementation**  |
| ------------- |
|In a background thread, app queries the `/movie/popular` or `/movie/top_rated` API for the sort criteria specified in the settings menu|
|App requests for related videos for a selected movie via the `/movie/{id}/videos` endpoint in a background thread and displays those details when the user selects a movie|
|App requests for user reviews for a selected movie via the `/movie/{id}/reviews` endpoint in a background thread and displays those details when the user selects a movie|

|  **Data Persistence**  |
| ------------- |
|The titles and IDs of the user’s favorite movies are stored in a native `SQLite` database and are exposed via a `ContentProvider`. This `ContentProvider` is updated whenever the user favorites or unfavorites a movie. No other persistence libraries are used|
|When the "favorites" setting option is selected, the main view displays the entire favorites collection based on movie ids stored in the `ContentProvider`|

## Technologies used

* MVP Pattern
* [TheMovieDB API](https://www.themoviedb.org/documentation/api)
* [ButterKnife](https://github.com/JakeWharton/butterknife) 
* [Dagger2](https://github.com/google/dagger) 
* [RxJava](https://github.com/ReactiveX/RxJava) & [RxAndroid](https://github.com/ReactiveX/RxAndroid) 
* [Retrofit](https://github.com/square/retrofit) 
* [OkHttp](https://github.com/square/okhttp) 
* [GSON](https://github.com/google/gson) 
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