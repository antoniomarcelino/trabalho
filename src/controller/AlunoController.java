package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Aluno;
import model.AlunoDAO;
import util.ValidarCampo;

public class AlunoController implements Initializable{
	

	    @FXML
	    private JFXTextField txtNome;

	    @FXML
	    private JFXTextField txtEmail;

	    @FXML
	    private JFXTextField txtRegistroAluno;

	    @FXML
	    private JFXTextField txtDataNascimento;
	    
	    @FXML
	    private JFXTextField txtDataMatricula;
	    
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
	    private Button btnGerarRelatorio;
	    
	    @FXML
	    private Label lblValidaNome;

	    @FXML
	    private Label lblValidaEmail;

	    @FXML
	    private Label lblValidaRegistroAluno;

	    @FXML
	    private Label lblValidaDataNascimento;
	    
	    @FXML
	    private Label lblValidaDataMatricula;
	    
	  
	    
	    @FXML
	    private TableView<Aluno> table;
	    
	    @FXML
	    private TableColumn<Aluno, Integer> col_id;
	    
	    @FXML
	    private TableColumn<Aluno, String> colNome;

	    @FXML
	    private TableColumn<Aluno, String> colEmail;

	    @FXML
	    private TableColumn<Aluno, String> colDataMatricula;

	    @FXML
	    private TableColumn<Aluno, LocalDate> colDataNascimento;

	    @FXML
	    private TableColumn<Aluno, Integer> colRegistro;

<<<<<<< HEAD
	    @FXML
	    private Button alunosPorFrequenciaBtn;
=======
>>>>>>> 6cfaf62a6c7be9a38349f363525cf026e733643f
	   	    
	    private ObservableList<Aluno> obsList = FXCollections.observableArrayList();
	    private List<Aluno> alunos;
	    
	    @FXML
	    void getAlunosPorFrequencia(ActionEvent event) {
	    	AlunoDAO dao = new AlunoDAO();
	    	List<String> alunos = dao.getAlunosPorFrequencia();
	    	String lista = "Alunos por Frequencia: \n";
	    	for(int x = 0; x <alunos.size(); x+=2) {
	    		lista+= "\t" + alunos.get(x) + "\n" + "\t" + alunos.get(x+1) + "%\n";
	    	}
	    	Alert alert;
        	alert = new Alert(AlertType.INFORMATION, lista, ButtonType.OK);
    		alert.setTitle("Lista de Alunos");
    		alert.setHeaderText("A lista mostra aluno por frequencia");
        	alert.show();
	    }

	    @FXML
	    void deletar(ActionEvent event) {
	    	AlunoDAO dao = new AlunoDAO();
	    	Aluno aluno = table.getSelectionModel().getSelectedItem();
	    	
	    	dao.deletar(aluno);
	    	limparCampos();
	    	atualizarTabela();
	    	
	    	btnAtualizar.setDisable(true);
	    	btnGuardar.setDisable(false);
	    }
	    
	    @FXML
	    void pesquisar(KeyEvent event) {
	    	atualizarTabela();
	    }
	    
	    @FXML
	    void atualizar(ActionEvent event) throws ParseException, SQLException {
	    	String labelRegistroAluno= lblValidaRegistroAluno.getText();
	    	String labelNome = lblValidaNome.getText();
	    	String labelEmail = lblValidaEmail.getText();
	    	String labelDataNascimento = lblValidaDataNascimento.getText();
	    	String labelDataMatricula = lblValidaDataMatricula.getText();
	    	
	    	
	    	if( (labelRegistroAluno.equals("") && labelNome.equals("") && labelDataNascimento.equals("") && labelEmail.equals("") && labelDataMatricula.equals("")) 
	    			&& (!ValidarCampo.checarCampoVazio(txtNome.getText()) && !ValidarCampo.checarCampoVazio(txtRegistroAluno.getText()) && !ValidarCampo.checarCampoVazio(txtDataMatricula.getText())
	    					&& !ValidarCampo.checarCampoVazio(txtDataNascimento.getText())) && !ValidarCampo.checarCampoVazio(txtEmail.getText()) ) {
	    	AlunoDAO dao = new AlunoDAO();
	    	Aluno a = new Aluno();

	    	a.setId(Integer.parseInt(txtID.getText()));
	    	a.setNome(txtNome.getText());
	    	a.setEmail(txtEmail.getText());
	    	a.setRegistroAluno(Integer.parseInt(txtRegistroAluno.getText()));
	    	a.setDataNascimento(txtDataNascimento.getText());
	    	a.setDataMatricula(txtDataMatricula.getText());

	    	dao.atualizar(a);
	    	atualizarTabela();
	    	limparCampos();
	    	}
	    }
	    
	    public void criarColunasTabela() {
	    	col_id.setCellValueFactory(new PropertyValueFactory<Aluno, Integer>("id"));
	    	colNome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));
	    	colEmail.setCellValueFactory(new PropertyValueFactory<Aluno, String>("email"));
	    	colDataMatricula.setCellValueFactory(new PropertyValueFactory<Aluno, String>("dataMatricula"));
	    	colDataNascimento.setCellValueFactory(new PropertyValueFactory<Aluno, LocalDate>("dataNascimento"));
	    	colRegistro.setCellValueFactory(new PropertyValueFactory<Aluno, Integer>("registroAluno"));
	    }
	    
	    public void atualizarTabela(){
	    	AlunoDAO dao = new AlunoDAO();
	    	obsList.clear();
	    	alunos = dao.consultar(barraPesquisa.getText());
	    	for(Aluno a: alunos){
	    		obsList.add(a);
	    	}
	    	table.getItems().setAll(obsList);	
	    }


	    @FXML
	    void limpar(ActionEvent event) {
	    	limparCampos();
	    	limparLabels();
	    	txtEmail.setDisable(false);
	    	btnAtualizar.setDisable(true);
	    	btnGuardar.setDisable(false);
	    }

    public void limparCampos()
    {
    	txtID.setText("");
    	txtNome.setText("");
    	txtEmail.setText("");
    	txtRegistroAluno.setText("");
    	txtDataNascimento.setText("");
    	txtDataMatricula.setText("");
    }
    
    public void limparLabels()
    {
    	lblValidaNome.setText("");
    	lblValidaEmail.setText("");
    	lblValidaRegistroAluno.setText("");
    	lblValidaDataNascimento.setText("");
    	lblValidaDataMatricula.setText("");
    }

    @FXML
    void guardar(ActionEvent event) throws ParseException, SQLException {
    	
    	String labelNome = lblValidaNome.getText();
    	String labelEmail = lblValidaEmail.getText();
    	String labelDataNascimento = lblValidaDataNascimento.getText();
    	String labelDataMatricula = lblValidaDataMatricula.getText();
    	String labelRegistroAluno = lblValidaRegistroAluno.getText();
    	
    	
    	if(  (labelNome.equals("") && labelDataNascimento.equals("") && labelEmail.equals("")) 
    		&& (!ValidarCampo.checarCampoVazio(txtNome.getText()) && !ValidarCampo.checarCampoVazio(txtDataNascimento.getText())
    			&& !ValidarCampo.checarCampoVazio(txtEmail.getText()) )){
    	AlunoDAO dao = new AlunoDAO();
    	Aluno a = new Aluno();
    	
    	a.setNome(txtNome.getText());
    	a.setEmail(txtEmail.getText());
    	a.setDataNascimento(txtDataNascimento.getText());
    	a.setDataMatricula(txtDataMatricula.getText());
    	a.setRegistroAluno(Integer.parseInt(txtRegistroAluno.getText()));
    	dao.inserir(a);
    	atualizarTabela();
    	limparCampos();
    	}
    }
    
    @FXML
    void gerarRelatorio(ActionEvent event) throws IOException {
  		 abrirForm("tela_relatorios");

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
    
    public void atualizarCampos() {
    	Aluno a = table.getSelectionModel().getSelectedItem();
    	String data = LocalDate.parse( 
    		    a.getDataNascimento().toString() ,  
    		    DateTimeFormatter.ofPattern( "uuuu-MM-dd" , Locale.UK ) 
    		).format(
    		    DateTimeFormatter.ofPattern( "dd/MM/uuuu" , Locale.UK )
    		);
    	txtID.setText(Integer.toString(a.getId()));
    	txtNome.setText(a.getNome());
    	txtEmail.setText(a.getEmail());
    	//txtTelefone.setText(a.getTelefone());
    	txtDataNascimento.setText(data);
    	
    	btnAtualizar.setDisable(false);
    	btnGuardar.setDisable(true);
    	txtEmail.setDisable(true);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		criarColunasTabela();
		atualizarTabela();
		btnAtualizar.setDisable(true);
		
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    atualizarCampos();
		    limparLabels();
		});
		
		//Validações de campos
		txtNome.focusedProperty().addListener( (observable, oldValue, newValue) -> 
		{
		
			if(!newValue) 
			{
				String information = "";
				
				if(ValidarCampo.checarCampoVazio(txtNome.getText())) {
					information += "Campo Nome Obrigatório\n";
		    	}
		    	
		    	if(!ValidarCampo.checarAlfa(txtNome.getText())) {
					information += "Campo Nome Deve Conter Apenas Letras\n";

		    	}

		    	if(!ValidarCampo.checarTamanho(txtNome.getText(), 80))
		    	{
		    		information += "Campo Nome Deve Conter no Máximo 50 Caracteres\n";

		    	}

				lblValidaNome.setText(information);
			}
		});
		
		
		
		txtEmail.focusedProperty().addListener( (observable, oldValue, newValue) -> 
		{
			
			if(!newValue) 
			{
				String information = "";
				
				if(ValidarCampo.checarCampoVazio(txtEmail.getText())) {
					information += "Campo Email Obrigatório\n";
		    	}
		    	
		    	
		    	if(!ValidarCampo.checarTamanho(txtEmail.getText(), 50))
		    	{
		    		information += "Campo Email Deve Conter no Máximo 11 Caracteres\n";
		    	}
				lblValidaEmail.setText(information);
				
			}
		});
	
		txtDataNascimento.focusedProperty().addListener( (observable, oldValue, newValue) -> 
		{
			
			if(!newValue) 
			{
				String information = "";
				
				if(ValidarCampo.checarCampoVazio(txtDataNascimento.getText())) {
					information += "Campo Data de Nascimento Obrigatório\n";
		    	}
		    	
		    	if(!ValidarCampo.checarFormatoData(txtDataNascimento.getText())) {
					information += "Campo Data de Nascimento Deve Ser do Formato DD/MM/YYYY \n";
		    	}
				lblValidaDataNascimento.setText(information);
				
			}
		});
		
		
	}
    
   

}
