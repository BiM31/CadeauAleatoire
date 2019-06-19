import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Class to calculate and inform each person of the targetted person they have to give a gift.
 */
public class RandomGift
{
    private static final String __SMTP_HOST = "smtp.mail.org";
    private static final String __SENDER = "no-reply@mail.org";
    
    /**
     * This method will launch the process of generating a random order to offer a gift to a random person.
     * @param persons The list of persons.
     */
    public static void launch(List<Person> persons)
    {
        int nbPersons = persons.size();
        
        // Generation of the random list
        Random r = new Random();
        
        Person[] listeEnvoi = new Person[nbPersons];
        for (int i = 0; i < nbPersons; i++)
        {
            listeEnvoi[i] = null;
        }
        
        for (int i = 0; i < nbPersons; i++)
        {
            int set;
            do
            {
                set = r.nextInt(nbPersons);
            }
            while (!(listeEnvoi[set] == null));
            listeEnvoi[set] = persons.get(i);
        }
        
        Set<String> logLines = new HashSet<>();
        // Send mail to the next person in the array
        for (int i = 0; i < nbPersons; i++)
        {
            Person offerer = listeEnvoi[i];
            Person winner = listeEnvoi[(i+1)%nbPersons];
            _sendMail(offerer, winner);
            logLines.add(offerer.getFirstName() + " -> " + winner.getFirstName());
        }
        
        // Log in a file, just in case...
        _log(logLines);
    }
    
    /**
     * Send the mail.
     * @param offerer The person who offer the gift
     * @param winner The person who receive the gift
     */
    private static void _sendMail(Person offerer, Person winner)
    {
         try
         {
              Properties prop = System.getProperties();
              prop.put("mail.smtp.host", __SMTP_HOST);
              Session session = Session.getDefaultInstance(prop, null);
              Message message = new MimeMessage(session);
              message.setFrom(new InternetAddress(__SENDER));
              InternetAddress[] internetAddresses = new InternetAddress[1];
              internetAddresses[0] = new InternetAddress(offerer.getMail());
              message.setRecipients(Message.RecipientType.TO, internetAddresses);
              message.setSubject("[Cadeau Noël] Et l'heureux gagnant de votre cadeau sera...");
              message.setText(winner.getFirstName());
              Transport.send(message);
         }
         catch (AddressException e)
         {
              e.printStackTrace();
         }
         catch (MessagingException e)
         {
              e.printStackTrace();
         }
    }
    
    /**
     * Write the log file.
     * @param logLines The lines to log
     */
    private static void _log(Set<String> logLines)
    {
        try (FileWriter writer = new FileWriter("gift-" + System.currentTimeMillis() + ".txt", true))
        {
            for (String logLine : logLines)
            {
                writer.write(logLine + System.getProperty("line.separator"));
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
