package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;

public class UsuarioBO {
    private UsuarioDAO usuarioDAO;

    public UsuarioTO saveUser(UsuarioTO usuarioTO) {
        usuarioDAO = new UsuarioDAO();

        UsuarioTO emailExiste = usuarioDAO.findByEmail(usuarioTO.getEmail());
        if (emailExiste != null) {
            throw new RuntimeException("Email já está em uso.");
        }

        return usuarioDAO.saveUser(usuarioTO);
    }

    public UsuarioTO findById(Long id) {
        usuarioDAO = new UsuarioDAO();

        return usuarioDAO.findById(id);
    }

    public UsuarioTO updateUser(UsuarioTO usuarioTO) {
        usuarioDAO = new UsuarioDAO();

        return usuarioDAO.updateUser(usuarioTO);
    }

    public boolean deleteUser(Long id) {
        usuarioDAO = new UsuarioDAO();

        return usuarioDAO.deleteById(id);
    }

    public UsuarioTO findByEmailAndPassword(String email, String password) {
        if(usuarioDAO == null){
            throw new RuntimeException("Usuário não existente");
        }
        return usuarioDAO.findByEmailAndPassword(email, password);
    }
}
