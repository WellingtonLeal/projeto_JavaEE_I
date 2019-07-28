package br.com.consultemed.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name = "Paciente.findAllCount", query = "SELECT COUNT(c) FROM Paciente c"),
	@NamedQuery(name="Paciente.findAll", query="SELECT c FROM Paciente c")
})
@Entity
@Table (name="TB_PACIENTE")
public class Paciente extends AbstractEntity<Long>{
	

	private String nome;
	private String email;
	private String telefone;
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	

	
	
	

}
