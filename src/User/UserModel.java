package User;

import java.util.*;

public class UserModel {
    private ArrayList<User> userList;

    public UserModel() {
        userList = new ArrayList<User>();
    }

    /* PRIVATE METHODS */
    /**
     * Check if the ID is in the system and return index
     * 
     * @param id the ID to check
     * @return -1 if not found, otherwise return index
     */
    private int checkID(String id) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getID().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    /* END OF PRIVATE METHODS */

    /* PUBLIC METHODS */
    /* Getter */
    /**
     * Get the user list
     * 
     * @return the user list
     */
    public ArrayList<User> getUserList() {
        return userList;
    }

    /**
     * Get size of user list
     */
    public int getUserListSize() {
        return userList.size();
    }

    /**
     * Get the user with the given ID
     *
     * @param id the ID to find
     * @return the user with the given ID, null if not found
     */
    public User getUser(String id) {
        int index = checkID(id);
        if (index >= 0) {
            return userList.get(index);
        }
        return null;
    }

    /**
     * Get username by id
     */
    public String getUserName(String id) {
        int index = checkID(id);
        if (index >= 0) {
            return userList.get(index).getName();
        }
        return null;
    }

    /**
     * Get list of User by name (partially match)
     * 
     * @param name
     * @return found: list of <index of userList, User>,
     *         otherwise: null
     */
    public HashMap<Integer, User> findUser(String name) {
        HashMap<Integer, User> found = new HashMap<Integer, User>();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getName().contains(name)) {
                found.put(i, userList.get(i));
            }
        }
        return found;
    }

    /* Setter */
    /**
     * Add a user to the system.
     *
     * @param user The user to add.
     */
    public void addUser(User user) throws Exception {
        if (checkID(user.getID()) >= 0) {
            throw new Exception("ID already exists");
        }
        userList.add(user);
    }

    /**
     * Remove a user from the system.
     * 
     * @param user The user to remove.
     */
    public void removeUser(User user) throws Exception {
        int index = checkID(user.getID());
        if (index < 0) {
            throw new Exception("ID not found");
        }
        if (userList.get(index).getID().equals("admin")) {
            throw new Exception("Cannot remove admin");
        }
        if (!user.isMaster()) {
            Customer c = (Customer) user;
            if (c.getNumBorrowing() > 0) {
                throw new Exception("Cannot remove customer being borrowing");
            }
        }
        userList.remove(index);
    }

    /**
     * Simply update user
     * Use only when you didn't change ID
     * 
     * @param user
     */
    public void updateUser(User user) {
        int index = checkID(user.getID());
        if (index >= 0) {
            userList.set(index, user);
        }
    }

    /**
     * Update a user by replacing
     * Use when you want to change ID
     * *This method is necessary because you can't find a user if the ID is changed.
     * 
     * @param user     the user to update
     * @param name     new one
     * @param id       new one
     * @param password new one
     */
    public void updateUser(User user, String name, String id, String password) {
        int index = checkID(user.getID());
        user.setName(name);
        user.setID(id);
        user.setPassword(password);
        if (index >= 0) {
            userList.set(index, user);
        }
    }

}