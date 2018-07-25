// original repo: https://github.com/elye/demo_wiki_search_count

package com.elyeproj.wikisearchcount

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener



class MainActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    private val gTTSService by lazy {
        GTTSService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_read.setOnClickListener {

            if (text_to_read.text.toString().isNotEmpty()) {
                beginSearch(text_to_read.text.toString())
            }

        }
    }

    private fun beginSearch(searchString: String) {
        var input = Input("<speak>\n" +
                "  Aqui são as mostras de <say-as interpret-as=\"characters\">SSML</say-as>.\n" +
                "  Eu posso fazer uma pausa <break time=\"3s\"/>.\n" +
                "  Eu posso tocar um som.\n" +
                "  <audio src=\"https://www.example.com/MY_MP3_FILE.mp3\">\n" +
                "não conseguiu o seu arquivo de áudio MP3</audio>.\n <break strength=\"weak\"/>" +
                "  Eu consigo falar em cardinais. <break strength=\"weak\"/> Seu número é<say-as interpret-as=\"cardinal\">10</say-as>.\n" +
                "  Ou consigo falar em ordinais. <break strength=\"weak\"/> Você está o <say-as interpret-as=\"ordinal\">10</say-as> na linha.\n" +
                "  Ou consigo falar em digitais. <break strength=\"weak\"/> Os digitós por 10 são <say-as interpret-as=\"characters\">10</say-as>.\n" +
                " <break strength=\"medium\"/>  Eu também posso substituir frases, por exemplo o <sub alias=\"World Wide Web Consortium\">W3C</sub>.\n" +
                "  Finalmente, eu posso falar um parágrafo com duas frases.\n" +
                "  <p><s>Essa é a primiera frase.</s><s>Essa é a segunda.</s></p>\n" +
                "</speak>" )

        input = Input("<speak> Eu consigo ler as datas completas como assim <break strength=\"weak\" /> <say-as interpret-as=\"date\" format=\"yyyymmdd\" detail=\"1\">\n" +
                "  1960-09-10\n" +
                "  </say-as>. Consigo tambèm ler datas feitas do mês e do dia sò <break strength=\"weak\" /> \n" +
                 "  <say-as interpret-as=\"date\" format=\"dm\">10-9</say-as>\n" +
                 ". Os horarios comerciais no Brasil são de \n" +
                 "  <say-as interpret-as=\"time\" format=\"hms24\" detail=\"1\">8h00</say-as>\n" +
                 " até <say-as interpret-as=\"time\" format=\"hhms24\" detail=\"1\">18h00</say-as>\n" +
                 ". O número do telefone da sankhya è <say-as interpret-as=\"telephone\">(34) 3239-0700</say-as> </speak> ")

        input = Input("<speak> Oi. <break time=\"500ms\"/> Eu sou a Bia da Sankhya. Eu quero trocar o som do gato pra você" +
                    "<audio src=\"https://actions.google.com/sounds/v1/animals/cat_purr_close.ogg\">\n" +
                "    <desc>a cat purring</desc>\n" +
                "    PURR (sound didn't load)\n" +
                "  </audio> <prosody rate=\"medium\">Gosta do meu audio?</prosody> <emphasis level=\"strong\">Eu quero saber sua opiniâo sobre isso.</emphasis> </speak>")

        input = Input("<speak>\n" +
                "  <par>\n" +
                "    <media xml:id=\"question\" begin=\"0.5s\">\n" +
                "      <speak>Quem inventou a Internet?</speak>\n" +
                "    </media>\n" +
                "    <media xml:id=\"answer\" begin=\"question.end+2.0s\">\n" +
                "      <speak>A interent foi inventada por os gatos.</speak>\n" +
                "    </media>\n" +
                "    <media begin=\"answer.end-0.2s\" soundLevel=\"-6db\">\n" +
                "      <audio\n" +
                "        src=\"https://actions.google.com/.../cartoon_boing.ogg\"/>\n" +
                "    </media>\n" +
                "    <media repeatCount=\"3\" soundLevel=\"+2.28dB\"\n" +
                "      fadeInDur=\"2s\" fadeOutDur=\"0.2s\">\n" +
                "      <audio\n" +
                "        src=\"https://actions.google.com/.../cat_purr_close.ogg\"/>\n" +
                "    </media>\n" +
                "  </par>\n" +
                "</speak>")

        input = Input("<speak>\n" +
                "  <par>\n" +
                "    <media xml:id=\"question\" begin=\"0.5s\">\n" +
                "      <speak>Quem inventou a Internet?</speak>\n" +
                "    </media>\n" +
                "    <media xml:id=\"answer\" begin=\"question.end+2.0s\">\n" +
                "      <speak>A Internet foi inventada por os gatos.</speak>\n" +
                "    </media>\n" +
                "    <media begin=\"answer.end-0.2s\" soundLevel=\"-6db\">\n" +
                "      <audio\n" +
                "        src=\"https://actions.google.com/sounds/v1/animals/cartoon_boing.ogg\"/>\n" +
                "    </media>\n" +
                "    <media repeatCount=\"3\" soundLevel=\"+2.28dB\"\n" +
                "      fadeInDur=\"2s\" fadeOutDur=\"0.2s\">\n" +
                "      <audio\n" +
                "        src=\"https://actions.google.com/sounds/v1/animals/cat_purr_close.ogg\"/>\n" +
                "    </media>\n" +
                "  </par>\n" +
                "</speak>")
        val voice = Voice("pt-BR", "pt-BR-Standard-A", "FEMALE" )

        val audioConfig = AudioConfig( "MP3" )
        val ttr = TextToRead(input, voice, audioConfig)

        disposable = gTTSService.read(ttr)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> 
                            //txt_search_result.text = "${result.audioContent}"
                            var text = "${result.audioContent}"
                            val audioByteArray = Base64.decode(text, Base64.DEFAULT )

                            File("${filesDir.absolutePath}/test.mp3").writeBytes(audioByteArray)

                            val mediaPlayer = MediaPlayer.create(this, Uri.parse("/data/user/0/com.elyeproj.wikisearchcount/files/test.mp3") )
                            mediaPlayer.start()

                            mediaPlayer.setOnCompletionListener(OnCompletionListener { mp -> mp.release() })

                        },
                        { error -> println(" ============= Not Working"); print(error) }
                )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
