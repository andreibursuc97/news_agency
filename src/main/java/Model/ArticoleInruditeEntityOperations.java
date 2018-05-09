package Model;

import ServerManagement.ClientHandler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ArticoleInruditeEntityOperations {

    EntityManager entityManager;
    EntityManagerFactory entityManagerFactory;
    ClientHandler clientHandler;

    public ArticoleInruditeEntityOperations(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public void insert(List<ArticolEntity> articolEntities) {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();

        Query query=entityManager.createNamedQuery("ArticolEntity.getMaxId");
        int id1=(Integer) query.getSingleResult();

        query=entityManager.createNamedQuery("ArticoleInruditeEntity.getMaxId");
        int idManage=(Integer) query.getSingleResult();
        ArticoleInruditeEntity articoleInruditeEntity;
        ArticoleInruditeEntity articoleInruditeEntity2;
        for(ArticolEntity art:articolEntities)
        {query = entityManager.createNamedQuery("ArticolEntity.getId");
        query.setParameter("titlu",art.getTitlu());
        List<Integer> ids=query.getResultList();
        idManage++;
        articoleInruditeEntity=new ArticoleInruditeEntity(idManage,id1,ids.get(0));
        //entityManager.flush();
        entityManager.getTransaction().begin();
        entityManager.persist(articoleInruditeEntity);
        entityManager.getTransaction().commit();
        //entityManager.getTransaction().commit();

        //articoleInruditeEntity2=new ArticoleInruditeEntity(ids.get(0),id1);
        //entityManager.getTransaction().begin();
        //entityManager.persist(articoleInruditeEntity2);
        idManage++;
        entityManager.getTransaction().begin();
        entityManager.flush();
        articoleInruditeEntity=new ArticoleInruditeEntity(idManage,ids.get(0),id1);
        entityManager.persist(articoleInruditeEntity);
        entityManager.flush();
        entityManager.getTransaction().commit();

        }


        entityManagerFactory.close();
        //HibernateUtil.shutdown();
    }

    public void arataArticoleInrudite(ArticolEntity articolEntity)
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();
        Query query=entityManager.createNamedQuery("ArticoleInruditeEntity.getArticoleInrudite");
        query.setParameter("id",articolEntity.getId());
        List<ArticolEntity> articolEntities=query.getResultList();
        entityManagerFactory.close();
        clientHandler.setArticoleInrudite(articolEntities);


    }

}
