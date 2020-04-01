package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> comboBox;

    @FXML
    private Button btnCercaIscritti;
    
    @FXML
    private Button btnCompleta;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnReset;

    @FXML
    void cercaCorsi(ActionEvent event) {
    	txtRisultato.clear();
    	int matricola;
    	List<Corso> corsi;
    	try {
    	matricola = Integer.parseInt(txtMatricola.getText());
    	}catch(NumberFormatException e) {
    		txtRisultato.setText("Devi inserire un numero di matricola valido");
    		return;
    	}
    	try {
    	corsi = this.model.getCorsiFrequentati(matricola);
    	}catch(NullPointerException e){
    		txtRisultato.setText("Hai inserito una matricola non presente nel sistema");
    		return;
    	}
    	if(comboBox.getValue() == null) {
	    	for(Corso c : corsi) {
	    		txtRisultato.appendText(c.getCodins()+"\n");
	    	}
    	}else {
    		boolean flag = this.model.verificaIscrizione(matricola,comboBox.getValue());
    		if(flag) txtRisultato.setText("Studente iscritto al corso!");
    			else txtRisultato.setText("Studente non iscritto al corso!"); 
    	}
    }

    @FXML
    void cercaIscritti(ActionEvent event) {
    	txtRisultato.clear();
    	
    	try {
    	List<Studente> studentiIscritti = this.model.getStudentiIscrittiAlCorso(comboBox.getValue());
    	
    	for(Studente s : studentiIscritti) {
    	txtRisultato.appendText(s.toString());
    	}
    	}catch(NullPointerException e) {
    		txtRisultato.setText("Seleziona un corso");
    		return;
    	}

    }
    
    @FXML
    void completa(ActionEvent event) {
    	
    	int matricola = Integer.parseInt(txtMatricola.getText());

    	try {
    	Studente s = this.model.getIscritto(matricola);
    	txtNome.appendText(s.getNome());
    	txtCognome.appendText(s.getCognome());
    	}catch(NullPointerException e) {
    		txtRisultato.setText("Hai inserito una matricola non presente nel sistema");
    		return;
    	}
    }

    @FXML
    void iscrivi(ActionEvent event) {
    	
    	int matricola;
    	
    	try {
        	matricola = Integer.parseInt(txtMatricola.getText());
        	}catch(NumberFormatException e) {
        		txtRisultato.setText("Devi inserire un numero di matricola valido");
        		return;
        	}
    	
    	boolean flag = this.model.verificaIscrizione(matricola,comboBox.getValue());
		if(flag) txtRisultato.setText("Studente già iscritto al corso!");
			else {
				this.model.inscriviStudenteACorso(this.model.getIscritto(matricola),comboBox.getValue());
				txtRisultato.setText("Hai eseguito l'iscrizione"); 
			}

    }

    @FXML
    void reset(ActionEvent event) {
    	
    	txtRisultato.clear();
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	comboBox.disarm(); // Definire reset comboBOX

    }

    @FXML
    void initialize() {
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCompleta != null : "fx:id=\"btnCompleta\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }

    public void setModel(Model model) {
    	this.model = model;
    	comboBox.getItems().addAll((this.model.getTuttiICorsi().values()));
    }
}


