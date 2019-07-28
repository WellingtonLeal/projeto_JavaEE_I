package br.com.consultemed.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import br.com.consultemed.model.Medico;
import br.com.consultemed.utils.JPAUtils;

public class MedicoDao implements Serializable {

	private static final long serialVersionUID = 1L;

	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager factory = emf.createEntityManager();

	
public void salvar(Medico medico) {
		
		this.factory.getTransaction().begin();
		this.factory.persist(medico);
		this.factory.getTransaction().commit();
		this.factory.close();
	}
	
	public void remover(Long idMedico) {
		
		this.factory.getTransaction().begin();
		this.factory.remove(buscaPorId(idMedico));
		this.factory.getTransaction().commit();
		this.factory.close();
	}
	
	public Medico buscaPorId(Long idMedico) {
		this.factory.getTransaction().begin();
		Medico medicoRemover = this.factory.find(Medico.class, idMedico);
		return medicoRemover;
	}
	
	public Medico buscaPorNome(String nome) {
		this.factory.getTransaction().begin();
		Query query = 
				this.factory.createQuery("SELECT c FROM Medico c WHERE c.nome = :nome");
		query.setParameter("nome", nome);
		Medico medico = (Medico) query.getSingleResult();
		return medico;
	}
	
	
	public Medico buscaPorEmail(String email) {
		this.factory.getTransaction().begin();
		Query query = 
				this.factory.createQuery("SELECT c FROM Medico c WHERE c.email = :email" );
		query.setParameter("email", email);
		Medico medico = (Medico) query.getSingleResult();
		return medico;
	}
	
	public void editar(Medico medico) {
		this.factory.getTransaction().begin();
		this.factory.merge(medico);
		this.factory.getTransaction().commit();
		this.factory.close();
	}
	
	public List<Medico> medico(){
		this.factory.getTransaction().begin();
		Query query = this.factory.createQuery("SELECT c FROM Medico c");
		@SuppressWarnings("unchecked")
		List<Medico> medicos = query.getResultList(); 
		for (Medico medico : medicos) {
			System.out.println(medico.getNomeMedico());
		}
		
		this.factory.close();
		return medicos;
	}
}
