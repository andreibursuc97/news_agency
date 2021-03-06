package Model;

import ServerManagement.ClientHandler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.*;
import java.util.List;

public class AdminOperations {

    EntityManager entityManager;
    EntityManagerFactory entityManagerFactory;
    //ClientHandler clientHandler;

    public void insert(AdminEntity adminEntity) {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(adminEntity);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
        //HibernateUtil.shutdown();
    }

    public void logare(AdminEntity adminEntity, ClientHandler clientHandler) {
        try {
            Encrypt code = new Encrypt();

            entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
            entityManager = entityManagerFactory.createEntityManager();
            Query query = entityManager.createNamedQuery("Admin.findById");

            query.setParameter("username", adminEntity.getUsername());
            List<AdminEntity> adminEntityList = query.getResultList();
            if (adminEntityList.isEmpty()) {
                clientHandler.sendCommand("UsernameGresit");
                return;
            }
            AdminEntity admin = (AdminEntity) query.getSingleResult();
            //admin=entityManager.find(AdminEntity);
            if (admin == null) {
                clientHandler.sendCommand("UsernameGresit");
                return;
            }
            System.out.println(code.codeToString(admin.getParola()));
            System.out.println(code.codeToString(adminEntity.getParola()));
            if (!code.codeToString(admin.getParola()).equals(code.codeToString(adminEntity.getParola()))) {
                clientHandler.sendCommand("ParolaGresita");
                return;
            }
            Byte byte1=1;
            if (admin.getLogat() == byte1) {
                clientHandler.sendCommand("DejaLogat");
                return;
            }
            admin.setLogat(byte1);
            entityManager.getTransaction().begin();
            entityManager.merge(admin);
            entityManager.getTransaction().commit();
            clientHandler.sendCommand("LogareReusita");

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            entityManagerFactory.close();
        }
        //  Query query = entityManager.createQuery("SELECT id FROM AdminEntity id where username= :?);

    }

    public void delogare(AdminEntity adminEntity)
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("Admin.findById");
        query.setParameter("username", adminEntity.getUsername());
        AdminEntity admin = (AdminEntity) query.getSingleResult();
        Byte byte1=0;
        admin.setLogat(byte1);
        entityManager.getTransaction().begin();
        entityManager.merge(admin);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    }

    public void adaugaJurnalist(JurnalistEntity jurnalistEntity,ClientHandler clientHandler) {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createNamedQuery("Jurnalist.findById");

        query.setParameter("username", jurnalistEntity.getUsername());

        List<AdminEntity> adminEntityList = query.getResultList();
        if (!adminEntityList.isEmpty()) {
            clientHandler.sendCommand("ExistaDejaJurnalist");
            return;
        }
        entityManager.getTransaction().begin();
        entityManager.persist(jurnalistEntity);
        entityManager.getTransaction().commit();
        clientHandler.sendCommand("jurnalistAdaugat");
        entityManagerFactory.close();
    }


}
