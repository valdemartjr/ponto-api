package com.pontointeligente.api.entities;

import java.beans.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
//import java.sql.Date;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.Table;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreUpdate;

import com.pontointeligente.api.enuns.PerfilEnum;


@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable{
	
	private static final long serialVersionUID = -7238084603069251273L;
	
	private long id;
	private String nome;
	private String email;
	private String senha;
	private String cpf;
	private BigDecimal valorHora;
	private Float qtHorasTrabalhoDia;
	private Float qtHorasAlmoco;
	private PerfilEnum Perfil;
	private Date dataCriacao;
	private Date dataAtualizacao;
	private Empresa empresa;
	private List<Lancamento> lancamentos;
	
	public Funcionario() {
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name= "nome",nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name= "email",nullable = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name= "senha",nullable = false)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Column(name= "cpf",nullable = false)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column(name= "valor_hora",nullable = true)
	public BigDecimal getValorHora() {
		return valorHora;
	}
	
	@Transient
	public Optional<BigDecimal> getValorHoraOpt(){
		return Optional.ofNullable(valorHora);
	}
	
	public void setValorHora(BigDecimal valorHora) {
		this.valorHora = valorHora;
	}

	@Column(name= "qtd_horas_trabalho_dia",nullable = true)
	public Float getQtHorasTrabalhoDia() {
		return qtHorasTrabalhoDia;
	}
	
	@Transient
	public Optional<Float> getQtHorasTrabalhoDiaOpt(){
		return Optional.ofNullable(qtHorasTrabalhoDia);
	}	

	public void setQtHorasTrabalhoDia(Float qtHorasTrabalhoDia) {
		this.qtHorasTrabalhoDia = qtHorasTrabalhoDia;
	}

	@Column(name= "qtd_horas_almoco",nullable = true)
	public Float getQtHorasAlmoco() {
		return qtHorasAlmoco;
	}

	public void setQtHorasAlmoco(Float qtHorasAlmoco) {
		this.qtHorasAlmoco = qtHorasAlmoco;
	}

	@Enumerated(EnumType.STRING)
	@Column(name= "perfil",nullable = true)
	public PerfilEnum getPerfil() {
		return Perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		Perfil = perfil;
	}

	@Column(name= "data_criacao",nullable = true)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Column(name= "data_atualizacao",nullable = true)
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = new Date();
	}
	
	@PreUpdate
	public void prePersist() {
		final Date atual = new Date();
		dataCriacao = atual;
		dataAtualizacao = atual;
	}
	
	@Override
	public String toString() {
		return  "Funcionario ["+
				  "id="+ id +"," +
				"]";
	}

	
}
