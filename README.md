# Karunada-Kala Source – Cultural Discovery & Artisan Platform

Karunada-Kala Source is a production-ready Android application developed to preserve, promote, and digitally showcase the rich cultural heritage of Karnataka. The application connects traditional artisans, performers, workshops, and cultural events with modern audiences through an interactive Android platform powered by AI and cloud technologies.

The platform acts as a “Directory of Pride” where users can explore Karnataka’s traditional art forms, discover artisans, attend cultural workshops, view performances, and interact with AI-generated cultural information.

---

# Features

## Heritage Explorer

* Explore Karnataka traditional art forms
* Modern grid-based cultural discovery interface
* Search and category-based filtering

## Smart Maps Integration

* Google Maps SDK integration
* Real-time artisan and workshop locations
* Event discovery using custom markers

## AI Cultural Context

* Integrated Google Gemini AI
* Generates cultural and historical explanations
* Interactive AI-powered learning experience

## Artisan Directory

* Detailed artisan profiles
* Contact and location information
* Traditional skill showcase

## Marketplace

* Discover handcrafted products
* Promote local artisans digitally
* Support cultural entrepreneurship

## Workshops & Events

* Register for cultural workshops
* Explore upcoming performances and exhibitions
* Event participation management

## Rich Media Feed

* ExoPlayer-powered video content
* Traditional performances like:

  * Yakshagana
  * Dollu Kunitha
  * Folk music
  * Cultural festivals

## Smart Call Feature

* One-tap artisan contact using system dialer

## User Profile Management

* Firebase Authentication support
* User preferences and activity management

## Bilingual Support

* English and Kannada language support

---

# Technologies Used

| Category             | Technologies            |
| -------------------- | ----------------------- |
| Programming Language | Kotlin                  |
| Architecture         | MVVM                    |
| UI Framework         | Jetpack Compose         |
| Design System        | Material Design 3       |
| Backend Services     | Firebase                |
| Authentication       | Firebase Authentication |
| Database             | Firebase Firestore      |
| Local Storage        | Room Database           |
| Maps Integration     | Google Maps SDK         |
| AI Integration       | Google Gemini API       |
| Media Playback       | ExoPlayer               |
| IDE                  | Android Studio          |

---

# System Architecture

The application follows MVVM (Model-View-ViewModel) architecture for scalable and maintainable development.

```text
User
   ↓
Android Application UI
   ↓
ViewModel Layer
   ↓
Repository Layer
   ↓
Firebase / Room DB / Gemini API / Google Maps
```

## Module Flow

```text
Authentication Module
        ↓
Home / Explorer Module
        ↓
 ├── Heritage Explorer
 ├── Smart Maps
 ├── AI Cultural Context
 ├── Marketplace
 ├── Workshops & Events
 ├── Media Feed
 └── User Profile
```

---

# Problem Statement

Traditional Karnataka art forms such as Yakshagana, Mysore Painting, Bidriware, Channapatna Toys, and Dollu Kunitha struggle to gain digital visibility in modern platforms.

Many users:

* cannot locate authentic artisans,
* do not know where workshops are conducted,
* are unaware of cultural events,
* and lack access to verified cultural learning resources.

Similarly, artisans struggle to:

* promote their work digitally,
* reach broader audiences,
* and preserve traditional knowledge among younger generations.

Karunada-Kala Source addresses this problem by building a centralized digital ecosystem for cultural discovery and preservation.

---

# Objectives

* Preserve Karnataka’s cultural heritage digitally
* Promote traditional artisans and performers
* Improve accessibility to workshops and events
* Build an AI-powered cultural learning platform
* Develop a scalable Android application using modern technologies

---

# Installation & Setup

## Prerequisites

* Android Studio Iguana or newer
* JDK 17
* Firebase Project
* Google Maps API Key
* Gemini API Key

---

## Clone Repository

```bash
git clone https://github.com/roshanattar012/Karunadukala-sources-using-gen-ai.git
```

---

## Open Project

1. Open Android Studio
2. Click Open Existing Project
3. Select cloned repository folder

---

## Configure Firebase

1. Create Firebase project
2. Add Android application
3. Download `google-services.json`
4. Place inside:

```text
app/
```

---

## Configure Google Maps API

Add Maps API key inside:

```xml
AndroidManifest.xml
```

```xml
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="YOUR_MAPS_API_KEY"/>
```

---

## Configure Gemini API

Add Gemini API key inside constants or environment configuration.

Example:

```kotlin
const val GEMINI_API_KEY = "YOUR_API_KEY"
```

---

# Build & Run

## Generate APK

In Android Studio:

```text
Build → Build APK(s)
```

APK output path:

```text
app/build/outputs/apk/debug/
```

---

# Firebase Hosting Deployment

The project includes Firebase Hosting deployment support for:

* APK distribution
* Project showcase
* Live demonstration

Live URL:

[Karunada-Kala Live Website](https://karunadakala-dcc1a.web.app?utm_source=chatgpt.com)

GitHub Repository:

[Karunadukala Sources Using Gen AI GitHub Repository](https://github.com/roshanattar012/Karunadukala-sources-using-gen-ai.git?utm_source=chatgpt.com)

---

# Implementation Details

## Firebase Authentication

Used for:

* User login
* Secure session management
* Profile authentication

Example:

```kotlin
private lateinit var auth: FirebaseAuth

auth = FirebaseAuth.getInstance()
```

---

## Google Maps Integration

Used for:

* Artisan location discovery
* Workshop and event mapping
* Real-time cultural navigation

Example:

```kotlin
val location = LatLng(15.3173, 75.7139)

mMap.addMarker(
    MarkerOptions().position(location)
)
```

---

## Gemini AI Integration

Used for:

* AI-generated cultural explanations
* Historical context generation
* Interactive learning

Example:

```kotlin
val model = GenerativeModel(
    modelName = "gemini-pro",
    apiKey = GEMINI_API_KEY
)
```

---

# Results & Analysis

## Achievements

* Successfully developed Android application
* Integrated Firebase services
* Implemented Google Maps functionality
* Added AI-based cultural information generation
* Designed responsive Material UI
* Enabled APK distribution through Firebase Hosting

## Performance Observations

* Smooth UI navigation
* Efficient Firebase synchronization
* Responsive Google Maps rendering
* Dynamic AI response generation
* Stable media playback using ExoPlayer

---

# Challenges Faced

* Firebase authentication configuration
* Google Maps permission handling
* Managing asynchronous API calls
* Video feed optimization
* Real-time data synchronization

---

# Learning Outcomes

## Technical Skills

* Kotlin programming
* MVVM architecture
* Firebase integration
* REST API handling
* AI integration
* Android UI/UX design

## Soft Skills

* Problem solving
* Debugging
* System design
* Technical documentation

---

# Future Scope

Future enhancements planned include:

* AI-based personalized recommendations
* Advanced multilingual support
* Push notifications
* Marketplace expansion
* Analytics dashboard
* Social interaction features
* Play Store deployment
* Advanced AI chatbot integration

---

# Conclusion

Karunada-Kala Source demonstrates how modern Android technologies and AI can be effectively used for cultural preservation and artisan empowerment.

The project successfully combines:

* Android development,
* cloud technologies,
* AI integration,
* and cultural heritage preservation

into a scalable and impactful digital platform.

---

# References

* Firebase Documentation
* Android Developers Documentation
* Google Maps SDK Documentation
* Gemini AI Documentation
* Kotlin Official Documentation

---

# Developer

**Roshan Attar**
USN: 2LB22AI017
Department of Artificial Intelligence & Machine Learning

Internship Project developed under MindMatrix Internship Program.
