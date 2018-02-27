package cnr.imaa.gaiaclimservice;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DbBinder {
    public Object getList(String query, Class classObj, String paramValue) {
        List<Object> result;

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cnr.imaa.gaiaclim.service.jpa");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        if (paramValue != null) {
            result = entityManager.createQuery(query, classObj)
                    .setParameter(1, paramValue)
                    .getResultList();
        }
        else {
            result = entityManager.createQuery(query, classObj).getResultList();
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        entityManagerFactory.close();

        return result;
    }

    public static void store(Object obj) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cnr.imaa.gaiaclim.service.jpa");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(obj);

        entityManager.getTransaction().commit();
        entityManager.close();

        entityManagerFactory.close();
    }

    public static void update(Object obj) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cnr.imaa.gaiaclim.service.jpa");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.merge(obj);

        entityManager.getTransaction().commit();
        entityManager.close();

        entityManagerFactory.close();
    }
}