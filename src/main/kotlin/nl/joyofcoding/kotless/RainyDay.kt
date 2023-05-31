package nl.joyofcoding.kotless

import io.kotless.dsl.lang.http.Get
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.html
import kotlinx.html.img
import kotlinx.html.p
import kotlinx.html.stream.createHTML

@Get("/")
fun rainyDay() =
    getBuienRadarData().forecast.fivedayforecast[0]
        .takeIf { it.rainChance >= 20 }
        ?.let {
            // kotlinx.html
            createHTML().html {
                body {
                    h1 { +"Take your umbrella?" }
                    img {
                        src = it.iconurl
                    }
                    p {
                        +it.weatherdescription
                    }
                }
            }
        }
