# Appetiser Apps Coding Challenge
#### Jian James P. Astrero

### Requirements:
Minimum SDK Version: 21 or 5.0 or Lollipop

### Download
[Google Drive - APK](https://drive.google.com/open?id=1VstytPY2CJDHvH9j8T1IeNfK91xshPSr)

## Architecture: Model - View - ViewModel (MVVM)
I have chosen to use the MVVM architecture because:
* It is recommended by Android. ([https://developer.android.com/jetpack/docs/guide#recommended-app-arch](https://developer.android.com/jetpack/docs/guide#recommended-app-arch))
* The View in MVVM takes care of the UI logic. Meaning, your ViewModel/Presenter/Controller doesn't have to do all the work. It helps a lot in identifying in what part of the code you would have to check.
* The ViewModel takes care of exposing data to use by the ViewModel in an observable way. So, you could just bind the exposed data to the view instead of setting the value using the Presenter/Controller.

## Persistence
* Date when the user last visited
* Save the last screen that the user was on. When the app restarts, it should present the user with that screen.
* I did not save any search data or data took from the api because search results should always be fresh. Unless, i have a server that could handle caching of 3rd part API, there is no good reason the persist search data.

## Documentation
* Not all functions and statements are documented
* Some functions/statements are self explanatory for beginner programmers, hoping the fact that "Source codes" will be read by any type of programmer and not by a person with no programming background.
* But, most are well documented.


