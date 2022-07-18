package User;

public class Staff extends User {

    public Staff(String name, int id, String password) {
        super(name, id, password);
        isMaster = true;
    }

}
