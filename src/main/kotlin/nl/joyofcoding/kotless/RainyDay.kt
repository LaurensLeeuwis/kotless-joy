package nl.joyofcoding.kotless

import io.kotless.dsl.lang.event.Scheduled
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.html
import kotlinx.html.img
import kotlinx.html.p
import kotlinx.html.stream.createHTML

// Amazon Cloudwatch cron. Runs 21:55 UTC, daily
@Scheduled("55 21 * * ? *")
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
        ?.let {
            Emailer.sendEmail("Take your umbrella?", it,
                "from@example.com", "Apps",
                "to@example.com")
        }
        ?.let {
            if (it) {"email sent"} else {"sending email failed"}
        }
        ?: "No chance of rain"