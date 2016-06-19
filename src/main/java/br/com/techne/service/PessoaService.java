package br.com.techne.service;
 
import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


import br.com.techne.model.Pessoa;
import br.com.techne.repository.ConnectionFactory;
import br.com.techne.repository.PessoaRepository;
import br.com.techne.utils.FailureResponseBuilder;
 
@Path("/pessoa")
public class PessoaService {
 
	ConnectionFactory connectionFactory = new ConnectionFactory();
	
	/**
	 * Serviço responsael para inserir uma pessoa.
	 * @param pessoa entidade que sera inserida
	 * @return 201 created, ou 500 internal server error.
	 * @throws Exception
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(@Context UriInfo uriInfo, Pessoa pessoa) throws Exception {
		try {
			PessoaRepository pessoaRepository = new PessoaRepository(connectionFactory.getConnection());
			pessoaRepository.criar(pessoa);
			URI uri = uriInfo.getRequestUriBuilder().path(pessoa.getId()).build();
			return Response.created(uri).build();
		} catch (Exception e) {
			return new FailureResponseBuilder().toResponse(e);
		}
	}
	
	/**
	 * Serviço responsável em buscar uma determinada pessoa
	 * @param id da pessoa que deseja proucurar
	 * @return 200 com a listagem de pessoas, 404 se a pessoa não for encontrada ou 500 internal server error
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response proucurar(@PathParam("id") String id) throws Exception {
		try {
			PessoaRepository pessoaRepository = new PessoaRepository(connectionFactory.getConnection());
			Pessoa pessoa = pessoaRepository.proucurar(id);
			return Response.ok().entity(pessoa).build();
		} catch (Exception e) {
			return new FailureResponseBuilder().toResponse(e);
		}
	}
	
	/**
	 * Serviço responsável em listar todas as pessoas
	 * @return 200 com a listagem de pessoas ou 500 internal server error
	 * @throws Exception
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listar() throws Exception {
		try {
			PessoaRepository pessoaRepository = new PessoaRepository(connectionFactory.getConnection());
			List<Pessoa> list = pessoaRepository.listar();
			return Response.ok().entity(list).build();
		} catch (Exception e) {
			return new FailureResponseBuilder().toResponse(e);
		}
	}

	/**
	 * Serviço responsável em modificar uma pessoa
	 * @param id da pessoa que ser modificada
	 * @param pessoa entidade modificada
	 * @return 204, 404 se a entidade não for encontrada ou 500 internet server error
	 * @throws Exception
	 */
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modificar(@PathParam("id") String id, Pessoa pessoa) throws Exception {
		try {
			PessoaRepository pessoaRepository = new PessoaRepository(connectionFactory.getConnection());
			pessoaRepository.modificar(id, pessoa);
			return Response.noContent().build();
		} catch (Exception e) {
			return new FailureResponseBuilder().toResponse(e);
		}
	}

	/**
	 * Serviço responsável em deletar uma pessoa
	 * @param id da pessoa que ser excluida
	 * @return 204, 404 se a entidade não for encontrada ou 500 internet server error
	 * @throws Exception
	 */
	@DELETE
	@Path("/{id}")
	public Response deletar(@PathParam("id") String id) throws Exception {
		try {
			PessoaRepository pessoaRepository = new PessoaRepository(connectionFactory.getConnection());
			pessoaRepository.deletar(id);
			return Response.noContent().build();
		} catch (Exception e) {
			return new FailureResponseBuilder().toResponse(e);
		}
	}
 
}