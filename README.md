# Photo Galley for Flickr

"Photo Galley for Flickr" is an Android Flickr client application that downloads and displays photos from Flickr's public feed written in native code. The app fetches and displays photos from Flickr, focusing on networking and background tasks.

Technologies Used: Kotlin, Android SDK, RecyclerView, Coil, Retrofit, WorkManager.

Key Features:
- Background Work: Implemented background tasks using WorkManager to fetch data from Flickr.
- Networking: Utilized Retrofit for making API calls to Flickr's web service.
- Image Loading: Integrated Coil library for efficient image loading and caching.
- RecyclerView: Displayed fetched images in a RecyclerView for a smooth and scrollable user experience.
- Model-View-ViewModel: Managed UI-related data lifecycle.


Paging library were not used in this reposiroty unlike the other repo that started the project from scratch.
