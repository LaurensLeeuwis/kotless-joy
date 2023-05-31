package nl.joyofcoding.kotless

import jakarta.mail.Message
import jakarta.mail.Session
import jakarta.mail.Transport
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeMessage
import nl.joyofcoding.kotless.EmailProperties.HOST
import nl.joyofcoding.kotless.EmailProperties.PROPS
import nl.joyofcoding.kotless.EmailProperties.SMTP_PASSWORD
import nl.joyofcoding.kotless.EmailProperties.SMTP_USERNAME

object Emailer {
    // Too much code to read in 15 seconds
    // See https://github.com/LaurensLeeuwis for code
    fun sendEmail(subject: String, body: String, fromAddress: String,
                  fromName: String, toAddress: String) : Boolean {
        val session: Session = Session.getDefaultInstance(PROPS)
        val msg = MimeMessage(session)
        msg.setFrom(InternetAddress(fromAddress, fromName))
        msg.setRecipient(Message.RecipientType.TO, InternetAddress(toAddress))
        msg.subject = subject
        msg.setContent(body, "text/html")
        val transport: Transport = session.getTransport()
        return try {
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD)
            transport.sendMessage(msg, msg.allRecipients)
            true
        } catch (ex: Exception) {
            false
        } finally {
            transport.close()
        }
    }
}
