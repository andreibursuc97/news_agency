package Model;

import ServerManagement.ClientHandler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class ArticolOperations {

    EntityManager entityManager;
    EntityManagerFactory entityManagerFactory;
    ClientHandler clientHandler;

    public ArticolOperations(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public void insert(ArticolEntity articolEntity) {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(articolEntity);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
        //HibernateUtil.shutdown();
    }

    public void update(ArticolEntity articolEntity) {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.merge(articolEntity);
        //entityManager.persist(articolEntity);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
        //HibernateUtil.shutdown();
    }

    public void arataArticole()
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();
        //Query query = entityManager.createQuery(afisareSpectacoleString);
        String[] dateTabel;
        dateTabel= new String[40];
        ArrayList<ArticolEntity> elemente=new ArrayList<>();
        //query.setParameter("username", username);
        List<ArticolEntity> articolEntities= entityManager.createNamedQuery("ArticolEntity.afiseazaArticole").getResultList();

        for(ArticolEntity articolEntity:articolEntities)
        {
            elemente.add(articolEntity);
        }
        entityManagerFactory.close();

        clientHandler.setArticolEntities(elemente);
    }
}
