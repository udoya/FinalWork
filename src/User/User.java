package User;

public class User {
    protected String name;
    protected String id;
    protected String password;
    protected boolean isMaster = false;

    // For Sign in
    // Constructor
    public User(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }

    // Setter
    void setName(String name) {
        this.name = name;
    }

    void setID(String id) {
        this.id = id;
    }

    void setIsMaster(boolean isMaster) {
        this.isMaster = isMaster;
    }

    void setPassword(String password) {
        this.password = password;
    }

    // Getter
    public String getName() {
        return name;
    }

    public String getID() {
        return id;
    }

    public boolean isMaster() {
        return isMaster;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        if (isMaster) {
            return "Staff";
        } else {
            return "Customer";
        }
    }
}
