package com.example.petagrampersistencia

import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

object MailSender {

    fun enviar(asunto: String, cuerpo: String) {
        val props = Properties().apply {
            put("mail.smtp.host", "smtp.gmail.com")
            put("mail.smtp.port", "587")
            put("mail.smtp.auth", "true")
            put("mail.smtp.starttls.enable", "true")
        }

        val sesion = Session.getInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication =
                PasswordAuthentication(MailConfig.CORREO, MailConfig.CLAVE_APP)
        })

        val mensaje = MimeMessage(sesion)
        mensaje.setFrom(InternetAddress(MailConfig.CORREO))
        mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(MailConfig.DESTINO))
        mensaje.subject = asunto
        mensaje.setText(cuerpo)

        Transport.send(mensaje)
    }
}
