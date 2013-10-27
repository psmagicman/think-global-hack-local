import users.User;
import users.UserManagementService;
import module.IGame;


public class Game implements IGame
{
	@Override
	public void startGame() 
	{
		Counting.main(null);		
	}
}
