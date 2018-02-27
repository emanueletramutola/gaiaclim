package cnr.imaa.dal;

import cnr.imaa.bll.MetadataXml;
import cnr.imaa.model.Station;
import cnr.imaa.model.StationForDB;
import cnr.imaa.model.wigos.ObjectFactory;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.w3c.dom.Document;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Parameter;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

class DbBinder {
    public Object getList(String query, Class classObj, String paramValue) {
        List<StationForDB> result;

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cnr.imaa.bll.jpa");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        result = entityManager.createQuery(query, classObj)
                .setParameter(1, paramValue)
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        entityManagerFactory.close();

        return result;
    }

    public static void store(Object obj) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cnr.imaa.bll.jpa");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(obj);

        entityManager.getTransaction().commit();
        entityManager.close();

        entityManagerFactory.close();
    }

    public static void update(Object obj) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cnr.imaa.bll.jpa");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.merge(obj);

        entityManager.getTransaction().commit();
        entityManager.close();

        entityManagerFactory.close();
    }
}