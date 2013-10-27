package users;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;

public class UserManagementService {
	private static UserManagementService instance = new UserManagementService();
	
	private static User mainUser;
	
	private UserManagementService() {
		mainUser = new User("testUser");
	}
	
	public static UserManagementService getInstance() {
		return instance;
	}
	
	public User getMainUser()
	{
		return mainUser;
	}
	
	public void setMainUser(User user) {
		mainUser = user;
	}
    
	public List<User> getUsers(){
		List<User> users = new ArrayList<User>();
		
		File file = new File(System.getProperty("user.dir") + "/data");
		if(!file.exists()) {
			boolean r = file.mkdir();
			System.out.println(r);
		}

		File[] files = file.listFiles();
		if(!file.exists()) {
			boolean r = file.mkdir();
			System.out.println(r);
		} 

		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().contains(".xml")) {
				XStream xs = new XStream(new DomDriver());
				User newUser = new User("");
				try {
					FileInputStream fis = new FileInputStream("data/" + files[i].getName());
					xs.fromXML(fis,newUser);
					users.add(newUser);
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		return users;
		
	}
	
	public User createUser(String name) throws NameTakenException {

		/* TEST ALL ITEMS IN LIST
		for (int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).getName());
		}
		*/
		
		List<User> users = getUsers();

		for (Object user : users){
			if (((User)user).getName().equals(name)){
				throw new NameTakenException(String.format("The name %s is already taken!", name));
			}
		}
		
		User newUser = new User(name);
		
		//generate a random id
		newUser.setId(makeUniqueId(users));
		File file = new File(System.getProperty("user.dir") + "/data");

		if(!file.exists()) {
			boolean r = file.mkdir();
			System.out.println(r);
		} 
		
		XStream xs = new XStream();
		try {
			FileOutputStream fs = new FileOutputStream("data/" + newUser.getId() + ".xml");
			xs.toXML(newUser,fs);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		users.add(newUser);
		return newUser;
	}
	
	public void saveMainUser() {
		XStream xs = new XStream();
		try {
			FileOutputStream fs = new FileOutputStream("data/" + mainUser.getId() + ".xml");
			xs.toXML(mainUser,fs);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	// Given a list of users, produces a unique ID
	private int makeUniqueId(List<User> users) {
		Random gen = new Random();
		int id = Math.abs(gen.nextInt());
		while (!isUnique(id, users)) {
			id = Math.abs(gen.nextInt());
		}
		return id;
	}

	// Return true if none of the users have the given id.
	private boolean isUnique(int id, List<User> users) {
		for (User u : users) {
			if (u.getId() == id)
				return false;
		}
		return true;
	}

}
