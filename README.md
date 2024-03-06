# Android Developer Assignment

An Android Keypad app with Jetpack compose, MVI, and Material3

### Build and run instruction
The project was built with Android Studio Iguana, Kotlin version `1.9.22`, and 
Jetpack Compose BOM version `2024.02.01` and version catalogs to manage dependencies. 
To run this project, kindly follow the following steps

1. Download and install [Android Studio](https://developer.android.com/studio)
2. Download / clone this repository to a folder on your computer.
3. Build/Sync the project, wait for this process to complete.
4. Run the project on either your device or an emulator.

### A brief explanation of the chosen architecture
MVI is short for “Model, View, Intent”. Over the last year this architecture pattern received more 
and more attention among Android developers. It’s similar to other commonly known patterns like MVP or MVVM, 
but it introduces two new concepts: the intent and the state.
The intent is an event sent to the ViewModel by the View in order to perform a particular task. 
It can be triggered by the user or by other parts of your app. As a result of that, a new state is 
set on the ViewModel which in turn updates the user interface. In the MVI architecture, the View listens to the state.
Every time the state changes, the View is notified.

#### Why MVI?

1. Model as State
2. Single Source of Truth
3. No Callbacks
4. Easier to hunt for crash
5. Independent UI Components

#### Advantages of MVI

1. As it is unidirectional, Data flow can be tracked and predicted easily. 
2. It ensures thread safety as the state objects are immutable.
3. Testing the app also will be easier as we can map the business logic for each state.

#### Disadvantages of MVI

1. It leads to lots of boilerplate code as we have to maintain a state for each user action.
2. As we know it has to create lots of objects for all the states. This makes it too costly for app memory management.

#### Resource for more reading on MVI
1. [Android MVI-Reactive Architecture Pattern by Abhishek Srivastava](https://abhiappmobiledeveloper.medium.com/android-mvi-reactive-architecture-pattern-74e5f1300a87)
2. [MVI architecture implementation with Kotlin flow, Android by Meet Janani](https://medium.com/@meetjanani47/mvi-architecture-implementation-with-kotlin-flow-android-ae094fa83bff)
3. [MVI Architecture with Android by Rim Gazzah](https://medium.com/swlh/mvi-architecture-with-android-fcde123e3c4a)
4. [MVI Architecture Explained On Android by Michal Ankiersztajn](https://blog.stackademic.com/mvi-architecture-explained-on-android-e36ee66bceaa)

### Relevant information about the project
The project employs a couple of 3rd party libraries to ensure that I do not try to reinvent the wheel, the notable libraries employed for this project are

1. [GridPad](https://github.com/touchlane/gridpad-android) for laying out the keypad, this ensures that the keys are laid out relative to the size of the display area available
2. [Jetpack Compose](https://developer.android.com/jetpack/compose) for UI components and screens
3. [Material3](https://m3.material.io/) guidelines were observed on this project.