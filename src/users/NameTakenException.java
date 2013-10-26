package users;

public class NameTakenException extends Exception {
	
	private String error;
	
	public NameTakenException(String error){
		this.error = error;
	}
	
	public String getError(){
		return error;
	}

}
