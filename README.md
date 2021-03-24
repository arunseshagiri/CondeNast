## CondeNast Assignment
Assignment for CondeNast

## Clone the project from github
1. Go to https://github.com/arunseshagiri/CondeNast
2. Click on this icon:
   
   <img width="125" alt="Screenshot 2021-03-24 at 10 55 51 PM" src="https://user-images.githubusercontent.com/13729393/112355923-22e0d180-8cf4-11eb-8bc7-56bffd061deb.png">

3. Chose HTTPS and copy the url shown in the dropdown.
4. Go to Terminal on MAC and type the below command

   git clone https://github.com/arunseshagiri/CondeNast.git

5. Project will be cloned inside CondeNast folder
6. You would see something like this:

   <img width="666" alt="Screenshot 2021-03-24 at 11 00 09 PM" src="https://user-images.githubusercontent.com/13729393/112356573-bd411500-8cf4-11eb-920c-fe9462eaff6d.png">

7. Cloning of project completed successfully.


## Importing the project to Android Studio
1. In Android Studio, choose open an Existing Project if in welcome screen, else go to file > New > Import project
2. Go to the location on your disk where project was cloned. 
3. Go inside CondeNast folder > NewsUpdates > Now click on **open**
4. The project will be imported to Android Studio, however, build.gradle will fail with following error:

   <img width="933" alt="Screenshot 2021-03-24 at 11 07 04 PM" src="https://user-images.githubusercontent.com/13729393/112357589-afd85a80-8cf5-11eb-82d9-bf630a7a9d6e.png">

## Resolve Build.gradle error
1. Go to <location where project was cloned>/CondeNast/NewsUpdates > local.properties

2. Open local.properties and add below line:

   debugApiKey="apiKey=API_KEY_SHARED_OVER_MAIL"

   releaseApiKey="apiKey=API_KEY_SHARED_OVER_MAIL"

3. Now rebuild the gradle file again by clicking on this icon in Android Studio: 

   <img width="38" alt="Screenshot 2021-03-24 at 11 14 13 PM" src="https://user-images.githubusercontent.com/13729393/112358790-a996ae00-8cf6-11eb-80d4-0b26cc611fca.png">
   
4. Once gradle build suceeds, go to Build > Make Project

   This will build the project. This means, our NewsUpdates APK is ready inside builds folder of the project :) 

5. Once build completes successfully, choose Run > Run 'app'
6. A new Emulator will be launched, and once build completes, the apk will be installed automatically to the Emulator and launch the app

   Note: Read Config information to know on which devices this app would run

## Run tests on Android Studio
1. Select com.arunkumar.newsupdates(Test) package from Project
2. Right click on this package and select > 

<img width="647" alt="Screenshot 2021-03-24 at 11 45 28 PM" src="https://user-images.githubusercontent.com/13729393/112362968-05633600-8cfb-11eb-8a13-5e8f9dc2d483.png">

3. Once all tests finish running, you would see the output something like below under **Run** tab:

<img width="932" alt="Screenshot 2021-03-24 at 11 46 43 PM" src="https://user-images.githubusercontent.com/13729393/112363155-32174d80-8cfb-11eb-9acd-51efb35a3ac4.png">


## App Screenshots

<img width="361" alt="Screenshot 2021-03-24 at 11 20 59 PM" src="https://user-images.githubusercontent.com/13729393/112359744-99cb9980-8cf7-11eb-8fac-499eb6338774.png">

<img width="369" alt="Screenshot 2021-03-24 at 11 22 29 PM" src="https://user-images.githubusercontent.com/13729393/112359938-cf708280-8cf7-11eb-8118-beaedac8a91b.png">

## App Config

App Version 1.0.0 (1)

minSdkVersion 21

targetSdkVersion 29

compileSdkVersion 29

Please make sure, the Android device is running Lollipop and above


