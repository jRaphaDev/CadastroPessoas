package br.com.techne.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {

	public static EntityManagerFactory factory = null;
	private EntityManager em;
	
	/**
	 * The constructor method will create or use an already open connection.
	 */
	
	public ConnectionFactory(){
		
		if (factory != null){
			if (factory.isOpen()){
				em = factory.createEntityManager();
			}else{
				factory = Persistence.createEntityManagerFactory("cadastro");
				em = factory.createEntityManager();
			}
		}else{
			factory = Persistence.createEntityManagerFactory("cadastro");
			em = factory.createEntityManager();
		}
	
	}

	/**
	 * Method that will provide a connection.
	 * @return - EntityManager
	 */
	
	public EntityManager getConnection() {
		return em;
	}
	
	/**
	 * Method to close the connection. 
	 */
	
	public void close(){
		this.em.close();
		factory.close();
		factory = null;
	}
}
