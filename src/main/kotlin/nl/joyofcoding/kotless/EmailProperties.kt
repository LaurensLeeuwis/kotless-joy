package nl.joyofcoding.kotless

import java.util.Properties

object EmailProperties {
    const val SMTP_USERNAME = "secret"
    const val SMTP_PASSWORD = "secret"
    const val HOST = "email-smtp.eu-central-1.amazonaws.com"

    val PROPS = Properties().let {
        it["mail.transport.protocol"] = "smtp"
        it["mail.smtp.port"] = 587
        it["mail.smtp.starttls.enable"] = "true"
        it["mail.smtp.auth"] = "true"
        it
    }
}