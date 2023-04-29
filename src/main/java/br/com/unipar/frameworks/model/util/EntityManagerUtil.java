package br.com.unipar.frameworks.model.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {

    private static EntityManager em;
    private static EntityManagerFactory emf; // 

    public EntityManagerUtil() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {

        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("HibernateMaven");
            System.out.println("Criando EntityManagerFactory.");
        }
        return emf;
    }

    public static EntityManager getManager() {
        if (em == null || !em.isOpen()) {
            em = emf.createEntityManager();
            System.out.println("Criando EntityManager.");
        }
        return em;
    }
    
    public static void closeEntityManagerFactory(){
    
    if(emf != null && emf.isOpen()){
    emf.close();
        System.out.println("Fechando EntityManagerFactory.");
    }
        
    }

}
