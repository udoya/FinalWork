package User;

abstract class User {
    protected String name;
    protected int id;
    protected String password;
    protected boolean isMaster = false;

    // For Sign in
    // Constructor
    public User(String name, int id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setIsMaster(boolean isMaster) {
        this.isMaster = isMaster;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public boolean isMaster() {
        return isMaster;
    }
    public String getPassword(int id) {
        if (this.id == id) {
            return password;
        } else {
            return "";
        }
    }
}
