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

/**
 * 
 * @author Raphael Freitas
 * @version 0.0.1
 */
public class PessoaRepository {

	EntityManager entityManager;
	private static final Logger LOGGER = LoggerFactory.getLogger(PessoaRepository.class);

	
	/**
	 * O construtor é o responsavel para fazer a inversão de controle.
	 * @param entityManager
	 */
	//Não foi uitlizado nenhum framework pois não foi pedido pelo recrutador mas obetenho conhecimentos com Spring. 
	public PessoaRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	/**
	 * Esse método é responsável para inserir uma nova pessoa no banco de dados. 
	 * @param pessoa Entidade que sera inserida
	 * @throws Exception
	 */
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

	/**
	 * 
	 * @param id indentificação da pessoa que se deseja proucurar.
	 * @return pessoa encontrada de acordo com o id.
	 * @throws Exception
	 */
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

	/**
	 * Esse método é responsavel em listar todas as pessoas cadastradas no banco de dados.
	 * @return uma listagem com todos as pessoas
	 * @throws Exception
	 */
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

	/**
	 * Esse método é responsavel em modificar uma pessoa
	 * @param id, Id da pessoa que sera modificada
	 * @param pessoa entidade modificada.
	 * @throws Exception
	 */
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

	/**
	 * Método responsavel em deletar uma pessoa especifica.
	 * @param id da pessoa que será excluida.
	 * @throws Exception
	 */
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
