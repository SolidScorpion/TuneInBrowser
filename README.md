# TuneInBrowser
Home Assignment Task

This is my take on TuneIn home assignment.
Project features:
- MVVM
- StateFlow for wrapped UI response (with a small hack - see comments in code)
- Dependency Injection using Hilt
- Gradle Build script files using kotlin
- Retrofit for networking and custom Moshi adapter for parsing response from api
- Unit tests for logic layers (viewmodels, repos, interactors, mapper, adapter)
- Audio streaming using ExoPlayer (what else ?)
- Navigation component for fragment navigation
- Single activity architecture
- Coroutines
- KtLint

What could have been done to make current project better:
- UI tests
- Make some custom composite view for binding items in adapter (good place to demonstrate usage of `<merge>` tag which is often overlooked)
- Design UI better (I took a look @ TuneIn App only after i almost finished the project, but then again, this is not a task for a month of work, right?)
- Make navigation more interesting (maybe include search?)
- Make separate gradle modules for domain and data packages to show that I at least know about this, but I thought that this is a simple test task and might be an overkill (remember: KISS)
- Introduce some kind of local database caching 'cause at the moment everything is fetched from network every time user is entering screen
- More granual error handling 
- Sorting incoming content

Overall it was an interesting experience, and long time since I made something in short period of time. And I guess this is the best I can do at the moment, giving that I wrote this while on "vacation" visiting relatives with a toddler by my side.
To complicate things - this could have been done using vanilla android components (no open source libs), but who does that at the moment?

Hope you don't swear too much when reviewing the code
