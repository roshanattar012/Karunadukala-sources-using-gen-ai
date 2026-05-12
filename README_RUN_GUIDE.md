# Karunada-Kala Source – Run Guide

## 🚀 Setup Instructions

### 1. Firebase Configuration
- Create a new project on [Firebase Console](https://console.firebase.google.com/).
- Add an Android App with package name `com.karunada.kala`.
- Download `google-services.json` and place it in the `app/` directory.
- Enable **Authentication** (Email/Password & Google).
- Enable **Cloud Firestore** and **Firebase Storage**.
- Use the provided `firestore_sample_data.json` to populate your collections (`artisans`, `workshops`, `art_forms`, `events`, `products`).

### 2. Google Maps API
- Go to [Google Cloud Console](https://console.cloud.google.com/).
- Enable **Maps SDK for Android**.
- Create an API Key.
- Add the key to `local.properties`:
  ```
  MAPS_API_KEY=your_api_key_here
  ```

### 3. Gemini AI API
- Get an API key from [Google AI Studio](https://aistudio.google.com/).
- Add the key to `local.properties`:
  ```
  GEMINI_API_KEY=your_gemini_key_here
  ```

### 4. Build & Run
- Open the project in Android Studio.
- Sync Gradle.
- Run the `app` module on an emulator or physical device.

## 🛠️ Tech Stack
- **Architecture**: Clean Architecture + MVVM
- **UI**: Jetpack Compose (Material 3)
- **Local DB**: Room (Offline-first)
- **Remote**: Firebase Firestore
- **DI**: Hilt
- **AI**: Google Gemini Flash 1.5
- **Maps**: Google Maps Compose with Clustering

## 📂 Project Structure
- `data/`: Local (Room) and Remote (Firestore) data sources and repositories.
- `domain/`: Use Cases (Clean Architecture).
- `ui/`: Compose screens, ViewModels, and Theme.
- `service/`: AI and Messaging services.

---
*Created for Karunada-Kala Source - Promoting Karnataka's Heritage.*
