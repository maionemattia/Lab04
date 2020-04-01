package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;

public class Corso {
	
	private String codins;
	private int crediti;
	private String nome;
	private int pd;
	private List<Studente> studentiCorso;
	
	/**
	 * @param codins
	 * @param crediti
	 * @param nome
	 * @param pd
	 */
	public Corso(String codins, int crediti, String nome, int pd) {
		super();
		this.codins = codins;
		this.crediti = crediti;
		this.nome = nome;
		this.pd = pd;
		this.studentiCorso = new LinkedList<>();
	}
	
	/**
	 * @return the codins
	 */
	public String getCodins() {
		return codins;
	}
	/**
	 * @param codins the codins to set
	 */
	public void setCodins(String codins) {
		this.codins = codins;
	}
	/**
	 * @return the crediti
	 */
	public int getCrediti() {
		return crediti;
	}
	/**
	 * @param crediti the crediti to set
	 */
	public void setCrediti(int crediti) {
		this.crediti = crediti;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the pd
	 */
	public int getPd() {
		return pd;
	}
	/**
	 * @param pd the pd to set
	 */
	public void setPd(int pd) {
		this.pd = pd;
	}

	/**
	 * @return the studentiCorso
	 */
	public List<Studente> getStudentiCorso() {
		return studentiCorso;
	}

	/**
	 * @param studentiCorso the studentiCorso to set
	 */
	public void aggiungiStudente(Studente studente) {
		this.studentiCorso.add(studente);
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codins == null) ? 0 : codins.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		if (codins == null) {
			if (other.codins != null)
				return false;
		} else if (!codins.equals(other.codins))
			return false;
		return true;
	}

}


