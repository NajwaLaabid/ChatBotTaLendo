<h3>ChatBot Tá Lendo: Simple Android App to test Google Cloud Text to speech API in Brazilian Portuguese.</h3>

<b>ChatBot tá lendo</b> (literally translating to 'ChatBot is reading') is a Kotlin android app using <a href="https://cloud.google.com/text-to-speech/docs/basics" > Google Cloud Text To Speech API </a> and <a href="https://developers.google.com/actions/reference/ssml">SSML</a>  to read an input text. <br>



<div id="flow"> 
	<h4><b>What's the idea?</b></h4>
	- Read the user's input from the provided text field. 
	- Send an asynchronous POST request to Google Cloud TTS API using retrofit2 and rxjava to receive an audio version of the user's input.
	- Parse the API response to extract the base64-encoded audio file sent from TTS API.
	- Decode the string received into ArrayByte and save it in an MP3 file in the internal storage of the phone.
	- Play the file saved using Android MediaPlayer library.
</div>

<div id="test">
	<h4><b>How to test it?</b></h4>
	<b>System Setting</b>
	<ul>
		<li>- Download Android Studio with the Kotlin plugin</li>
		<li>- Clone the repository locally. </li>
		<li>- Open the project in android studio. </li>
		<li>- Set a simulator if you haven't already. Run the code. </li>
		<li>- The app should appear as below. </li>
		<li>- Enter text and hit "L?" to read it. </li>
	</ul>
</div>

<div id="code">
	<h4><b>How does it work?</b></h4>
	<b>Front end</b>
	<b>Networking</b>
	<b>Audio Conversion</b>
	<b>SSML Tags</b>
</div>

<div id="resources">
	<h4><b>For beginners:</b></h4>
	<ul>
		<li>- Technologies: Read more about Kotlin and its relationship to Java here: <a href="">here</a>. </li>
		<li>- Front-end: More about the design and building of Android Apps: </li>
		<li>- Networking: Concepts of networking in Android. About retrofit2 and rxjava2 libraries. </li>
		<li>- Audio: Save files in Kotlin. MediaPlayer class in Kotlin. </li>
	</ul>
</div>


<div id="notes">
	<h4><b>Notes:</b></h4>
	<ul>
		<li>- This app currently uses the app build of <a href="https://github.com/elye/demo_wiki_search_count">Wiki Search Demo</a>.</li>
	</ul>
</div>




















