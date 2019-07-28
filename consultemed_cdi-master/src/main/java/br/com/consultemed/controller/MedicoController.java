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

import br.com.consultemed.model.Medico;
import br.com.consultemed.service.MedicoBusiness;
import br.com.consultemed.utils.Constantes;

/**
 * Servlet implementation class MedicoController
 */

@WebServlet("/admin/medicos")
public class MedicoController extends HttpServlet {
	

	private static final long serialVersionUID = 1L;

	private static final String ID_USUARIO = "id";
	
	private static final String NOME_CRM = "crm";

	private static final String TELEFONE_USUARIO = "telefone";

	private static final String NOME_USUARIO = "nome";
	
	@Inject
	private MedicoBusiness business;
	
    public MedicoController() {
    	this.business = new MedicoBusiness();
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
		RequestDispatcher rd = request.getRequestDispatcher(Constantes.MEDICOS);
		Collection<Medico> medicos = this.business.listAll();
		request.setAttribute("medicos", medicos);
		rd.forward(request, response);
		
	}

	/**
	 * Prepara formulário para cadastro de um novo medico
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void novo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(Constantes.ADD_MEDICOS);
		rd.forward(request, response);
	}

	/**
	 * Cadastro de um novo medico
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomeMedico = request.getParameter(NOME_USUARIO);
		String crm = request.getParameter(NOME_CRM);
		String telefone = request.getParameter(TELEFONE_USUARIO);
		String id = request.getParameter(ID_USUARIO);
		
			
		Medico medico = new Medico();
		medico.setNomeMedico(nomeMedico);
		medico.setCrm(crm);
		medico.setTelefone(telefone);
		
		if(id != "") {
			medico.setId(Long.parseLong(id));
			request.setAttribute("editado", Constantes.MEDICO + " " + nomeMedico + Constantes.MEDICO_EDITADO);
		}else {
			request.setAttribute("cadastro", Constantes.MEDICO + " "+ nomeMedico + Constantes.MEDICO_SUCESSO);			
		}
		try {
			this.business.save(medico);
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
	 * Metodo que remove um medico do banco de dados
	 * 
	 * @param request
	 * @param response
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		
		this.business.deleteById(Long.parseLong(request.getParameter(ID_USUARIO)));
		request.setAttribute("remover", Constantes.MEDICO + Constantes.MEDICO_REMOVIDO);
		
		try {
			list(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Metodo que edita um medico
	 * 
	 * @param request
	 * @param response
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	private void editar(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {

		Medico medico = this.business.findById(Long.parseLong(request.getParameter(ID_USUARIO)));
		RequestDispatcher rd = request.getRequestDispatcher(Constantes.ADD_MEDICOS);
		request.setAttribute("medico", medico);
		rd.forward(request, response);
	}
}
