package module;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URI;
import java.net.URLClassLoader;

public class GameLauncher {
	public void launchGame(String path) {
		File file = new File(path);
		try {
			// TODO Auto-generated method stub
			URL url = null;
			try {
				url = file.toURI().toURL();
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
			URL[] urls = {url};
			
			URLClassLoader child = new URLClassLoader (urls, this.getClass().getClassLoader());
			Class classToLoad;
			classToLoad = Class.forName ("Game", true, child);
			Method method = classToLoad.getDeclaredMethod ("startGame");
			Object instance = classToLoad.newInstance ();
			Object result = method.invoke (instance);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
