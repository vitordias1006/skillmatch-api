package br.com.fiap.dao;

import br.com.fiap.to.TesteRapidoTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TesteRapidoDAO {

    public TesteRapidoTO saveTest(TesteRapidoTO testeRapidoTO) {
        String sql = "insert into TESTE_RAPIDO (id_usuario, respostas, resultado, data_realizacao) values (?, ?, ?, ?) ";

        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql, new String[]{"id_teste"})){
            ps.setLong(1, testeRapidoTO.getUserId());
            ps.setString(2, testeRapidoTO.getAnswers());
            ps.setString(3, testeRapidoTO.getResult());
            ps.setDate(4, java.sql.Date.valueOf(testeRapidoTO.getRealizationDate()));

            int rows = ps.executeUpdate();
            if (rows == 0){
                System.out.println("Erro ao salvar teste");
                return null;
            }

            long testId;

            try (ResultSet rsKeys = ps.getGeneratedKeys()) {
                if (rsKeys.next()) {
                    testId = rsKeys.getInt(1);
                    testeRapidoTO.setTestId(testId);
                } else {
                    System.out.println("Não foi possível recuperar o ID do teste.");
                    return null;
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro no teste" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return testeRapidoTO;
    }

    public ArrayList<TesteRapidoTO> findAllMyTests(Long userId) {
        ArrayList<TesteRapidoTO> tests = new ArrayList<>();
        String sql = "select * from TESTE_RAPIDO where id_usuario = ?";
        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TesteRapidoTO testeRapidoTO = new TesteRapidoTO();
                testeRapidoTO.setTestId(rs.getLong("id_teste"));
                testeRapidoTO.setUserId(rs.getLong("id_usuario"));
                testeRapidoTO.setAnswers(rs.getString("respostas"));
                testeRapidoTO.setResult(rs.getString("resultado"));
                testeRapidoTO.setRealizationDate(LocalDate.parse(rs.getString("data_realizacao")));
                tests.add(testeRapidoTO);
            }

        } catch (SQLException e) {
            System.out.println("Nenhum teste encontrado" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return tests;
    }

    public TesteRapidoTO updateTest(TesteRapidoTO testeRapidoTO) {
        String sql = "Update TESTE_RAPIDO set respostas = ? , resultado = ? where id_teste = ? ";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setString(1, testeRapidoTO.getAnswers());
            ps.setString(2, testeRapidoTO.getResult());
            ps.setLong(3, testeRapidoTO.getTestId());

            if (ps.executeUpdate() > 0){
                return testeRapidoTO;
            } else  {
                return testeRapidoTO;
            }

        } catch (SQLException e) {
            System.out.println("Não foi possível atualizar teste" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean deleteTest(Long testId) {
        String sql = "Delete from TESTE_RAPIDO where id_teste = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setLong(1, testId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e){
            System.out.println("Não foi possível deletar teste" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }
}
