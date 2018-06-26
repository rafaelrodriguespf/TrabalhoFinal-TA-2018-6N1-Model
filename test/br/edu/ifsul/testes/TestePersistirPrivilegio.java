package br.edu.ifsul.testes;


import br.edu.ifsul.modelo.Privilegio;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author Jorge
 */
public class TestePersistirPrivilegio {
    
  EntityManagerFactory emf;
    EntityManager em;
    

    public TestePersistirPrivilegio() {

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
    public void testePersistirPais() {
        boolean exception = false;
        try {
           
            Privilegio p1 = new Privilegio();
            p1.setTipo("GERENTE");
            p1.setDescricao("Usuario administrativo");
           
            Privilegio p2 = new Privilegio();
            p2.setTipo("VENDEDOR");
            p2.setDescricao("Usuario simples");
            
            em.getTransaction().begin();
            em.persist(p1);
            em.persist(p2);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            exception = true;
            
        }
        // verificando se o resultado é igual ao esperado
        Assert.assertEquals(false, exception);
    }
    
}
