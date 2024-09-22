package cms;

public interface UserPersistencia {
    void createUser(String username, String password);
    void listUsers();
    void updateUser(String oldUsername, String newUsername, String newPassword);
    void deleteUser(String username);
    User getUserByUsername(String username);
}
