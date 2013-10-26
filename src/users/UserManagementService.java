package users;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;

public class UserManagementService {
	public static User mainUser = new User("test");
	public static User getMainUser()
	{
		return mainUser;
	}
	
	public static void createUser(String name) throws NameTakenException{
		User newUser = new User(name);
		
		//generate a random id
		Random gen = new Random();
		int id = gen.nextInt();
		//TODO: Should check if this id is unique!!!!
		newUser.setId(id);
		File file = new File("../../user-store/");
		if(!file.exists()) {
			boolean r = file.mkdir();
			System.out.println(r);
		} 
		PersistenceStrategy strategy = new FilePersistenceStrategy(file);
		XmlArrayList list = new XmlArrayList(strategy);
		
		for (Object user : list){
			if (((User)user).getName().equals(name)){
				throw new NameTakenException(String.format("The name %s is already taken!", name));
			}
		}
		
		list.add(newUser);
	}
}