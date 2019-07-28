package br.com.consultemed.service;

import java.util.Collection;

import javax.inject.Inject;


import br.com.consultemed.dao.MedicoDaoImpl;
import br.com.consultemed.model.Medico;

public class MedicoBusiness {
	
	@Inject
	private MedicoDaoImpl dao;

	public MedicoBusiness() {
		this.dao = new MedicoDaoImpl();
	}
	
	public void save(Medico medico) throws Exception  {
		this.dao.save(medico);
	}
	
	public void update(Medico medico) throws Exception  {
		this.dao.update(medico);
	}
	
	public Collection<Medico> listAll() throws Exception {
		return this.dao.listAll();
	}
	
	public Medico findById(Long id) throws Exception {
		return this.dao.findById(id);
	}
	
	public void deleteById(Long id) throws Exception {
		this.dao.deleteById(id);
	}
	
	public int conut() throws Exception {
		return this.dao.countMedico();
	}

}
