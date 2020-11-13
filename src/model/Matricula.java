package model;

import java.util.List;

public class Matricula {
	private int id;
	private int aluno;
	private int turma;
	private List<Double> notas;
	private int faltas;
	private int status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAluno() {
		return aluno;
	}
	public void setAluno(int aluno) {
		this.aluno = aluno;
	}
	public int getTurma() {
		return turma;
	}
	public void setTurma(int turma) {
		this.turma = turma;
	}
	
	public int getFaltas() {
		return faltas;
	}
	public void setFaltas(int faltas) {
		this.faltas = faltas;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<Double> getNotas() {
		return notas;
	}
	public void setNotas(double nota) {
		notas.add(nota);
	}
	
	

}
