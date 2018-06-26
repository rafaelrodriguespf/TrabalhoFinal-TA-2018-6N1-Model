/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;


import br.edu.ifsul.modelo.Funcionario;
import br.edu.ifsul.modelo.Privilegio;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class TestePersistirFuncionario {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirFuncionario() {
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
            Funcionario obj = new Funcionario();
            obj.setEmail("rafaelrodriguespf@gmail.com");
            obj.setNascimento(Calendar.getInstance());
            obj.setNome("Rafael Rodrigues");
            obj.setPassword("admin");
            obj.setCpf("83173609034");
           
            Privilegio p = em.find(Privilegio.class, "GERENTE");
            Privilegio p1 = em.find(Privilegio.class, "VENDEDOR");
            obj.getPrivilegios().add(p);
            obj.getPrivilegios().add(p1);
            em.persist(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
        }
        Assert.assertEquals(false, exception);
    }

}
