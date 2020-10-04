package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.Turma;
import model.TurmaDAO;
import util.ValidarCampo;

public class TurmaController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;

    @FXML
    private JFXTextField txtDisciplina;

    @FXML
    private JFXTextField txtProfessor;
    
    @FXML
    private JFXTextField txtID;

    @FXML
    private TextField barraPesquisa;
    
    @FXML
    private Button btnNovo;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnGuardar;
    
    @FXML
    private Button btnAtualizar;
    
    @FXML
    private Label lblValidaCodigo;

    @FXML
    private Label lblValidaDisciplina;

    @FXML
    private Label lblValidaProfessor;

    @FXML
    private TableView<Turma> table;
    
    @FXML
    private TableColumn<Turma, Integer> col_id;

    @FXML
    private TableColumn<Turma, String> colCodigo;

    @FXML
    private TableColumn<Turma, String> colDisciplina;

    @FXML
    private TableColumn<Turma, String> colProfessor;
  
    private ObservableList<Turma> obsList = FXCollections.observableArrayList();
    private List<Turma> turmas;
    
    @FXML
    void atualizar(ActionEvent event) throws SQLException {
    	TurmaDAO dao = new TurmaDAO();
    	Turma t = new Turma(); 	
    	t.setId(Integer.parseInt(txtID.getText()));
    	t.setCodigo(txtCodigo.getText());
    	t.setDisciplina(txtDisciplina.getText());
    	t.setProfessor(txtProfessor.getText());
    	dao.atualizar(t);
    	atualizarTabela();
    	limparCampos();
    }
    
    public void criarColunasTabela() {
    	col_id.setCellValueFactory(new PropertyValueFactory<Turma, Integer>("id"));
    	colCodigo.setCellValueFactory(new PropertyValueFactory<Turma, String>("codigo"));
    	colDisciplina.setCellValueFactory(new PropertyValueFactory<Turma, String>("disciplina"));
    	colProfessor.setCellValueFactory(new PropertyValueFactory<Turma, String>("professor"));	
    }
    
    public void atualizarTabela(){
    	TurmaDAO dao = new TurmaDAO();
    	obsList.clear();
    	turmas = dao.consultar(barraPesquisa.getText());
    	for(Turma t: turmas){
    		obsList.add(t);
    	}
    	table.getItems().setAll(obsList);		
    }

    @FXML
    void deletar(ActionEvent event) throws SQLException {
    	TurmaDAO dao = new TurmaDAO();
    	Turma turma = table.getSelectionModel().getSelectedItem();
    	if(dao.deletar(turma)) {
    		atualizarTabela();
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION, "Não foi possivel deletar turma já possui aluno matriculado", ButtonType.OK);
    		alert.setTitle("Aviso!");
        	alert.show();
    	}
    	limparCampos();
    	btnAtualizar.setDisable(true);
    	btnGuardar.setDisable(false);	
    }
    
    @FXML
    void pesquisar(KeyEvent event) {
    	atualizarTabela();
    }

    @FXML
    void guardar(ActionEvent event) {
    	String labelCodigo = lblValidaCodigo.getText();
    	String labelDisciplina = lblValidaDisciplina.getText();
    	String labelProfessor = lblValidaProfessor.getText();
    	
    	if( (labelCodigo.equals("") && labelDisciplina.equals("") && labelDisciplina.equals("") && labelProfessor.equals("")) 
    			&& (!ValidarCampo.checarCampoVazio(txtCodigo) && !ValidarCampo.checarCampoVazio(txtDisciplina) 
    					&& !ValidarCampo.checarCampoVazio(txtProfessor)) ) {
    	TurmaDAO dao = new TurmaDAO();
    	Turma t = new Turma();
    
    	t.setCodigo(txtCodigo.getText());
    	t.setDisciplina(txtDisciplina.getText());
    	t.setProfessor(txtProfessor.getText());
    	dao.inserir(t);
    	atualizarTabela();
    	limparCampos();
    	}
    }

    @FXML
    void limpar(ActionEvent event) {
    	limparCampos();
    	limparLabels();
    	txtCodigo.setDisable(false);
    	btnAtualizar.setDisable(true);
    	btnGuardar.setDisable(false);
    }
    
    public void limparCampos()
    {
    	txtID.setText("");
    	txtCodigo.setText("");
    	txtDisciplina.setText("");
    	txtProfessor.setText("");
    }
    
    public void limparLabels()
    {
    	lblValidaCodigo.setText("");
    	lblValidaDisciplina.setText("");
    	lblValidaProfessor.setText("");
    	
    }
    
    public void atualizarCampos() {
    	Turma t = table.getSelectionModel().getSelectedItem();
    	txtID.setText(Integer.toString(t.getId() ));
    	txtCodigo.setText(t.getCodigo());
    	txtDisciplina.setText(t.getDisciplina());
    	txtProfessor.setText(t.getProfessor());	
    	btnAtualizar.setDisable(false);
    	btnGuardar.setDisable(true);
    	txtCodigo.setDisable(true);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		criarColunasTabela();
		atualizarTabela();
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    atualizarCampos();
		});
		
		txtCodigo.focusedProperty().addListener( (observable, oldValue, newValue) -> 
		{
		
			if(!newValue) 
			{
				String information = "";
				
				if(ValidarCampo.checarCampoVazio(txtCodigo)) {
					information += "Campo Código Obrigatório\n";
		    	}
		    	
		    	if(!ValidarCampo.checarTamanho(txtCodigo, 20))
		    	{
		    		information += "Campo Código Deve Conter no Máximo 20 Caracteres\n";

		    	}

				lblValidaCodigo.setText(information);
			}
		});
		
		txtDisciplina.focusedProperty().addListener( (observable, oldValue, newValue) -> 
		{
			
			if(!newValue) 
			{
				String information = "";
				
				if(ValidarCampo.checarCampoVazio(txtDisciplina)) {
					information += "Campo Disciplina Obrigatório\n";
		    	}
		    	

		    	if(!ValidarCampo.checarTamanho(txtDisciplina, 50))
		    	{
		    		information += "Campo Disciplina Deve Conter no Máximo 50 Caracteres\n";

		    	}
				lblValidaDisciplina.setText(information);
				
			}
		});
		
		txtProfessor.focusedProperty().addListener( (observable, oldValue, newValue) -> 
		{
			
			if(!newValue) 
			{
				String information = "";
				
				if(ValidarCampo.checarCampoVazio(txtProfessor)) {
					information += "Campo Nome do Professor Obrigatório\n";
		    	}
		    	
		    	
		    	if(!ValidarCampo.checarTamanho(txtProfessor, 80))
		    	{
		    		information += "Campo Nome do Professor Deve Conter no Máximo 80 Caracteres\n";
		    	}
				lblValidaProfessor.setText(information);
				
			}
		});
	
		
		
	}

}
