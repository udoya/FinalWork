package User;

abstract class User {
    protected String name;
    protected int id;
    protected boolean isMaster = false;
    protected String password;

    // For Sign in
    // Constructor
    public User(String name, int id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }
}
