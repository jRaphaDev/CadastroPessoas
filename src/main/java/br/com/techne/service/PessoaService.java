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
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response proucurar(@PathParam("id") String id) throws Exception {
		try {
			PessoaRepository pessoaRepository = new PessoaRepository(connectionFactory.getConnection());
			Pessoa pessoa = pessoaRepository.proucurar(id);
			return Response.status(200).entity(pessoa).build();
		} catch (Exception e) {
			return new FailureResponseBuilder().toResponse(e);
		}
	}
	
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