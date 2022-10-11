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

public class ProductRepository implements ServletContextListener {

    @PersistenceUnit(unitName = "store-pu")
    private EntityManagerFactory emf;

    private EntityManager em;

    public ProductRepository() {
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
		context.setAttribute("productRepository", this);
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

    public Product findProductById(Long id) {
		em = entityManager();
		return em.find(Product.class, id);
    }

	public List findAllProducts() {
		em = entityManager();
		Query q = em.createQuery("select obj from Product obj");
		return q.getResultList();
	}

	public Product newProduct() {
		Product object = new Product();
		
		// TODO: This part you'll need to write by hand. Sorry!
		// Set the attributes of object here
		// Insert one or more parameters if necessary
		
		persistOrMerge(object, object.getId());
		return object;
	}
    
}
