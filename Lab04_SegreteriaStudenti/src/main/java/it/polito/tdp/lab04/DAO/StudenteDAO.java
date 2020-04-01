package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	Map<Integer, Studente> studenti = new HashMap<Integer, Studente>();
	Map<Integer,Studente> corsiFrequentati;
	CorsoDAO corsoDao;
	
	public void popolaIscritti() {

		final String sql = "SELECT * FROM studente";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");

				//System.out.println(matricola + " " + cognome + " " + nome + " " + CDS);

				// Crea un nuovo JAVA Bean Corso
				Studente studente = new Studente(matricola,cognome,nome,CDS);
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				studenti.put(matricola,studente);
			}

			conn.close();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public void popolaCorsiFrequentati(){
		
		final String sql = "SELECT * FROM iscrizione";
		
		corsoDao = new CorsoDAO();
		corsiFrequentati = new HashMap<>();
		
		Map<String,Corso> corsi = corsoDao.getTuttiICorsi();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String codins = rs.getString("codins");
				
				Corso c = corsi.get(codins);
				Studente s = studenti.get(matricola);
				
				if(corsiFrequentati.get(matricola) == null) {
					corsiFrequentati.put(matricola, s);
					corsiFrequentati.get(matricola).aggiungiCorso(c);
				}else {
					corsiFrequentati.get(matricola).aggiungiCorso(c);
				}
			}

			conn.close();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		
	}
	
	public List<Corso> getCorsiFrequentati(Integer matricola) {
		return corsiFrequentati.get(matricola).getCorsiFrequentati();
	}
	
	public Map<Integer, Studente> getTuttiIscritti(){
		return studenti;
	}

	public Studente getIscritto(Integer matricola) {
		return studenti.get(matricola);
	}

	public boolean verificaIscrizione(int matricola, Corso corso) {
		if(corsiFrequentati.get(matricola).getCorsiFrequentati().contains(corso)) return true;
			else return false;
	}

}
