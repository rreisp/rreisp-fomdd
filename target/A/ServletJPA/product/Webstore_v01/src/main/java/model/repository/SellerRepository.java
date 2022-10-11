package model.repository;


import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import model.*;

public class SellerRepository implements ServletContextListener {

    @PersistenceUnit(unitName = "store-pu")
    private EntityManagerFactory emf;

    private EntityManager em;

    public SellerRepository() {
		emf = (EntityManagerFactory) Persistence.createEntityManagerFactory("store-pu");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
		if (emf.isOpen()) {
	   		emf.close();
		}
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		context.setAttribute("sellerRepository", this);
    }

    public final EntityManager entityManager() {
		if (em == null || !em.isOpen()) {
		    em = emf.createEntityManager();
		}
		return em;
    }

    public void persistOrMerge(Serializable entity, Serializable id) {
		em = entityManager();

		if (entity == null) {
		    throw new IllegalArgumentException("entity");
		}
		try {
	    	em.getTransaction().begin();
	    	if (id == null) {
				em.persist(entity);
	    	} else {
				em.merge(entity);
	   		}
	    	em.getTransaction().commit();
		} finally {
	    	em.close();
		}
    }

    public Seller findSellerById(Long id) {
		em = entityManager();
		return em.find(Seller.class, id);
    }

	public List findAllSellers() {
		em = entityManager();
		Query q = em.createQuery("select obj from Seller obj");
		return q.getResultList();
	}

	public Seller newSeller() {
		Seller object = new Seller();
		
		// TODO: This part you'll need to write by hand. Sorry!
		// Set the attributes of object here
		// Insert one or more parameters if necessary
		
		persistOrMerge(object, object.getId());
		return object;
	}
    
}
