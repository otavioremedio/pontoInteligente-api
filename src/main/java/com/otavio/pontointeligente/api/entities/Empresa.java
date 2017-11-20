package com.otavio.pontointeligente.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



//notação para entidade do banco
@Entity
//notação para indicar a tabela
@Table(name="empresa")
public class Empresa implements Serializable {

	private static final long serialVersionUID = -2311542391160606988L;
	
	private Long id;	
	private String razaoSocial;
	private String cnpj;
	private Date dataCriacao;
	private Date dataAtualizacao;
	private List<Funcionario> funcionarios;
	
	public Empresa() {
	}

	//notação para chave
	@Id 
	//notação para gerar valor automático
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name= "razao_social", nullable=false)
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	@Column(name= "cnpj", nullable=false)
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Column(name= "data_criacao", nullable=false)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Column(name= "data_atualizacao", nullable=false)
	public Date getDataAtualização() {
		return dataAtualizacao;
	}

	public void setDataAtualização(Date dataAtualização) {
		this.dataAtualizacao = dataAtualização;
	}

	//notação um para muitos
	//uma empresa tem muitos funcionários
	//fetch lazy para não trazer todos os objetos
	//cascade para o delete
	@OneToMany(mappedBy="empresa", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	//metodo que executa antes do update
	public void preUpdate() {
		dataAtualizacao = new Date();
	}
	
	//metodo que executa antes do insert
	public void prePersist() {
		final Date atual = new Date();
		dataCriacao = atual;
		dataAtualizacao = atual;
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", razaoSocial=" + razaoSocial + ", cnpj=" + cnpj + ", dataCriacao=" + dataCriacao
				+ ", dataAtualização=" + dataAtualizacao + ", funcionarios=" + funcionarios + "]";
	}
	
}
