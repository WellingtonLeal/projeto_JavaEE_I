package br.com.consultemed.controller;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.consultemed.model.Paciente;
import br.com.consultemed.service.PacienteBusiness;
import br.com.consultemed.utils.Constantes;

/**
 * Servlet implementation class PacienteController
 */
@WebServlet("/admin/pacientes")
public class PacienteController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String ID_USUARIO = "id";
	
	private static final String NOME_EMAIL = "email";

	private static final String TELEFONE_USUARIO = "telefone";

	private static final String NOME_USUARIO = "nome";
	
	@Inject
	private PacienteBusiness business;
	
    public PacienteController() {
    	this.business = new PacienteBusiness();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter(Constantes.ACTION);

		try {
			switch (action) {
			case Constantes.NOVO:
				novo(request, response);
				break;
			case Constantes.DELETE:
				delete(request, response);
				break;
			case Constantes.EDITAR:
				editar(request, response);
				break;
			case Constantes.LISTAR :
				list(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	
	private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RequestDispatcher rd = request.getRequestDispatcher(Constantes.PACIENTES);
		Collection<Paciente> pacientes = this.business.listAll();
		request.setAttribute("pacientes", pacientes);
		rd.forward(request, response);
		
	}

	/**
	 * Prepara formulário para cadastro de um novo paciente
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void novo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(Constantes.ADD_PACIENTES);
		rd.forward(request, response);
	}

	/**
	 * Cadastro de um novo paciente
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter(NOME_USUARIO);
		String email = request.getParameter(NOME_EMAIL);
		String telefone = request.getParameter(TELEFONE_USUARIO);
		String id = request.getParameter(ID_USUARIO);
		
			
		Paciente paciente = new Paciente();
		paciente.setNome(nome);
		paciente.setEmail(email);
		paciente.setTelefone(telefone);
		
		if(id != "") {
			paciente.setId(Long.parseLong(id));
			request.setAttribute("editado", Constantes.PACIENTE + " " + nome + Constantes.PACIENTE_EDITADO);
		}else {
			request.setAttribute("cadastro", Constantes.PACIENTE + " "+ nome + Constantes.PACIENTE_SUCESSO);			
		}
		try {
			this.business.save(paciente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			list(request, response);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

	/**
	 * Metodo que remove um paciente do banco de dados
	 * 
	 * @param request
	 * @param response
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		
		this.business.deleteById(Long.parseLong(request.getParameter(ID_USUARIO)));
		request.setAttribute("remover", Constantes.PACIENTE + Constantes.PACIENTE_REMOVIDO);
		
		try {
			list(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Metodo que edita um paciente
	 * 
	 * @param request
	 * @param response
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	private void editar(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {

		Paciente paciente = this.business.findById(Long.parseLong(request.getParameter(ID_USUARIO)));
		RequestDispatcher rd = request.getRequestDispatcher(Constantes.ADD_PACIENTES);
		request.setAttribute("paciente", paciente);
		rd.forward(request, response);
	}

}
