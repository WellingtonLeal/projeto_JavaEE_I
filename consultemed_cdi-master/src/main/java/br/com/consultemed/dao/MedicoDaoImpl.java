package br.com.consultemed.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import br.com.consultemed.model.Medico;
import br.com.consultemed.utils.JPAUtils;

public class MedicoDaoImpl  implements IMedicoDao{
	
	
	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager factory = null;


	@Override
	public void save(Medico medico) throws Exception {
		this.factory = emf.createEntityManager();

		try {
			factory.getTransaction().begin();
			if (medico.getId() == null) {
				factory.persist(medico);
			} else {
				factory.merge(medico);
			}
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
			
		} finally {
			factory.close();
		}
		
	}

	@Override
	public Medico findById(Long id) throws Exception {
		this.factory = emf.createEntityManager();
		
		Medico medico = new Medico();
		try {
			medico = factory.find(Medico.class, id);
			return medico;

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}
		return null;
	}

	@Override
	public void deleteById(Long id) throws Exception {
		
		this.factory = emf.createEntityManager();
		Medico medico = new Medico();

		try {

			medico = factory.find(Medico.class, id);
			factory.getTransaction().begin();
			factory.remove(medico);
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}
		
	}

	@Override
	public void update(Medico medico) throws Exception {
		
		this.factory = emf.createEntityManager();

		try {
			factory.getTransaction().begin();
			factory.merge(medico);
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}
		
	}

	@Override
	public Collection<Medico> listAll() throws Exception {
		
		this.factory = emf.createEntityManager();
		List<Medico> medicos = new ArrayList<Medico>();
		try {
			factory.getTransaction().begin();
			TypedQuery<Medico> query = factory.createNamedQuery("Medico.findAll", Medico.class);
			medicos = query.getResultList();
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

		return medicos;
	}

	@Override
	public int countMedico() throws Exception {
		
		this.factory = emf.createEntityManager();
		int numMedico = 0;
		
		try {
			factory.getTransaction().begin();
			 numMedico = ((Number)this.factory.createNamedQuery("Medico.findAllCount").getSingleResult()).intValue();
		     System.out.println(numMedico);
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

		return numMedico;
	}
	
	

}
