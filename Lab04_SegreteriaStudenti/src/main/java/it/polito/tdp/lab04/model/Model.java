package it.polito.tdp.lab04.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO CorsoDao;
	private StudenteDAO StudenteDao;
	
	public Model() {
		CorsoDao = new CorsoDAO();
		StudenteDao = new StudenteDAO();
		CorsoDao.popolaTuttiICorsi();
		StudenteDao.popolaIscritti();
		CorsoDao.popolaStudentiIscrittiAiCorsi();
		StudenteDao.popolaCorsiFrequentati();
		
	}
	
	public Map<String,Corso> getTuttiICorsi(){
		return CorsoDao.getTuttiICorsi();
	}
	
	public Map<Integer, Studente> getTuttiIscritti(){
		return StudenteDao.getTuttiIscritti();
	}
	
	public List<Corso> getCorsiFrequentati(Integer m){
		return StudenteDao.getCorsiFrequentati(m);
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso){
		return CorsoDao.getStudentiIscrittiAlCorso(corso);
	}
	
	public Studente getIscritto(Integer matricola) {
		return StudenteDao.getIscritto(matricola);
	}

	public boolean verificaIscrizione(int matricola, Corso corso) {
		return StudenteDao.verificaIscrizione(matricola,corso);
	}

	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		return CorsoDao.inscriviStudenteACorso(studente, corso);
		
	}
	
}
