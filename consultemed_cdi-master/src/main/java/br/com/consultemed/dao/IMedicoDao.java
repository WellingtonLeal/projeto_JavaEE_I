package br.com.consultemed.dao;

import java.util.Collection;


import br.com.consultemed.model.Medico;

public interface IMedicoDao extends GenericDao<Medico, Long>{
	
	public void save(Medico medico) throws Exception;
	
	public Medico findById(Long id) throws Exception;
	
	public void deleteById(Long id) throws Exception;
	
	public void update(Medico medico) throws Exception;
	
	public Collection<Medico> listAll() throws Exception;
	
	public int countMedico() throws Exception;
	

}
