# Realtime Android App using Android Architecture Components(Kotlin)

This is an android application that showcases how to use Android Architecture Components to build an
 android application that updates in realtime.
 
It uses Pusher as the realtime data source. When a new post event is received, the list of post items
get updated in realtime.


## Requirements
- [A free Pusher account](https://pusher.com)
- [Android Studio](https://developer.android.com/studio/index.html) version 2.3 or above 
- MinSdkVersion: 19
- TargetSdkVersion: 26

## Setup Instructions
1. Create an app on Pusher and copy your app key.
2. Clone this repository and open it with Android Studio.
3. You would need to update the PUSHER_API_KEY static variables in the PusherPostDataSource.kt file to match those of your pusher setup.
4. Build the project and run it.

## Testing Instructions
There are two possible ways to test this application:

First (the easiest way), head over to the Debug Console of the Pusher application you created and
push new post events from the Event Creator.

Second way is to write a Server that would push new post events using the Pusher server libraries. 
More info about this can be found [here](https://pusher.com/docs/server_api_guide)