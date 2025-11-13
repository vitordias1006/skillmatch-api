package br.com.fiap.bo;

import br.com.fiap.dao.UserDAO;
import br.com.fiap.to.UserTO;

public class UserBO {
    private UserDAO userDAO;

    public UserTO saveUser(UserTO userTO) {
        userDAO = new UserDAO();

        return userDAO.saveUser(userTO);
    }

    public UserTO findById(Long id) {
        UserTO userTO = new UserTO();

        return userDAO.findById(id);
    }

    public UserTO updateUser(UserTO userTO) {
        userDAO = new UserDAO();

        return userDAO.updateUser(userTO);
    }

    public boolean deleteUser(Long id) {
        userDAO = new UserDAO();

        return userDAO.deleteById(id);
    }

    public UserTO findByEmailAndPassword(String email, String password) {
        if(userDAO == null){
            throw new RuntimeException("Usuário não existente");
        }
        return userDAO.findByEmailAndPassword(email, password);
    }
}
