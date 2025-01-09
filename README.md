# UserConnect

A modern Android application that demonstrates best practices in Android development using Jetpack Compose, Hilt dependency injection, and MVVM architecture. The app fetches and displays user information while handling network connectivity states.

## Features

- Display user profiles with images and details
- Real-time network connectivity monitoring
- Graceful error handling
- Pull-to-refresh functionality
- Clean Architecture implementation
- Modern UI with Material Design 3

## Tech Stack

- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **Dependency Injection**: Hilt
- **Networking**: Retrofit
- **Concurrency**: Kotlin Coroutines & Flow
- **Image Loading**: Coil (AsyncImage)
- **API**: MockAPI

## Architecture Components

- **Repository Pattern**: Abstracts data sources and provides a clean API
- **ViewModel**: Manages UI-related data and handles business logic
- **StateFlow**: Handles UI state management
- **NetworkConnectivity**: Monitors device network status
- **ConnectivityReceiver**: Broadcasts network state changes


## Setup & Installation

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run the app on an emulator or physical device


## Network Handling

The app implements robust network handling with:
- Network state monitoring
- Automatic retry on network recovery
- User-friendly error messages
- Network state broadcasts

## UI States

The app handles three main states:
- Loading: Shows a progress indicator
- Success: Displays the user list
- Error: Shows error message with retry option

## Dependencies

```gradle
dependencies {
    // Compose
    implementation("androidx.activity:activity-compose")
    implementation(platform("androidx.compose:compose-bom"))
    
    // Hilt
    implementation("com.google.dagger:hilt-android")
    kapt("com.google.dagger:hilt-android-compiler")
    
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit")
    implementation("com.squareup.retrofit2:converter-gson")
    
    // Coil
    implementation("io.coil-kt:coil-compose")
}
```
