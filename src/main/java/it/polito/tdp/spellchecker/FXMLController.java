/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.Dictionary.Languages;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary dizionario;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnClear"
    private Button btnClear; // Value injected by FXMLLoader

    @FXML // fx:id="btnControllaOrtografia"
    private Button btnControllaOrtografia; // Value injected by FXMLLoader

    @FXML // fx:id="cmbSelezionaLingua"
    private ComboBox<Dictionary.Languages> cmbSelezionaLingua; // Value injected by FXMLLoader

    @FXML // fx:id="lblNumeroErrori"
    private Label lblNumeroErrori; // Value injected by FXMLLoader

    @FXML // fx:id="lblTempo"
    private Label lblTempo; // Value injected by FXMLLoader

    @FXML // fx:id="txtInput"
    private TextArea txtInput; // Value injected by FXMLLoader

    @FXML // fx:id="txtOutput"
    private TextArea txtOutput; // Value injected by FXMLLoader

    @FXML
    void doClear(ActionEvent event) {
    	
    	this.txtOutput.clear();
    	this.lblNumeroErrori.setText("");
    	this.lblTempo.setText("");
    	this.txtInput.clear();

    }
    
    
    /*
	 * allora questa merda di metodo deve:
	 * leggere il testo in input parola per parola
	 * ignorare la punteggiatura
	 * confrontare ogni parola dell'input con il dizionario
	 * se è contenuta deve creare una richword con la stringa uguale alla parola in input e il boolean true
	 * se non è contenuta deve creare una richword con la stringa uguale alla parola in input e il boolean false
	 * aggiungere tutte le string delle richword il cui boolean è false al testo in output
	 * contare le richword con il boolean false
	 * misurare quanto tempo ha impiegato
	 */
    
    @FXML
    void doControllaOrtografia(ActionEvent event) {
    	
    	this.txtOutput.clear();
    	
    	List <String> listaTestoInput = new LinkedList<String>();
    	
    	//leggo il testo in input e lo metto in una sola stringa
    	
    	String testoInput = this.txtInput.getText();
    	
    	if(testoInput.isEmpty()) {
    		System.out.println("Inserisci testo da controllare, scemo!");
    		return;
    	}
    	
    	//faccio la suddivisione del testo e ci sono molti modi per farla
    	
    	//modo1
    	StringTokenizer st = new StringTokenizer(testoInput, "\n[.,?\\/#!$%^&*;:{}=-_`~()] ");
    	while(st.hasMoreTokens()) {
    		listaTestoInput.add(st.nextToken());
    	}
    	
    	//modo 2 non mi piace e non lo faccio
    	
    	//ora devo prendere la misura del tempo e fare l'operazione vera e propria
    	
    	long tic = System.nanoTime();
    	
    	List <RichWord> listaOutput = dizionario.controllo(listaTestoInput);
    	
    	long toc = System.nanoTime();
    	
    	// ora bisogna contare gli errori (i false nella listaoutput) e i tempi e metterli nelle label
    	
    	int errori = 0;
    	for(RichWord r : listaOutput) {
    		if(!r.isCorretta()) {
    			this.txtOutput.appendText(r.getWord() + "\n");
    			errori ++;
    		}
    	}
    	
    	this.lblNumeroErrori.setText("Il tetso contiene " + errori + " errori.");
    	this.lblTempo.setText("Ci sono voluti " + ((toc-tic)/1E9) + " secondi."); 

    }

    @FXML
    
    //devo solo invocare il metodo loadDictionary() di Dictionary
    void doSelezionaLingua(ActionEvent event) {
    	
    	Dictionary.Languages lingua = this.cmbSelezionaLingua.getValue();
    	System.out.println("Ce l'abbiamo fatta porcoddioooo!!");
    	    	
    	try {
			this.dizionario.loadDictionary(lingua);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.txtOutput.setText("porcoddio manco questa volta sei riuscito a caricare sta merda di dizionario");
		}

    }
    
    //creo metodo nel controller per collegare il modello attraverso l'entry point
    public void setModel(Dictionary model) {
    	this.dizionario = model;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnControllaOrtografia != null : "fx:id=\"btnControllaOrtografia\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbSelezionaLingua != null : "fx:id=\"cmbSelezionaLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblNumeroErrori != null : "fx:id=\"lblNumeroErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTempo != null : "fx:id=\"lblTempo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'Scene.fxml'.";
        
        this.cmbSelezionaLingua.getItems().add(Languages.Italian);
        this.cmbSelezionaLingua.getItems().add(Languages.English);

    }

}

