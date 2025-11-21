package br.com.fiap.dao;

import br.com.fiap.to.UsuarioTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public UsuarioTO saveUser(UsuarioTO usuarioTO) {
        String sql = "Insert into usuario (nome, email, senha, idade) values (?, ?, ?, ?) ";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql, new String[]{"id"})){
            ps.setString(1, usuarioTO.getName());
            ps.setString(2, usuarioTO.getEmail());
            ps.setString(3, usuarioTO.getPassword());
            ps.setInt(4, usuarioTO.getAge());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("Erro ao inserir usuaro!");
                return null;
            }

            long idUser;
            try (ResultSet rsKeys = ps.getGeneratedKeys()) {
                if (rsKeys.next()) {
                    idUser = rsKeys.getInt(1);
                    usuarioTO.setId(idUser);

                } else {
                    System.out.println("Não foi possível recuperar o ID do usuário.");
                    return null;
                }
            }

        } catch (SQLException e){
            System.out.println("Erro ao inserir usuario: " + e.getMessage());
        } finally{
            ConnectionFactory.closeConnection();
        }
        return usuarioTO;
    }

    public UsuarioTO findById(long id){
        UsuarioTO usuarioTO = new UsuarioTO();
        String sql = "select * from usuario where id = ?";

        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuarioTO.setId(rs.getLong("id"));
                usuarioTO.setName(rs.getString("nome"));
                usuarioTO.setEmail(rs.getString("email"));
                usuarioTO.setPassword(rs.getString("senha"));
                usuarioTO.setAge(rs.getInt("idade"));
            } else{
                return null;
            }

        }catch (SQLException e) {
            System.out.println("Erro ao recuperar usuario: " + e.getMessage());
        } finally{
            ConnectionFactory.closeConnection();
        }
        return usuarioTO;
    }

    public UsuarioTO updateUser(UsuarioTO usuarioTO) {
        String sql = "Update usuario set nome=?, senha=?, idade=? where id=?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setString(1, usuarioTO.getName());
            ps.setString(2, usuarioTO.getPassword());
            ps.setInt(3, usuarioTO.getAge());
            ps.setLong(4, usuarioTO.getId());

            if (ps.executeUpdate() > 0) {
                return usuarioTO;
            } else  {
                return usuarioTO;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuario: " + e.getMessage());
        } finally{
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean deleteById(Long id){
        String sql = "delete from usuario where id=?";

        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar usuário " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public UsuarioTO findByEmailAndPassword(String email, String password) {
        UsuarioTO usuarioTO = null;
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuarioTO = new UsuarioTO();
                usuarioTO.setId(rs.getLong("id"));
                usuarioTO.setName(rs.getString("nome"));
                usuarioTO.setEmail(rs.getString("email"));
                usuarioTO.setPassword(rs.getString("senha"));
                usuarioTO.setAge(rs.getInt("idade"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarioTO;
    }

    public UsuarioTO findByEmail(String email) {
        String sql = "SELECT * FROM usuario WHERE email = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UsuarioTO user = new UsuarioTO();
                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("nome"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("senha"));
                    user.setAge(rs.getInt("idade"));
                    return user;
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
