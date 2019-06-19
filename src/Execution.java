import java.util.ArrayList;
import java.util.List;

/**
 * Execution class.
 */
public class Execution
{
    /**
     * Launch the execution.
     * @param args Nothing needed in the arguments
     */
    public static void main(String[] args)
    {
        List<Person> persons = new ArrayList<>();
        /* Add here the persons who participates */
        persons.add(new Person("Challenger 1", "challenger1@mail.org"));
        persons.add(new Person("Challenger 2", "challenger2@mail.org"));
        persons.add(new Person("Challenger 3", "challenger3@mail.org"));
        persons.add(new Person("Challenger 4", "challenger4@mail.org"));
        persons.add(new Person("Challenger 5", "challenger5@mail.org"));
        persons.add(new Person("Challenger 6", "challenger6@mail.org"));
        persons.add(new Person("Challenger 7", "challenger7@mail.org"));
        persons.add(new Person("Challenger 8", "challenger8@mail.org"));
        persons.add(new Person("Challenger 9", "challenger9@mail.org"));
        
        RandomGift.launch(persons);
    }
}
