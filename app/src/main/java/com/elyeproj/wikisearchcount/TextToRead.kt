package com.elyeproj.wikisearchcount

data class TextToRead( val input: Input, val voice: Voice, val audioConfig: AudioConfig )
data class Input( val ssml: String )
data class Voice ( val languageCode: String, val name: String, val ssmlGender: String )
data class AudioConfig ( val audioEncoding: String )