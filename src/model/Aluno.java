package model;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class Aluno {
	private int id;
	private String nome;
	private String email;
	private String dataMatricula;
	private LocalDate dataNascimento;
	private int registroAluno;
	private ArrayList<Turma> turmas;
	
	
	
	public String getDataMatricula() {
		return dataMatricula;
	}
	public void setDataMatricula(String dataMatricula) {
		this.dataMatricula = dataMatricula;
	}
	public int getRegistroAluno() {
		return registroAluno;
	}
	public void setRegistroAluno(int registroAluno) {
		this.registroAluno = registroAluno;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(String s) {
		Locale locale = new Locale("pt", "BR");
    	DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu", locale);
    	LocalDate data = LocalDate.parse(s, formato);
		this.dataNascimento = data;
	}
	public void setDataNascimento(LocalDate data) {
		this.dataNascimento = data;
	}
	
	public ArrayList<Turma> getTurmas() {
		return turmas;
	}
	public void setTurmas(ArrayList<Turma> turmas) {
		this.turmas = turmas;
	}
	public Aluno() {
		super();
		this.turmas = new ArrayList<Turma>();
	}

	
}
