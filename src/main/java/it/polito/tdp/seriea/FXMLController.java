package it.polito.tdp.seriea;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.seriea.model.Model;
import it.polito.tdp.seriea.model.Squadre;
import it.polito.tdp.seriea.model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

//controller turno A --> switchare al branch master_turnoB o master_turnoC per turno B o C

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Team> boxSquadra;

    @FXML
    private ChoiceBox<?> boxStagione;

    @FXML
    private Button btnCalcolaConnessioniSquadra;

    @FXML
    private Button btnSimulaTifosi;

    @FXML
    private Button btnAnalizzaSquadre;

    @FXML
    private TextArea txtResult;

    @FXML
    void doAnalizzaSquadre(ActionEvent event) {
    	txtResult.clear();
    	
    	this.model.creaGrafo();
    	txtResult.appendText("Grafo creato\n");
    	txtResult.appendText("#Vertici: "+ this.model.nVertici()+"\n");
    	txtResult.appendText("#Archi: "+ this.model.nArchi()+"\n");

    	this.boxSquadra.getItems().addAll(this.model.getTeam());
    }

    @FXML
    void doCalcolaConnessioniSquadra(ActionEvent event) {
    	txtResult.clear();
    	Team t= this.boxSquadra.getValue();

    	if(t==null) {
    		txtResult.appendText("Devi selezionare una squadra");
    	}
    	
    	List<Squadre> vicini= this.model.getVicini(t);
    	
    	txtResult.appendText("Squadre avversarie di"+ t+"\n");
    	for(Squadre s: vicini) {
    		txtResult.appendText(s.toString()+"\n");
    	}
    }

    @FXML
    void doSimulaTifosi(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert boxSquadra != null : "fx:id=\"boxSquadra\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert boxStagione != null : "fx:id=\"boxStagione\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnCalcolaConnessioniSquadra != null : "fx:id=\"btnCalcolaConnessioniSquadra\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnSimulaTifosi != null : "fx:id=\"btnSimulaTifosi\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnAnalizzaSquadre != null : "fx:id=\"btnAnalizzaSquadre\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SerieA.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}