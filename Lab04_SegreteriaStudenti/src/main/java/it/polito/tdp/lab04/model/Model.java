package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO CorsoDao;
	private StudenteDAO StudenteDao;
	
	public Model() {
		CorsoDao = new CorsoDAO();
		StudenteDao = new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi(){
		return CorsoDao.getTuttiICorsi();
	}

}
