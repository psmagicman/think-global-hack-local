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
		motivationalPhrases.add("excellent work");
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