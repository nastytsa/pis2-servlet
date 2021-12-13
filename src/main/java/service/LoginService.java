package service;

import dao.implementations.UserDaoImpl;
import entities.User;

import java.util.Optional;

public class LoginService {
    public static User getUser(Long id, String password){
        UserDaoImpl userDao = new UserDaoImpl();
        Optional<User> user = userDao.get(id);

        if(user.isEmpty()) return null;
        if(!password.equals(user.get().getPassword())) return null;
        return user.get();
    }
}
