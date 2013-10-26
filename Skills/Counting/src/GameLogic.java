import java.util.*;

public class GameLogic 
{
	public final int MIN_LEVEL = 1;
	public final int MAX_LEVEL = 9;
	
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
	
	int GenerateRandomNumber()
	{
		Random generator = new Random();
		int randomNumber = generator.nextInt(LevelMultiplier.get(level));
		
		return randomNumber;
	}
	
	void SetLevel(int newLevel)
	{
		if (newLevel >= MIN_LEVEL && newLevel <= MAX_LEVEL)
			level = newLevel;
	}
	
}
