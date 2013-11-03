package br.com.secoti.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
@SequenceGenerator(name="idUsuario", sequenceName="usuarios_id_seq",allocationSize=1,initialValue=1)
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/* attributes */
	@Id
	@GeneratedValue(generator="idUsuario", strategy=GenerationType.SEQUENCE)
	private Long id;
	@Column(name="nome")
	private String nome;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="usuario_id")
	private List<Conta> contas;

	/* getters and setters */
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Conta> getContas() {
		return contas;
	}
	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
}