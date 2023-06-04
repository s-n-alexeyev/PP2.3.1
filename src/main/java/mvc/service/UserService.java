package mvc.service;


import mvc.model.User;

import java.util.List;

public interface UserService {
    void createUser(User user);

    void updateUser(User user);

    List<User> getAllUsers();

    User readUser(long id);

    User deleteUser(long id);

    void createOrUpdateUser(User user);

}
