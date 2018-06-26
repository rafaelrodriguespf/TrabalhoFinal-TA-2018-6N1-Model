/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;


import br.edu.ifsul.modelo.Automovel;
import br.edu.ifsul.modelo.Combustivel;
import br.edu.ifsul.modelo.Funcionario;
import br.edu.ifsul.modelo.Modelo;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class TestePersistirAutomovel {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirAutomovel() {
    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("TrabalhoFinal-TA-2018-6N1-ModelPU");
        em = emf.createEntityManager();
    }

    @After
    public void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    public void teste() {
        boolean exception = false;
        try {
            em.getTransaction().begin();
            Automovel obj = new Automovel(); 
            
            obj.setQuilometragem("60000");
            obj.setAnoModelo(Calendar.getInstance());
            obj.setDescricao("Carro de exelente qualidade");
            obj.setCor("Branco");
            obj.setMotor(2.0);
            obj.setPlaca("ITF1122");
            obj.setCombustivel(em.find(Combustivel.class, 1));
            obj.setModelo(em.find(Modelo.class, 3));
            obj.setPortas(4);
            obj.setValor(1000.00);
            obj.setFuncionario(em.find(Funcionario.class, 1));        
            em.persist(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
        }
        Assert.assertEquals(false, exception);
    }

}
