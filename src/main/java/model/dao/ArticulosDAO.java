package model.dao;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.hibernate.criterion.Example.create;

@Repository("articulosDAO")
@Scope("prototype")
public class ArticulosDAO {
    private static final Logger log = LoggerFactory.getLogger(ArticulosDAO.class);

    private SessionFactory sessionFactory;

    //Constructor
    public ArticulosDAO() {
    }

    @Autowired
    public ArticulosDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFacory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    protected void initDao() {
    }

    public void save(Articulos instance) {
        log.debug("saving Articules");
        try {
            getCurrentSession().save(instance);
            log.debug("Articule saved successfuly");
        } catch (RuntimeException ex) {
            log.error("save failed", ex);
            throw ex;
        }
    }

    public void delete(Articulos instance) {
        log.debug("deleting Articules");
        try {
            getCurrentSession().delete(instance);
            log.debug("deleted successfuly");
        } catch (RuntimeException ex) {
            log.error("deleting failed", ex);
            throw ex;
        }
    }

    public Articulos findById(Integer id) {
        log.debug("Looking for id: " + id);
        try {
            return (Articulos) getCurrentSession().get(
                    "model.dao.Articulos", id);
        } catch (RuntimeException ex) {
            log.error("find by id; " + id + " failed");
            throw ex;
        }
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    public List<Articulos> findByExample(Articulos instance) {
        log.debug("Looking by examples");
        try {
            List<Articulos> results = (List<Articulos>) getCurrentSession()
                    .createCriteria("model.dao.Articulos")
                    .add(create(instance)).list();
            log.debug("Looking by examples successfull");
            return results;
        } catch (RuntimeException ex) {
            log.error("Looking by example failed", ex);
            throw ex;
        }
    }

    @SuppressWarnings({"deprecation", "JpaQlInspection", "unchecked"})
    public List<Articulos> findAll() {
        log.debug("Getting all Articules");
        try {
            String queryString = "from Articulos";
            Query<Articulos> queryObject = getCurrentSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException ex) {
            log.error("Getting all Articules failed", ex);
            throw ex;
        }
    }

    public Articulos merge(Articulos instanceToMerge) {
        log.debug("Merging Articules");
        try {
            Articulos result = (Articulos) getCurrentSession()
                    .merge(instanceToMerge);
            log.debug("Merge Articules successfuly");
            return result;
        } catch (RuntimeException ex) {
            log.error("Merging Articules failed", ex);
            throw ex;
        }
    }

    public void attachDirty(Articulos instance) {
        log.debug("Attaching Articules dirty way");
        try {
            getCurrentSession().saveOrUpdate(instance);
            log.debug("Attached successfuly");
        } catch (RuntimeException ex) {
            log.error("Attaching failed", ex);
            throw ex;
        }
    }

    public void attachClean(Articulos instance) {
        log.debug("Attaching Articules clean way");
        try {
            getCurrentSession().buildLockRequest(LockOptions.NONE)
                    .lock(instance);
            log.debug("Attached successfuly");
        } catch (RuntimeException ex) {
            log.error("Attaching failed", ex);
            throw ex;
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static ArticulosDAO getFromApplicationContext(ApplicationContext ctx) {
        return (ArticulosDAO) ctx.getBean("ArticulosDAO");
    }

}
