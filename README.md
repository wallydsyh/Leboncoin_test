# Leboncoin_test

Technical Decisions:

I chose the go with the MVVM architecture because it’s more befitting for this project’s size and it’s the architecture that I know the most in term of implementations along with MVC, and MVP, but those are kind of outdated To request Data from server I used RxJava and Retrofit as REST API, because both combinations provide an exceptional tools to build a strong app capable of requesting data in asynchronous way in a background thread for the network call and the main thread for updating the UI

Gson library to Parse my Json

To load Images I used picasso, I could have gone with Glide, or ImageLoader, but I chose Picasso because of its network and cache performances

Kotlin Coroutines help to manage long-running tasks

ROOM is chosen over SQlite mostly because it’s the one to use with architecture component It’s build to work with LiveData and RxJava for data observation while SQlite does not. Room maps our database objects to  Object without boilerplate code
