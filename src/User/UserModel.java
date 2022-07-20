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

    /* END OF Getter */

    /**
     * Add a user to the system.
     *
     * @param user The user to add.
     * @return true if the user is added, false if the ID is already in
     *         the system.
     */
    public boolean addUser(User user) {
        if (checkID(user.getID()) >= 0) {
            return false;
        }
        userList.add(user);
        return true;
    }

    /**
     * Remove a user from the system.
     * 
     * @param user The user to remove.
     * @return true if the user is removed, false if the user is not in the
     *         system.
     */
    public boolean removeUser(User user) {
        int index = checkID(user.getID());
        if (index >= 0) {
            userList.remove(index);
            System.out.println("User " + user.getName() + " is removed.");
            return true;
        }
        return false;
    }

    /**
     * Update a user by replacing
     * 
     * @param user
     */
    public void updateUser(User user) {
        int index = checkID(user.getID());
        if (index >= 0) {
            userList.set(index, user);
            System.out.println("User " + user.getName() + " is updated.");
        }
    }

}