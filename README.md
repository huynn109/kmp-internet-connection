# kmp-internet-connection

## Overview
A Kotlin Multiplatform (KMP) library to monitor internet connection status across different platforms (Android, iOS).

## Features
- Monitor internet connection status on Android and iOS.
- Provides a simple API to start and stop monitoring.
- Uses Kotlin Coroutines and Flow for reactive programming.

<img src="img/1.png" alt="Android" width="250"/>

## Installation

### Gradle
Add the following to your `build.gradle.kts` file:

```kotlin
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.huynn109.kmp.internetconnection:shared:1.0.0")
            }
        }
        val androidMain by getting
        val iosMain by getting
    }
}
```

## Usage

### Common Code
In your common code.

```kotlin
expect class InternetStatus {
    val current: MutableStateFlow<Boolean>
    fun start()
    fun stop()
}
```

### Android
In your Android-specific code, you can use the same `InternetConnection` class.

```kotlin
actual class InternetStatus(
    context: Context
) {
    val internetConnection = InternetConnection(context)
    actual val current: MutableStateFlow<Boolean> = internetConnection.isNetworkConnected

    actual fun start() {
        internetConnection.start()
    }

    actual fun stop() {
        internetConnection.stop()
    }
}
```

### iOS
In your iOS-specific code, you can use the same `InternetConnection` class.

```kotlin
TODO
```

## License
This project is licensed under the MIT License. See the `LICENSE` file for more details.