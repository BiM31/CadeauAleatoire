/**
 * Objet representing a person.
 */
public class Person
{
    private String _firstName;
    private String _mail;
    
    /**
     * Constructor.
     * @param firstName First name
     * @param mail Mail
     */
    public Person(String firstName, String mail)
    {
        this._firstName = firstName;
        this._mail = mail;
    }
    
    /**
     * Get the first name.
     * @return the first name
     */
    public String getFirstName()
    {
        return _firstName;
    }
    
    /**
     * Get the mail.
     * @return the mail
     */
    public String getMail()
    {
        return _mail;
    }
}
