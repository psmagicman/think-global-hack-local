import java.util.*;

public class GameLogic 
{
	public final int MIN_LEVEL = 1;
	public final int MAX_LEVEL = 9;
	
	private int currentNumber;

	private static int level;	// when creating game, this level will be set according to the user prefernece MAYBE or just Min Level
	private static final HashMap<Integer, Integer> LevelMultiplier;
	static
	{
		LevelMultiplier = new HashMap<Integer, Integer>();
		LevelMultiplier.put(1,10);
		LevelMultiplier.put(2,100);
		LevelMultiplier.put(3,1000);
		LevelMultiplier.put(4,10000);
		LevelMultiplier.put(5,100000);
		LevelMultiplier.put(6,1000000);
		LevelMultiplier.put(7,10000000);
		LevelMultiplier.put(8,100000000);
		LevelMultiplier.put(9,1000000000);
	}
	
	GameLogic(int initialLevel)
	{
		if (!SetLevel(initialLevel))
			level = MIN_LEVEL;
	}
	
	int GenerateRandomNumber()
	{
		Random generator = new Random();
		int currentNumber = generator.nextInt(LevelMultiplier.get(level));
		
		return currentNumber;
	}
	
	boolean SetLevel(int newLevel)
	{
		if (newLevel >= MIN_LEVEL && newLevel <= MAX_LEVEL)
		{
			level = newLevel;
			return true;
		}
		return false;
	}
	
	int GetCurrentNumber()
	{
		return currentNumber;
	}
	
	int GetNextNumber()
	{
		return currentNumber + 1;
	}
	
	boolean IsThisDigitCorrect(int digit, int position)
	{
		String nextNumberInString = new Integer(GetNextNumber()).toString();
		
		return nextNumberInString.charAt(position) == digit;	// check char and int conversion
	}
}
