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

public class CorsoDAO {
	
	Map<String,Corso> corsi = new HashMap<>();
	Map<String,Corso> studentiAlCorso;
	StudenteDAO studenteDao;
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public Map<String,Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				//System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				Corso corso = new Corso(codins,numeroCrediti,nome,periodoDidattico);
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				corsi.put(codins,corso);
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		
		final String sql = "SELECT * FROM iscrizione";
		
		studenteDao = new StudenteDAO();
		studentiAlCorso = new HashMap<>();
		
		Map<Integer,Studente> studenti = studenteDao.getTuttiIscritti();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String codins = rs.getString("codins");
				
				Corso c = corsi.get(codins);
				Studente s = studenti.get(matricola);
				
				if(studentiAlCorso.get(codins) == null) {
					studentiAlCorso.put(codins,c);
					studentiAlCorso.get(codins).aggiungiStudente(s);
				}else {
					studentiAlCorso.get(codins).aggiungiStudente(s);
				}
			}

			conn.close();
			
			return studentiAlCorso.get(corso.getCodins()).getStudentiCorso();
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		
		final String sql = "INSERT INTO	iscrizione(matricola,codins) VALUE (?, ?)";
		int rs;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,studente.getMatricola());
			st.setString(2,corso.getCodins());

			rs = st.executeUpdate();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		if(rs == 1) return true;
			else return false;
	}

}
