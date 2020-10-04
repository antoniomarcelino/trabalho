package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.AlunoDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

	public class LoginAlunoController implements Initializable{

	    @FXML
	    private JFXTextField txtRegistroAluno;

	    @FXML
	    private Button btnEntrarAluno;

	    @FXML
	    void entrar(ActionEvent event) throws IOException {
	    	AlunoDAO dao = new AlunoDAO();
	    	String registro = txtRegistroAluno.getText();
	    	if(registro.equals("")) {
	    		Alert alert;
	        	String info = "Preencha o campo com registro de aluno";
	        	alert = new Alert(AlertType.INFORMATION, info, ButtonType.OK);
	    		alert.setTitle("Aviso!");
	    		alert.setHeaderText("Tente novamente!");
	        	alert.show();
	    	}else {
	    		boolean bool = dao.existe(registro);
		    	if(bool) {
		    		abrirForm("tela_alunos");
		    	}
		    	else {
		    		Alert alert;
		        	String info = "Registro de Aluno não encontrado";
		        	alert = new Alert(AlertType.INFORMATION, info, ButtonType.OK);
		    		alert.setTitle("Aviso!");
		    		alert.setHeaderText("Tente novamente!");
		        	alert.show();
		    	}
	    	}
	    	
	    	
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
			// TODO Auto-generated method stub
			
		}

	}

