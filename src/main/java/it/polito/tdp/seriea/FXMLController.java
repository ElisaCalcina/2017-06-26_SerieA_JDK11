package it.polito.tdp.seriea;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.seriea.model.Model;
import it.polito.tdp.seriea.model.Partite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

//controller turno C --> switchare al branch master_turnoA o master_turnoB per turno A o B

public class FXMLController {
	
	private Model model;

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Integer> boxNumeroDiGoal;

    @FXML
    private ChoiceBox<?> boxSquadra1;

    @FXML
    private Button btnCalcolaConnessioniGoal;

    @FXML
    private Button btnAnalizzaRisultati;

    @FXML
    private ChoiceBox<?> boxSquadra2;

    @FXML
    private Button btnSimulaStagioni;

    @FXML
    private TextArea txtResult;

    @FXML
    void doAnalizzaRisultati(ActionEvent event) {
    	txtResult.clear();
    	
    	this.model.creaGrafo();
    	txtResult.appendText("Grafo creato\n");
    	txtResult.appendText("#vertici: "+ this.model.nVertici());
    	txtResult.appendText("#archi: "+ this.model.nArchi());

    	this.boxNumeroDiGoal.getItems().addAll(this.model.getVertici());

    }

    @FXML
    void doCalcolaConnessioniGoal(ActionEvent event) {
    	txtResult.clear();
    	
    	Integer goal= this.boxNumeroDiGoal.getValue();
    	
    	if(goal==null) {
    		txtResult.appendText("Devi selezionare un numero");
    	}
    	
    	List<Partite> res= this.model.getRisultati(goal);
    	for(Partite p: res) {
    		txtResult.appendText(p.toString()+"\n");
    	}

    }

    @FXML
    void doSimulaStagioni(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert boxNumeroDiGoal != null : "fx:id=\"boxNumeroDiGoal\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert boxSquadra1 != null : "fx:id=\"boxSquadra1\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnCalcolaConnessioniGoal != null : "fx:id=\"btnCalcolaConnessioniGoal\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnAnalizzaRisultati != null : "fx:id=\"btnAnalizzaRisultati\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert boxSquadra2 != null : "fx:id=\"boxSquadra2\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnSimulaStagioni != null : "fx:id=\"btnSimulaStagioni\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SerieA.fxml'.";

    }
    
	public void setModel(Model model) {
		this.model = model;
	}
}