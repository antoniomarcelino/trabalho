package controller;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
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
	    private TableColumn<Aluno, String> colTelefone;

	    @FXML
	    private TableColumn<Aluno, LocalDate> colDataNascimento;

	    @FXML
	    private TableColumn<Aluno, String> colMatricula;

	    @FXML
	    private TableColumn<Aluno, Integer> colAnoIngresso;
	    
	    private ObservableList<Aluno> obsList = FXCollections.observableArrayList();
	    private List<Aluno> alunos;

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
	    			&& (!ValidarCampo.checarCampoVazio(txtNome) && !ValidarCampo.checarCampoVazio(txtRegistroAluno) && !ValidarCampo.checarCampoVazio(txtDataMatricula)
	    					&& !ValidarCampo.checarCampoVazio(txtDataNascimento)) && !ValidarCampo.checarCampoVazio(txtEmail) ) {
	    	AlunoDAO dao = new AlunoDAO();
	    	Aluno a = new Aluno();

	    	a.setId(Integer.parseInt(txtID.getText()));
	    	a.setNome(txtNome.getText());
	    	a.setEmail(txtEmail.getText());
	    	a.setRegistroAluno(txtRegistroAluno.getText());
	    	a.setDataNascimento(txtDataNascimento.getText());

	    	dao.atualizar(a);
	    	atualizarTabela();
	    	limparCampos();
	    	}
	    }
	    
	    public void criarColunasTabela() {
	    	col_id.setCellValueFactory(new PropertyValueFactory<Aluno, Integer>("id"));
	    	colNome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));
	    	colEmail.setCellValueFactory(new PropertyValueFactory<Aluno, String>("email"));
	    	colTelefone.setCellValueFactory(new PropertyValueFactory<Aluno, String>("telefone"));
	    	colDataNascimento.setCellValueFactory(new PropertyValueFactory<Aluno, LocalDate>("dataNascimento"));
	    	colMatricula.setCellValueFactory(new PropertyValueFactory<Aluno, String>("matricula"));
	    	colAnoIngresso.setCellValueFactory(new PropertyValueFactory<Aluno, Integer>("anoIngresso"));
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
    	txtTelefone.setText("");
    	txtDataNascimento.setText("");
    }
    
    public void limparLabels()
    {
    	lblValidaNome.setText("");
    	lblValidaEmail.setText("");
    	lblValidaRegistroAluno.setText("");
    	lblValidaDataNascimento.setText("");
    }

    @FXML
    void guardar(ActionEvent event) throws ParseException, SQLException {
    	String labelTelefone= lblValidaRegistroAluno.getText();
    	String labelNome = lblValidaNome.getText();
    	String labelEmail = lblValidaEmail.getText();
    	String labelDataNascimento = lblValidaDataNascimento.getText();
    	
    	
    	if( (labelTelefone.equals("") && labelNome.equals("") && labelDataNascimento.equals("") && labelEmail.equals("")) 
    			&& (!ValidarCampo.checarCampoVazio(txtNome) && !ValidarCampo.checarCampoVazio(txtTelefone) 
    					&& !ValidarCampo.checarCampoVazio(txtDataNascimento)) && !ValidarCampo.checarCampoVazio(txtEmail) ) {
    	AlunoDAO dao = new AlunoDAO();
    	Aluno a = new Aluno();
    	
    	
    	a.setNome(txtNome.getText());
    	a.setEmail(txtEmail.getText());
    	a.setTelefone(txtTelefone.getText());
    	a.setDataNascimento(txtDataNascimento.getText());
    	a.setAnoIngresso();
    	a.setMatricula();
    	dao.inserir(a);
    	atualizarTabela();
    	limparCampos();
    	}
    }
    
    //Preenche os campos do formulario com os dados da tabela
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
    	txtTelefone.setText(a.getTelefone());
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
				
				if(ValidarCampo.checarCampoVazio(txtNome)) {
					information += "Campo Nome Obrigatório\n";
		    	}
		    	
		    	if(!ValidarCampo.checarAlfa(txtNome)) {
					information += "Campo Nome Deve Conter Apenas Letras\n";

		    	}

		    	if(!ValidarCampo.checarTamanho(txtNome, 80))
		    	{
		    		information += "Campo Nome Deve Conter no Máximo 50 Caracteres\n";

		    	}

				lblValidaNome.setText(information);
			}
		});
		
		txtTelefone.focusedProperty().addListener( (observable, oldValue, newValue) -> 
		{
			
			if(!newValue) 
			{
				String information = "";
				
				if(ValidarCampo.checarCampoVazio(txtTelefone)) {
					information += "Campo Telefone Obrigatório\n";
		    	}
		    	

		    	if(!ValidarCampo.checarNumerico(txtTelefone)) {
					information += "Campo Telefone Deve Ser Numérico \n";
		    	}
		    	
		    	if(!ValidarCampo.checarTamanhoTelefone(txtTelefone))
		    	{
		    		information += "Campo Telefone Deve Conter no Máximo 11 Dígitos\n";

		    	}
				lblValidaTelefone.setText(information);
				
			}
		});
		
		txtEmail.focusedProperty().addListener( (observable, oldValue, newValue) -> 
		{
			
			if(!newValue) 
			{
				String information = "";
				
				if(ValidarCampo.checarCampoVazio(txtEmail)) {
					information += "Campo Email Obrigatório\n";
		    	}
		    	
		    	
		    	if(!ValidarCampo.checarTamanho(txtEmail, 50))
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
				
				if(ValidarCampo.checarCampoVazio(txtDataNascimento)) {
					information += "Campo Data de Nascimento Obrigatório\n";
		    	}
		    	
		    	if(!ValidarCampo.checarFormatoData(txtDataNascimento)) {
					information += "Campo Data de Nascimento Deve Ser do Formato DD/MM/YYYY \n";
		    	}
				lblValidaDataNascimento.setText(information);
				
			}
		});
		
		
	}
    
   

}
