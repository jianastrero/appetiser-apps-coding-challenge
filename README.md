# Appetiser Apps Coding Challenge
#### Jian James P. Astrero

## Architecture: Model - View - ViewModel (MVVM)
I have chosen to use the MVVM architecture because:
* It is recommended by Android. ([https://developer.android.com/jetpack/docs/guide#recommended-app-arch](https://developer.android.com/jetpack/docs/guide#recommended-app-arch))
* The View in MVVM takes care of the UI logic. Meaning, your ViewModel/Presenter/Controller doesn't have to do all the work. It helps a lot in identifying in what part of the code you would have to check.
* The ViewModel takes care of exposing data to use by the ViewModel in an observable way. So, you could just bind the exposed data to the view instead of setting the value using the Presenter/Controller.
