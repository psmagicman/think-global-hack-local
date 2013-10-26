package users;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javassist.bytecode.Descriptor.Iterator;

import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;

public class UserManagementService {
	public static User mainUser;
	
	public static User getMainUser()
	{
		return mainUser;
	}
    
	public static List<User> getUsers(){
		List<User> users = new ArrayList<User>();
		
		File file = new File(System.getProperty("user.dir") + "/data");
		File[] files = file.listFiles();
		
		PersistenceStrategy strategy = new FilePersistenceStrategy(file);
		// looks up the list:
		List list = new XmlArrayList(strategy);
		
		// remember the list is still there! the files int@[1-5].xml are still in /tmp!
		// the list was persisted!
		
		for(java.util.Iterator it = list.iterator(); it.hasNext(); ) {
			User user = (User) it.next();
			//System.out.println(user.getName());
			users.add(user);
			
		}
		
		return users;
		
	}
		
	
	public static User createUser(String name) throws NameTakenException{

		/* TEST ALL ITEMS IN LIST
		for (int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).getName());
		}
		*/
		List<User> users = getUsers();
	    
		User newUser = new User(name);
		
		//generate a random id
		newUser.setId(makeUniqueId(users));
		
	
		File file = new File("/user-store"); 
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
		return newUser;
	}

	// Given a list of users, produces a unique ID
	private static int makeUniqueId(List<User> users) {
		Random gen = new Random();
		int id = Math.abs(gen.nextInt());
		while (!isUnique(id, users)) {
			id = Math.abs(gen.nextInt());
		}
		return id;
	}

	// Return true if none of the users have the given id.
	private static boolean isUnique(int id, List<User> users) {
		for (User u : users) {
			if (u.getId() == id)
				return false;
		}
		return true;
	}

}
