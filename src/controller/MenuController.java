package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController implements Initializable{

    
    
    @FXML
    private Button btnAlunos;

    @FXML
    private Button btnTurmas;

    @FXML
    private Button btnMatriculas;

    @FXML
    void abrirAlunos(ActionEvent event) throws IOException {
    	abrirForm("tela_login_alunos");
    }

    @FXML
    void abrirTurmas(ActionEvent event) throws IOException {
    	abrirForm("tela_turmas");
    }
    
    @FXML
    void abrirMatriculas(ActionEvent event) throws IOException {
    	abrirForm("tela_matriculas");
    }

    public void abrirForm(String formulario) throws IOException
    {
	    Parent root = FXMLLoader.load(getClass().getResource("/view/"+formulario+".fxml"));
	    Stage stage = new Stage();
	    stage.setScene(new Scene(root));
	    stage.setTitle(formulario);
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.show();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnAlunos.setOnMouseEntered(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent t) {
		        btnAlunos.setStyle("-fx-background-color: #054363;");
		    }
		});
		
		btnAlunos.setOnMouseExited(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent t) {
		        btnAlunos.setStyle("-fx-background-color:transparent;");
		    }
		});
		
		btnTurmas.setOnMouseEntered(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent t) {
		        btnTurmas.setStyle("-fx-background-color: #054363;");
		    }
		});
		
		btnTurmas.setOnMouseExited(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent t) {
		        btnTurmas.setStyle("-fx-background-color:transparent;");
		    }
		});

		
		btnMatriculas.setOnMouseEntered(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent t) {
		        btnMatriculas.setStyle("-fx-background-color: #054363;");
		    }
		});
		
		
		
		btnMatriculas.setOnMouseExited(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent t) {
		        btnMatriculas.setStyle("-fx-background-color:transparent;");
		    }
		});


		
		
	}
    
    
    
    
    
}
