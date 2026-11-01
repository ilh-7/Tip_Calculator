# Tip Calculator 💵
 
An Android app built with **Kotlin**, **Jetpack Compose** and **Material 3** that calculates restaurant tips based on the tipping culture in different US states. Calculations can be saved and are stored locally with a **Room** database.
 
## Features
 
- **Landing page:** welcome message and a button for two states (California and Arizona)
- **State page:** welcome message and info about the state's usual tip
- **Tip calculator:** enter the bill amount, choose 10% or 15%, and see the amount, tip and total to pay
- **History:** save calculations to a local database, view them per state, delete one or clear all
- Input validation (accepts both `.` and `,` as decimal separator)
## Stack
 
- Kotlin
- Jetpack Compose + Material 3
- Navigation Compose
- Room (SQLite) with KSP, Coroutines and Flow
- JUnit for unit tests
