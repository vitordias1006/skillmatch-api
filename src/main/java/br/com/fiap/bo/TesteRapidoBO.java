package br.com.fiap.bo;

import br.com.fiap.dao.TesteRapidoDAO;
import br.com.fiap.to.TesteRapidoTO;

import java.util.ArrayList;
import java.util.List;

public class TesteRapidoBO {
    private TesteRapidoDAO testeRapidoDAO;

    public TesteRapidoTO saveTest(TesteRapidoTO testeRapidoTO){
        testeRapidoDAO = new TesteRapidoDAO();

        return testeRapidoDAO.saveTest(testeRapidoTO);
    }

    public ArrayList<TesteRapidoTO> findAllMyTests(Long userId){
        testeRapidoDAO = new TesteRapidoDAO();

        return testeRapidoDAO.findAllMyTests(userId);
    }

    public TesteRapidoTO updateTest(TesteRapidoTO testeRapidoTO){
        testeRapidoDAO = new TesteRapidoDAO();
        return testeRapidoDAO.updateTest(testeRapidoTO);
    }

    public boolean deleteTest(Long testId){
        testeRapidoDAO = new TesteRapidoDAO();

        return testeRapidoDAO.deleteTest(testId);
    }
}
