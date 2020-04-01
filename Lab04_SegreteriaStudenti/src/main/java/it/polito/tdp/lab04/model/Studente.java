package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;

public class Studente {
	
	private Integer matricola;
	private String cognome;
	private String nome;
	private String CDS;
	private List<Corso> corsiFrequentati;
	
	/**
	 * @param matricola
	 * @param cognome
	 * @param nome
	 * @param cDS
	 */
	public Studente(Integer matricola, String cognome, String nome, String cDS) {
		super();
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		CDS = cDS;
		corsiFrequentati = new LinkedList<>();
	}

	/**
	 * @return the matricola
	 */
	public Integer getMatricola() {
		return matricola;
	}

	/**
	 * @param matricola the matricola to set
	 */
	public void setMatricola(Integer matricola) {
		this.matricola = matricola;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
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
	 * @return the cDS
	 */
	public String getCDS() {
		return CDS;
	}

	/**
	 * @param cDS the cDS to set
	 */
	public void setCDS(String cDS) {
		CDS = cDS;
	}

	/**
	 * @return the corsiFrequentati
	 */
	public List<Corso> getCorsiFrequentati() {
		return corsiFrequentati;
	}

	/**
	 * @param corsiFrequentati the corsiFrequentati to set
	 */
	public void aggiungiCorso(Corso corso) {
		this.corsiFrequentati.add(corso);
	}

	@Override
	public String toString() {
		return matricola +"     "+ cognome +"     "+ nome +"     "+ CDS + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricola == null) ? 0 : matricola.hashCode());
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
		Studente other = (Studente) obj;
		if (matricola == null) {
			if (other.matricola != null)
				return false;
		} else if (!matricola.equals(other.matricola))
			return false;
		return true;
	}

}
