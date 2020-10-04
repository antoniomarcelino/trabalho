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
	private String telefone;
	private String matricula;
	private LocalDate dataNascimento;
	private int anoIngresso;
	private ArrayList<Turma> turmas;
	
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public void setMatricula() {
		this.matricula = Integer.toString(anoIngresso) + "123";
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
	public int getAnoIngresso() {
		return anoIngresso;
	}
	public void setAnoIngresso() {
		int ano = Year.now().getValue();
		this.anoIngresso = ano;
	}
	public void setAnoIngresso(int anoIngresso) {
		this.anoIngresso = anoIngresso;
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
