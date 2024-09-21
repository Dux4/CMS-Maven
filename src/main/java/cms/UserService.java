package cms;

public class UserService {
    public User validateLog(String user, String password){
        User us = null;
        if(user != null && !user.isEmpty() && user.equals(password)){
            us = new User(user,password);
        }
        return us;
    }
}
