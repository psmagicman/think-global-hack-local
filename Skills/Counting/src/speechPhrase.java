import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class speechPhrase
{
	public static List<String> motivationalPhrases = new ArrayList<String>();
	
	public speechPhrase()
	{
		motivationalPhrases.add("good job");
		motivationalPhrases.add("you are so smart");
		motivationalPhrases.add("that is correct");
		motivationalPhrases.add("wow awesome");
		motivationalPhrases.add("nicely done");
		motivationalPhrases.add("amazing work");
		motivationalPhrases.add("fantastic");
		motivationalPhrases.add("keep up the good work");
		motivationalPhrases.add("excellent work");
		motivationalPhrases.add("yes, that is the right answer");
		motivationalPhrases.add("ding ding ding ding");
		motivationalPhrases.add("success");
		motivationalPhrases.add("you did it");
		motivationalPhrases.add("well done ");
	}
	
	public String getRandomCompliment()
	{
		System.out.printf(motivationalPhrases.get(GenerateRandomNumber())); //debug
		return motivationalPhrases.get(GenerateRandomNumber());	
	}

	public int GenerateRandomNumber()
	{
		Random generator = new Random();
		return generator.nextInt(motivationalPhrases.size());
	}
}