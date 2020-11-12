package controller;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Aluno;
import model.AlunoDAO;

public class RelatorioController {

    @FXML
    private TextField txtAulas;

    @FXML
    private Button btnGerarRelatorio;
    
    @FXML
    private TableView<Aluno> table;

    @FXML
    private TableColumn<Aluno, String> colNome;

    @FXML
    private TableColumn<Aluno, String> colEmail;
    
    @FXML
    private TableColumn<Aluno, Integer> colRegistro;

    private ObservableList<Aluno> obsList = FXCollections.observableArrayList();
    private List<Aluno> alunos;
    
    public void criarColunasTabela() {
    	colNome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));
    	colEmail.setCellValueFactory(new PropertyValueFactory<Aluno, String>("email"));
    	colRegistro.setCellValueFactory(new PropertyValueFactory<Aluno, Integer>("registroAluno"));
    }
    
    
    @FXML
    void gerarRelatorio(ActionEvent event) {
    	AlunoDAO dao = new AlunoDAO();
    	obsList.clear();
    	alunos = dao.gerarRelatorio("r");
    	
    	for(Aluno a: alunos){
    		obsList.add(a);
    	}
    	table.getItems().setAll(obsList);	
    	
    }

}
