package com.controle.remedios.suporte;


import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;


@Entity(name = "remedios")
@Table(name = "Remedios")
public class Remedio {
	@Id	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Enumerated(EnumType.STRING)
	private Via via;
	private String lote;
	private int quantidade;
	private LocalDate validade;
	@Enumerated(EnumType.STRING)
	private Laboratorio laboratorio;
	private Boolean ativo;



	public Remedio(Long id, String nome, Via via, String lote, int quantidade, LocalDate validade,
			Laboratorio laboratorio) {
		super();
		this.id = id;
		this.nome = nome;
		this.via = via;
		this.lote = lote;
		this.quantidade = quantidade;
		this.validade = validade;
		this.laboratorio = laboratorio;
		
	}


	public Remedio() {
		super();
	}


	public Remedio(DadosCadastroRemedio dados) {
		this.ativo = true;
		this.nome = dados.nome();
		this.via = dados.via();
		this.lote = dados.lote();
		this.quantidade = dados.quantidade();
		this.validade = dados.validade();
		this.laboratorio = dados.laboratorio();

	}


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
	public Via getVia() {
		return via;
	}
	public void setVia(Via via) {
		this.via = via;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}

	public Laboratorio getLaboratorio() {
		return laboratorio;
	}
	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}


	public int getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	public LocalDate getValidade() {
		return validade;
	}


	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}




	public Boolean getAtivo() {
		return ativo;
	}


	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, laboratorio, lote, nome, quantidade, validade, via);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Remedio other = (Remedio) obj;
		return Objects.equals(id, other.id) && laboratorio == other.laboratorio && Objects.equals(lote, other.lote)
				&& Objects.equals(nome, other.nome) && Objects.equals(quantidade, other.quantidade)
				&& Objects.equals(validade, other.validade) && via == other.via;
	}


	public void atualizarInformacoes(@Valid DadosAtualizarRemedio dados) {

		if(dados.nome() != null) {
			this.nome = dados.nome();
		}

		if(dados.via() != null) {
			this.via = dados.via();
		}

		if(dados.laboratorio() != null) {
			this.laboratorio = dados.laboratorio();
		}
	}


	public void inativar() {
		this.ativo = false;

	}

	public void reativar() {
		this.ativo = true;
	}


}
