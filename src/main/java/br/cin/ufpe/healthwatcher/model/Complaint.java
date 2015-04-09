package br.cin.ufpe.healthwatcher.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class Complaint implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int codigo;

	@Column(length = 100)
	private String solicitante;

	@Column(length = 200)
	private String descricao;

	@Column(length = 200)
	private String observacao;

	@Column(length = 100)
	private String email;

	@ManyToOne
	@JoinColumn(name = "login")
	private Employee atendente;

	// TODO Verificar se o enum vai ser utilizado aqui
	@Column(nullable = false)
	private int situacao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataParecer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataQueixa;

	private Address enderecoSolicitante;

	private long timestamp; // TODO para tratamento de concorrencia (scbs)
	
	public Complaint() {
	}
	
	// TODO: Verificar o construtor
	public Complaint(String solicitante, String descricao, String observacao, String email,
			Employee atendente, int situacao, Date dataParecer, Date dataQueixa,
			Address enderecoSolicitante, long timestamp) {

		//Numero fica vazio por enquanto - no Repositorio ele eh inicializado
		this.codigo = 0;
		this.solicitante = solicitante;
		this.descricao = descricao;
		this.observacao = observacao;
		this.email = email;
		this.atendente = atendente;
		this.situacao = situacao;
		this.dataParecer = dataParecer;
		this.dataQueixa = dataQueixa;
		this.enderecoSolicitante = enderecoSolicitante;
		this.timestamp = timestamp;
	}

	public Employee getAtendente() {
		return atendente;
	}

	public void setAtendente(Employee atendente) {
		this.atendente = atendente;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getDataParecer() {
		return dataParecer;
	}

	public void setDataParecer(Date dataParecer) {
		this.dataParecer = dataParecer;
	}

	public Date getDataQueixa() {
		return dataQueixa;
	}

	public void setDataQueixa(Date dataQueixa) {
		this.dataQueixa = dataQueixa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getEnderecoSolicitante() {
		return enderecoSolicitante;
	}

	public void setEnderecoSolicitante(Address enderecoSolicitante) {
		this.enderecoSolicitante = enderecoSolicitante;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	// TODO: entender onde esse metodo esta sendo utilizado
	public void incTimestamp() {
		this.timestamp = timestamp + 1;
	}
}
