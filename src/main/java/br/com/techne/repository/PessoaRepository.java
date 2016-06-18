package br.com.techne.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.techne.model.Pessoa;
import br.com.techne.utils.StatusException;

public class PessoaRepository {

	EntityManager entityManager;
	private static final Logger LOGGER = LoggerFactory.getLogger(PessoaRepository.class);

	public PessoaRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void criar(Pessoa pessoa) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(pessoa);
			entityManager.getTransaction().commit();
			LOGGER.debug("Pessoa criada com sucesso");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
	}

	public Pessoa proucurar(String id) throws Exception {
		try {
			entityManager.getTransaction().begin();
			Pessoa pessoa = entityManager.find(Pessoa.class, id);
			entityManager.getTransaction().commit();
			if (pessoa == null) {
				LOGGER.error("O id : "+id+" não foi encontrado");
				throw new StatusException(Status.NOT_FOUND.getStatusCode() ,"O id : "+id+" não foi encontrado");
			}
			LOGGER.debug("Pessoa encontrada com sucesso");
			return pessoa;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
	}

	
	@SuppressWarnings("unchecked")
	public List<Pessoa> listar() throws Exception  {
		try {
			
			List<Pessoa> list = new ArrayList<Pessoa>();
			
			entityManager.getTransaction().begin();
			Query query = entityManager.createQuery("FROM Pessoa p ORDER BY p.nome ASC");
			entityManager.getTransaction().commit();
			
			list = query.getResultList();
			
			LOGGER.debug("Pessoas listadas com sucesso");
			return list;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
	}

	public void modificar(String id, Pessoa pessoa) throws Exception {
		try {
			proucurar(id);
			entityManager.getTransaction().begin();
			entityManager.merge(pessoa);
			entityManager.getTransaction().commit();
			LOGGER.debug("Pessoa modificada com sucesso");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}	
	}

	public void deletar(String id) throws Exception {
		try {
			Pessoa pessoa = proucurar(id);
			entityManager.getTransaction().begin();
			entityManager.remove(pessoa);
			entityManager.getTransaction().commit();
			LOGGER.debug("Pessoa removida com sucesso");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
	}
}
