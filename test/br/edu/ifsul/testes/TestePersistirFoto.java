/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;


import br.edu.ifsul.modelo.Automovel;
import br.edu.ifsul.modelo.Foto;
import br.edu.ifsul.modelo.Funcionario;
import br.edu.ifsul.modelo.Privilegio;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class TestePersistirFoto {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirFoto() {
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
            Automovel a = em.find(Automovel.class, 51);
            Foto f = new Foto();

            Path path = Paths.get("/Users/Rafael/Downloads/sensorUmidade.jpg");
            f.setArquivo(Files.readAllBytes(path));
            a.adicionarFoto(f);
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
        }
        Assert.assertEquals(false, exception);
    }

}
