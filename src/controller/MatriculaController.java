package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import model.Aluno;
import model.AlunoDAO;
import model.Matricula;
import model.MatriculaDAO;
import model.Turma;
import model.TurmaDAO;

public class MatriculaController implements Initializable {

    @FXML
    private ComboBox<String> cbAlunos;
    
    @FXML
    private ComboBox<String> cbTurmas;
    
    @FXML
    private Button btnMatricular;
    
    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnAdicionar;

    @FXML
    private TableView<Turma> table;

    @FXML
    private TableColumn<Turma, String> colCodigo;

    @FXML
    private TableColumn<Turma, String> colDisciplinas;

    @FXML
    private TableColumn<Turma, String> colProfessor;
    
    private ObservableList<String> obsListAlunos = FXCollections.observableArrayList();
    private ObservableList<String> obsListTurmas = FXCollections.observableArrayList();
    private ObservableList<Turma> obsListMatriculas = FXCollections.observableArrayList();
    
    private List<Turma> turmas;
    private List<Aluno> alunos;
    private List<Turma> turmasMatriculadas = new ArrayList<Turma>();
    private List<Turma> turmasParaMatricular = new ArrayList<Turma>();
    
    @FXML
    void deletar(ActionEvent event) {
    	MatriculaDAO dao = new MatriculaDAO();
    	Matricula matricula = new Matricula();
    	int turma = table.getSelectionModel().getSelectedItem().getId();
    	int aluno = alunos.get(cbAlunos.getSelectionModel().getSelectedIndex()).getId();
    	
    	matricula.setTurma(turma);
    	matricula.setAluno(aluno);
    	dao.deletar(matricula);
    	
    	Alert alert;
    	String info = "Matrícula deletada com sucesso!";
    	alert = new Alert(AlertType.INFORMATION, info, ButtonType.OK);
		alert.setTitle("OK!");
		alert.setHeaderText("Sucesso!");
    	alert.show();
    	
    	Window stage = btnDeletar.getScene().getWindow();
    	stage.hide();
    	
    }
    
    public void novaTabela() {
    	colCodigo.setCellValueFactory(new PropertyValueFactory<Turma, String>("codigo"));
    	colDisciplinas.setCellValueFactory(new PropertyValueFactory<Turma, String>("disciplina"));
    	colProfessor.setCellValueFactory(new PropertyValueFactory<Turma, String>("professor"));
    }
    
    public void listarAlunos(){
    	AlunoDAO dao = new AlunoDAO();
    	obsListAlunos.clear();
    	alunos = dao.consultar("");
    	for(Aluno a: alunos){
    		obsListAlunos.add(a.getNome());
    	}
    	cbAlunos.setItems(obsListAlunos);		
    }
    
    public void listarTurmas(){
    	TurmaDAO dao = new TurmaDAO();
    	obsListTurmas.clear();
    	turmas = dao.consultar("");
    	for(Turma t: turmas){
    		obsListTurmas.add(t.getCodigo() + " - " + t.getDisciplina() + " - Professor: " + t.getProfessor());
    	}
    	cbTurmas.setItems(obsListTurmas);		
    }
    
    public void atualizarTabela() {
    	int indexTurma = cbTurmas.getSelectionModel().getSelectedIndex();
    	int indexAluno = cbAlunos.getSelectionModel().getSelectedIndex();
    	Turma turma = turmas.get(indexTurma);
    	Aluno aluno = alunos.get(indexAluno);
    	turmasMatriculadas = aluno.getTurmas();
    	
    	if(!find(turma)) {
    		turmasMatriculadas.add(turma);
    		turmasParaMatricular.add(turma);	
    	}
    	obsListMatriculas.clear();
    	 	
    	for(Turma t: turmasMatriculadas){
    		obsListMatriculas.add(t);
    	}
    	
    	for(Turma t: turmasParaMatricular){
    		System.out.println(t.getDisciplina());
    	}
    	
    	table.getItems().setAll(obsListMatriculas);
    	table.getSelectionModel().selectFirst();
    	
		if(turmasMatriculadas.size()>=5) {
		    		btnAdicionar.setDisable(true);
		 }
    }
    
    public boolean find(Turma turma) {
    	for(Turma t: turmasMatriculadas)
    	{
    		if(t.getCodigo().equals(turma.getCodigo()))
    			return true;
    	}
    	return false;
    }
    
    public void listarTurmasMatriculadas() {
    	int indexAluno = cbAlunos.getSelectionModel().getSelectedIndex();
    	Aluno a = alunos.get(indexAluno);
    	turmasMatriculadas = a.getTurmas();
    	obsListMatriculas.clear();
    	
    	for(Turma m: turmasMatriculadas){
    		obsListMatriculas.add(m);
    	}
    	
    	table.getItems().setAll(obsListMatriculas);
    	table.getSelectionModel().selectFirst();
    	
		if(turmasMatriculadas.size()>=5) {
		    	btnAdicionar.setDisable(true);
		 }else {btnAdicionar.setDisable(false);}
		
		if(turmasMatriculadas.size()>=5) {
	    	btnMatricular.setDisable(true);
		}else {btnMatricular.setDisable(false);}

    }
    
  
    
    @FXML
    void adicionar(ActionEvent event) {
    	atualizarTabela();
    }
    
    @FXML
    void matricular(ActionEvent event) {
    	MatriculaDAO dao = new MatriculaDAO();
    	Matricula matricula = new Matricula();
    	int indexAluno = cbAlunos.getSelectionModel().getSelectedIndex();
    	Aluno aluno = alunos.get(indexAluno);
    	
    	for(Turma turma: turmasParaMatricular) {
    		matricula.setAluno(aluno.getId());
    		matricula.setTurma(turma.getId());
    		dao.inserir(matricula);
    	}
    	turmasParaMatricular.clear();
    	
    	Alert alert;
    	String info = "Matrícula realizada com sucesso!";
    	alert = new Alert(AlertType.INFORMATION, info, ButtonType.OK);
		alert.setTitle("OK!");
		alert.setHeaderText("Sucesso!");
    	alert.show();		
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		novaTabela();
		listarAlunos();
		listarTurmas();
		
		cbAlunos.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue value, String old, String now) {
            	listarTurmasMatriculadas();
            }    
        });
	
	}
}

