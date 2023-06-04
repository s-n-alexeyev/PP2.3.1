package mvc.dao;

import mvc.model.User;
import java.util.List;

public interface UserDao {

    public void createUser(User user);
    public List<User> getAllUsers();
    void updateUser(User user);
    public User readUser(long id);
    public User deleteUser(long id) throws NullPointerException;


}
