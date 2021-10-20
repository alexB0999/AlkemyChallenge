package com.project.DisneyApi.sendgrid;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    /*@Value("${app.sendgrid.templateId}")
    private String templateId;*/

    @Value("${app.sendgrid.key}")
    private String appkey;

    @Autowired
    SendGrid sendGrid;

    private String remitente = "alexis.bahi@alumnos.frm.utn.edu.ar";

    private String destinatario;

    private String asunto = "Bienvenida al Sistema";

    private String mensaje = "Se ha registrado exitosamente como usuario."
            +"\n" +"Le damos la bienvenida al sistema, muchas gracias por suscribirse";

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public void sendWelcomeEmail() throws IOException {

        Email from = new Email();
        from.setEmail(remitente);
        String subject = asunto;
        Email to = new Email();
        to.setEmail(this.destinatario);
        Content content = new Content("text/plain", mensaje);
        Mail mail = new Mail(from, subject, to, content);
        //mail.setTemplateId(templateId);

        SendGrid sg = new SendGrid(appkey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }

}
