package Model;

import ServerManagement.ClientHandler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.*;
import java.util.List;

public class JurnalistOperations {

    EntityManager entityManager;
    EntityManagerFactory entityManagerFactory;

    public void logare(JurnalistEntity jurnalistEntity, ClientHandler clientHandler) {
        try {
            Encrypt code = new Encrypt();

            entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
            entityManager = entityManagerFactory.createEntityManager();
            Query query = entityManager.createNamedQuery("Jurnalist.findById");

            query.setParameter("username", jurnalistEntity.getUsername());
            List<JurnalistEntity> jurnalistEntityList = query.getResultList();
            if (jurnalistEntityList.isEmpty()) {
                clientHandler.sendCommand("UsernameGresitJurnalist");
                return;
            }
            JurnalistEntity jurnalist = (JurnalistEntity) query.getSingleResult();
            //admin=entityManager.find(AdminEntity);
            if (jurnalist == null) {
                clientHandler.sendCommand("UsernameGresitJurnalist");
                return;
            }
            System.out.println(code.codeToString(jurnalist.getParola()));
            System.out.println(code.codeToString(jurnalistEntity.getParola()));
            if (!code.codeToString(jurnalist.getParola()).equals(code.codeToString(jurnalistEntity.getParola()))) {
                clientHandler.sendCommand("ParolaGresitaJurnalist");
                return;
            }
            Byte byte1=1;
            if (jurnalist.getLogat() == byte1) {
                clientHandler.sendCommand("JurnalistDejaLogat");
                return;
            }
            jurnalist.setLogat(byte1);
            entityManager.getTransaction().begin();
            entityManager.merge(jurnalist);
            entityManager.getTransaction().commit();
            clientHandler.sendCommand("LogareReusitaJurnalist");

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            entityManagerFactory.close();
        }
        //  Query query = entityManager.createQuery("SELECT id FROM AdminEntity id where username= :?);

    }

    public void delogare(JurnalistEntity jurnalistEntity)
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("Jurnalist.findById");
        query.setParameter("username", jurnalistEntity.getUsername());
        JurnalistEntity jurnalist = (JurnalistEntity) query.getSingleResult();
        Byte byte1=0;
        jurnalist.setLogat(byte1);
        entityManager.getTransaction().begin();
        entityManager.merge(jurnalist);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    }
}
