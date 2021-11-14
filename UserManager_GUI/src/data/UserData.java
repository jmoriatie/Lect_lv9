package data;

public class UserData {
	private String name, id, password;
	private String write;

	public UserData(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.write = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getWrite() {
		return write;
	}

	public void setWrite(String write) {
		this.write = write;
	}
	
}
