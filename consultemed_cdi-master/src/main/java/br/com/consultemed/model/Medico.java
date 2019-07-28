package br.com.consultemed.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name = "Medico.findAllCount", query = "SELECT COUNT(c) FROM Medico c"),
	@NamedQuery(name="Medico.findAll", query="SELECT c FROM Medico c")
})
@Entity
@Table (name="TB_MEDICO")
public class Medico extends  AbstractEntity<Long>{
	
	private String crm;
	private String nomeMedico;
	private String telefone;
	
	
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getNomeMedico() {
		return nomeMedico;
	}
	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}
	
	

	

	
	
}
