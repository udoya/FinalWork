package User;

public class User {
    protected String name;
    protected int id;
    protected boolean isMaster = false;

    void setName(String name) {
        this.name = name;
    }

    void setID(int id) {
        this.id = id;
    }
}
