package source;

public class People { //People class file
    private String name;
    private String password;
    private int ID;
    private int type;
    
	public People(String name, String password, int id, int type) {
		this.name = name;
		this.password = password;
		this.ID = id;
		this.type = type;
	}
	
	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

}
