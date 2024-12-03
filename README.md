# Notes
![appIcon](https://github.com/user-attachments/assets/7285b7d6-8b52-4474-b33b-e916856bf98e)

## Overview
Notes is an innovative advanced application better than traditional notes applications. It is an Android Kotlin-based application designed for performing CRUD operations on notes in a unique user-authenticated MongoDB remote database. The application uses an API hosted on Render for performing operations on the database.

## Features and Functionalities

### User Accounts
- **Signup and Login:** The first page that pops up on launching the notes app is the signup page for new user authentication. It requires a username, email, and password, and stores the credentials on the remote database. If the user is already registered, they can authenticate via the login page.

### Notes Management
- **Main Screen:** The main screen displays the notes of the authenticated signed-in user in a grid view. It includes options for logging out, editing, and deleting notes.
- **Edit Screen:** The edit screen allows the user to view selected notes and provides options to edit or delete the text.

### Navigation
- **Navigation Graph:** The application uses a navigation graph to manage the interaction between different fragments seamlessly.

## Technologies Used

### Frontend
- **Kotlin:** The app is fully developed using Kotlin, providing modern language features and improved performance.
- **XML:** Used for designing the user interface.
- **Jetpack Compose:** Used for building the UI, providing a modern and declarative approach to designing the user interface.

### Backend
- **Firebase Authentication:** Handles user management including signup, login, and authentication.
- **Firebase Firestore:** A NoSQL document database for storing user data, match details, and chat messages.
- **Firebase Storage:** Used for storing user profile images securely.

### Implementations/Usages
- **Hilt-Dagger:** Used for dependency injection.
- **Retrofit and Coroutines:** For network operations.
- **Model View-View Model (MVVM) Architecture:** Ensures separation of concerns and better manageability of the codebase.
- **Live Data:** For observing data changes in real-time.
- **RecyclerView:** For displaying the list of notes.
- **ViewModelProvider:** For managing UI-related data in a lifecycle-conscious way.
- **Coroutines:** For asynchronous programming.

### Development Tools
- **Render:** For API hosting.
- **Postman:** For API testing.
