package br.com.fiap.dao;

import br.com.fiap.to.UserTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public UserTO saveUser(UserTO userTO) {
        String sql = "Insert into usuario (nome, email, senha, idade, tipo_perfil) values (?, ?, ?, ?, ?) ";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setString(1, userTO.getName());
            ps.setString(2, userTO.getEmail());
            ps.setString(3, userTO.getPassword());
            ps.setInt(4, userTO.getAge());
            ps.setString(5, userTO.getProfileType());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("Erro ao inserir usuaro!");
                return null;
            }

            long idUser;
            try (ResultSet rsKeys = ps.getGeneratedKeys()) {
                if (rsKeys.next()) {
                    idUser = rsKeys.getInt(1);
                    userTO.setId(idUser);
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
        return userTO;
    }

    public UserTO findById(long id){
        UserTO userTO = new UserTO();
        String sql = "select * from usuario where id = ?";

        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userTO.setId(rs.getLong("id"));
                userTO.setName(rs.getString("nome"));
                userTO.setEmail(rs.getString("email"));
                userTO.setPassword(rs.getString("senha"));
                userTO.setAge(rs.getInt("idade"));
                userTO.setProfileType(rs.getString("tipo_perfil"));
            } else{
                return null;
            }

        }catch (SQLException e) {
            System.out.println("Erro ao recuperar usuario: " + e.getMessage());
        } finally{
            ConnectionFactory.closeConnection();
        }
        return userTO;
    }

    public UserTO updateUser(UserTO userTO) {
        String sql = "Update usuario set nome=?, email=?, senha=?, idade=?, tipo_perfil=? where id=?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setString(1, userTO.getName());
            ps.setString(2, userTO.getEmail());
            ps.setString(3, userTO.getPassword());
            ps.setInt(4, userTO.getAge());
            ps.setString(5, userTO.getProfileType());
            ps.setLong(6, userTO.getId());

            if (ps.executeUpdate() > 0) {
                return userTO;
            } else  {
                return userTO;
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

    public UserTO findByEmailAndPassword(String email, String password) {
        UserTO userTO = null;
        String sql = "SELECT * FROM user WHERE email = ? AND senha = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                userTO.setId(rs.getLong("id"));
                userTO.setName(rs.getString("nome"));
                userTO.setEmail(rs.getString("email"));
                userTO.setPassword(rs.getString("senha"));
                userTO.setAge(rs.getInt("idade"));
                userTO.setProfileType(rs.getString("tipo_perfil"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userTO;
    }
}
