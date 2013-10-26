import java.util.*;

public class GameLogic 
{
	public final int MIN_LEVEL = 1;
	public final int MAX_LEVEL = 9;	// ask Harry if he still wants max level to be 6

	private static int level;	// when creating game, this level will be set according to the user prefernece MAYBE or just Min Level
	private static final int[] LevelMultiplier = {0, 10, 100, 1000, 10000, 100000, 
												  1000000, 10000000, 100000000, 1000000000};
	
	private int currentNumber;
	private int numberOfAttempts;
	private int numberOfHintsUsed;
	
	GameLogic(int initialLevel)
	{
		if (!SetLevel(initialLevel))
			level = MIN_LEVEL;
		
		numberOfAttempts = 0;	// spec doesn't seem to specify when these numbers should increment. Ask Harry
		numberOfHintsUsed = 0;
	}
	
	int GenerateRandomNumber()
	{
		Random generator = new Random();
		
		int maxNumbrForThisLevel = GetMaxNumberForThisLevel();
		int minNumberForThisLevel = GetMinNumberForThisLevel();
		int currentNumber = generator.nextInt(maxNumbrForThisLevel - minNumberForThisLevel) + minNumberForThisLevel;
		
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
	
	int GetCurrentLevel()
	{
		return level;
	}
	
	boolean IsThisDigitCorrect(int digit, int position)
	{
		String nextNumberInString = new Integer(GetNextNumber()).toString();
		
		return nextNumberInString.charAt(position) == digit;	// check char and int conversion
	}
	
	List<Integer> GetLevels()
	{
		List<Integer> levels = new ArrayList<Integer>();
		for (int i = MIN_LEVEL; i <= MAX_LEVEL; ++i)
			levels.add(i);
		
		return levels;
	}
	
	private int GetMaxNumberForThisLevel()
	{
		return LevelMultiplier[level] - 1;
	}
	
	private int GetMinNumberForThisLevel()
	{
		return LevelMultiplier[level-1];
	}
}
