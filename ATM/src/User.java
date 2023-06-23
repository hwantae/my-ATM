
public class User {
	private String name;

	public User(String inputName) {
		// name is initialized by constructor
		name = inputName;
	}
	//this function returns value of name
	public String getName() {
		return name;
	}
	//this function enables users to set the value of name.
	public void setName(String name) {
		this.name = name;
	}
	
	
}