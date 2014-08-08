firstandroid
============

A very basic android app with  Asynchrous requests to fetch data over REST (using Spring restTemplate), Results are cached with ormlite, callback to activities when the data is loaded, jackson json/object mapping, maven based (for android deployments). All the data loading is in the background service.
We are using Robospice framework inernally for basic functionality.

A user can login to exchange, view his organizations, view his contacts and download/view the documents. (Supported only pdf and jpg at the moment)


Setup:

Android SDK:
Download and install the latest android SDK from : http://developer.android.com/sdk/index.html Now add the android_home and tools path to your profile: 

On linux:
Open Terminal
Type vi ~/.bash_profile in command line. It creates it, if its already not there
Type following in vi editor:
export PATH=$PATH:<PATH-TO-SDK>/android/sdks/android-sdk-macosx/tools:<PATH-TO-SDK>/android/sdks/android-sdk-macosx/platform-toolsexport ANDROID_HOME=<PATH-TO-SDK>/android/sdks/android-sdk-macosx

start android emulator device either from eclipse or from command avd.

To compile and deploy application:
mvn clean compile install android:deploy

