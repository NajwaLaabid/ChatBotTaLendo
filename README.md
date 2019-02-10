# ChatBot Tá Lendo: Simple Android App to test Google Cloud Text to speech API in Brazilian Portuguese.

<b>ChatBot tá lendo</b> (literally translating to 'ChatBot is reading') is a Kotlin android app using [Google Cloud Text To Speech API](https://cloud.google.com/text-to-speech/docs/basics) and [SSML](https://developers.google.com/actions/reference/ssml) to read an input text. 

## What's the idea?
- Read the user's input from the provided text field. 
- Send an asynchronous POST request to Google Cloud TTS API using retrofit2 and rxjava to receive an audio version of the user's input.
- Parse the API response to extract the base64-encoded audio file sent from TTS API.
- Decode the string received into ArrayByte and save it in an MP3 file in the internal storage of the phone.
- Play the file saved using Android MediaPlayer library.

## System Setting
- Download Android Studio with the Kotlin plugin.
- Clone the repository locally.
- Open the project in android studio.
- Set a simulator if you haven't already. Run the code.
- Enter text and hit "L?" to read it.

## For beginners:
- Technologies: Read more about Kotlin and its relationship to Java [here](https://www.netguru.com/blog/kotlin-java-which-one-you-should-choose-for-your-next-android-app).
- Front-end: More about the design and building of Android Apps [here](https://developer.android.com/training/basics/firstapp/building-ui).
- Networking: Concepts of networking in Android. About retrofit2 and rxjava2 libraries [here](https://medium.com/3xplore/handling-api-calls-using-retrofit-2-and-rxjava-2-1871c891b6ae).
- Audio: Save files in Kotlin. More about MediaPlayer class in Kotlin [here](https://developer.android.com/reference/kotlin/android/media/MediaPlayer)

## Notes:

This app currently uses the app build of [Wiki Search Demo](https://github.com/elye/demo_wiki_search_count).




















