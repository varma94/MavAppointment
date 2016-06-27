package uta.mav.appoint.email;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.mail.BodyPart;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class Email {
	private String subject;
	private String text;
	private String toEmail;
        final String user = "mavappoint.donotreply@gmail.com";
		final String pass = "mavappointemail";

	public Email(String sub, String txt, String destEmail) {
		subject = sub;
		text = txt;
		toEmail = destEmail;
		// toEmail = "mavappoint.donotreply@gmail.com";
	}

	public void sendMail() {
		

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, pass);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(
					"mavappoint.donotreply@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toEmail));
			message.setSubject(subject);
			message.setText(text);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
        
        public void sendEventSync(String description, String summary, Calendar start, Calendar end) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        Calendar currentTime = Calendar.getInstance();

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "smtp.gmail.com";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");

      // Get the Session object.
      Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, pass);
         }
      });

        try {
            // Define message
            MimeMessage message = new MimeMessage(session);
            message.addHeaderLine("method=REQUEST");
            message.addHeaderLine("charset=UTF-8");
            message.addHeaderLine("component=VEVENT");

            message.setFrom(new InternetAddress("mavappoint.donotreply@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);

            StringBuffer sb = new StringBuffer();

            StringBuffer buffer = sb.append("BEGIN:VCALENDAR\n" +
                    "PRODID:-//Microsoft Corporation//Outlook 9.0 MIMEDIR//EN\n" +
                    "VERSION:2.0\n" +
                    "METHOD:REQUEST\n" +
                    "BEGIN:VEVENT\n" +
                    "ATTENDEE;ROLE=REQ-PARTICIPANT;RSVP=TRUE:MAILTO:mavappoint.donotreply@gmail.com\n" +
                    "ORGANIZER:MAILTO:mavappoint.donotreply@gmail.com\n" +
                    "DTSTART:" + format.format(start.getTime()) +"\n" + //"20051208T053000Z\n" +
                    "DTEND:" + format.format(end.getTime()) + "\n" + //20051208T060000Z\n" +
                    "LOCATION:Advising Office\n" +
                    "TRANSP:OPAQUE\n" +
                    "SEQUENCE:0\n" +
                    "UID:040000008200E00074C5B7101A82E00800000000002FF466CE3AC5010000000000000000100\n" +
                    " 000004377FE5C37984842BF9440448399EB02\n" +
                    "DTSTAMP:" + format.format(currentTime.getTime()) + //"20051206T120102Z\n" +
                    "CATEGORIES:Meeting\n" +
                    "DESCRIPTION:" + description + "\n" +
                    "SUMMARY:" + summary + "\n" +
                    "PRIORITY:5\n" +
                    "CLASS:PUBLIC\n" +
                    "BEGIN:VALARM\n" +
                    "TRIGGER:PT1440M\n" +
                    "ACTION:DISPLAY\n" +
                    "DESCRIPTION:Reminder\n" +
                    "END:VALARM\n" +
                    "END:VEVENT\n" +
                    "END:VCALENDAR");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setHeader("Content-Class", "urn:content-  classes:calendarmessage");
            messageBodyPart.setHeader("Content-ID", "calendar_message");
            messageBodyPart.setDataHandler(new DataHandler(
                    new ByteArrayDataSource(buffer.toString(), "text/calendar")));// very important

            // Create a Multipart
            Multipart multipart = new MimeMultipart();

            // Add part one
            multipart.addBodyPart(messageBodyPart);

            // Put parts in message
            message.setContent(multipart);

            // send message
            Transport.send(message);
        } catch (MessagingException me) {
            me.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
