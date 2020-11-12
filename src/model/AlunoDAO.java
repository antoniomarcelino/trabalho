package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
	
	private Connection conexao;
	
	public AlunoDAO() {
		conexao = ConexaoBD.obterConexao();
	}
	
	public boolean deletar(Aluno a) {
		PreparedStatement stmt;
		String sql = "delete from aluno where id = ?";
		try {
			stmt = conexao.prepareStatement(sql);
		    stmt.setInt(1, a.getId());
		    stmt.execute();
		    stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
<<<<<<< HEAD
	public List<String> getAlunosPorFrequencia(){
		ArrayList<String> alunos = new ArrayList<>();
		PreparedStatement stmt;
		String str_nome = "";
		String str_faltas = "";
		String sql = "SELECT nome, faltas FROM aluno, matricula WHERE aluno.id = aluno";
		try {
			stmt = conexao.prepareStatement(sql);
		    ResultSet rs = stmt.executeQuery();
		    
		    while(rs.next()) {
		    	
		    	str_nome = rs.getString("nome");
		    	str_faltas = rs.getString("faltas");
		    	
		    	if(str_nome != null) 
		    		alunos.add(str_nome);
		    	
		    	if(str_faltas != null) {
		    		int freq = (100 * Integer.parseInt(str_faltas))/60;
		    		alunos.add(Integer.toString(freq));
		    		}
		    		
			  		
		    }
		    rs.close();
		    stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return alunos;
		}
		return alunos;
		
	}
	
=======
>>>>>>> 6cfaf62a6c7be9a38349f363525cf026e733643f
	public List<Aluno> gerarRelatorio(String str) {
		ArrayList<Aluno> alunos = new ArrayList<>();
		PreparedStatement stmt;
		Aluno a = null;
		String sql = "SELECT nome, registro_aluno, email FROM aluno, matricula WHERE aluno.id = aluno AND faltas > 45;";
		
		String nome;
	    String email;
	    int registroAluno;
		
		try {
			stmt = conexao.prepareStatement(sql);
		    ResultSet rs = stmt.executeQuery();
		    
		    while(rs.next()) {
		    	
		    	nome = rs.getString("nome");
		    	email = rs.getString("email");
		    	
		    	registroAluno = rs.getInt("registro_aluno");
		    	System.out.println(nome);
		    	System.out.println(email);
		    	
		    	if(nome != null) {
		    		a = new Aluno();
			    	a.setNome(nome);
			    	a.setEmail(email);
			    	a.setRegistroAluno(registroAluno);
			    
		    	}
		    	alunos.add(a);		
		    }
		    rs.close();
		    stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return alunos;
		}
		return alunos;
	}
	
	public boolean existe(String str) {
		PreparedStatement stmt = null;
        ResultSet result = null;
        String sql = "select * from aluno where registro_aluno = ?";
        try {
        	stmt = conexao.prepareStatement(sql);
        	stmt.setString(1, str);
		    result = stmt.executeQuery();
		    return result.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean inserir(Aluno a) throws SQLException {
		PreparedStatement stmt = null;
		String sql_insert = "INSERT INTO aluno (`nome`, `email`, `data_matricula`, `registro_aluno`, `data_nascimento`)" + 
				"VALUES (?, ?, ?, ?, ?)";
		try {
		    		stmt = conexao.prepareStatement(sql_insert);
		    		stmt.setString(1, a.getNome());
				    stmt.setString(2, a.getEmail());
				    stmt.setString(3, a.getDataMatricula());
				    stmt.setInt(4, a.getRegistroAluno());
				    stmt.setDate(5, java.sql.Date.valueOf(a.getDataNascimento()));
				    stmt.execute();
				    stmt.close();

		    
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean atualizar(Aluno a) throws SQLException {
		PreparedStatement stmt = null;
		boolean resultado = true;
		String sql_update = "UPDATE aluno SET nome = ?, email = ?, data_matricula= ?, data_nascimento =?, registro_aluno =  ? WHERE id = ?";
	        try {
	 
	        	stmt = conexao.prepareStatement(sql_update);
		           stmt.setString(1, a.getNome());
		           stmt.setString(2, a.getEmail());
		           stmt.setString(3, a.getDataMatricula());
		           stmt.setDate(4, java.sql.Date.valueOf( a.getDataNascimento() ));
		           stmt.setInt(5,  a.getRegistroAluno());
		           stmt.setInt(6, a.getId());
	           
	            int linhasAfetadas = stmt.executeUpdate();
	            resultado = linhasAfetadas > 0;
	            stmt.close();
	            
	        } catch (SQLException e) {
	           resultado = false;
	        }finally {
	        	if(stmt != null)
	        		stmt.close();
	        }
		return resultado;
	}
	
	public List<Aluno> consultar(String pesquisa) {
		ArrayList<Aluno> alunos = new ArrayList<>();
		PreparedStatement stmt;
		Aluno a = null;
		String sql = "";
		String sql2 = "SELECT turma.id, codigo, disciplina, professor " + 
		"FROM inclusao, aluno, turma " + 
		"WHERE aluno.id = ? and inclusao.aluno = ? and inclusao.turma = turma.id";
		
		if(pesquisa.length() == 0) {
			sql = "SELECT * FROM aluno";
		}
		else
		{
			sql = "select * from aluno where nome like '" + pesquisa + "%'";
		}
		
		
		
		int id;
		String nome;
	    String email;
	    Date dataNascimento;
	    String dataMatricula;
	    int registroAluno;
		
		try {
			stmt = conexao.prepareStatement(sql);
		    ResultSet rs = stmt.executeQuery();
		    
		    while(rs.next()) {
		    	id = rs.getInt("id");
		    	nome = rs.getString("nome");
		    	email = rs.getString("email");
		    	dataNascimento = rs.getDate("data_nascimento");
		    	dataMatricula = rs.getString("data_matricula");
		    	registroAluno = rs.getInt("registro_aluno");
		    	
		    	if(nome != null) {
		    		a = new Aluno();
			    	a.setId(id);
			    	a.setNome(nome);
			    	a.setEmail(email);
			    	a.setDataNascimento(dataNascimento.toLocalDate());
			    	a.setDataMatricula(dataMatricula);
			    	a.setRegistroAluno(registroAluno);
			    	
			    	/*ArrayList<Turma> turmas = new ArrayList<Turma>();
					Turma turma = null;
						String professor, codigo, disciplina;
						int idTurma;
							stmt = conexao.prepareStatement(sql2);
							stmt.setInt(1, id);
							stmt.setInt(2, id);
						    ResultSet rs2 = stmt.executeQuery();
						    while(rs2.next()) {
						    	idTurma = rs2.getInt("id");
						    	codigo = rs2.getString("codigo");
						    	disciplina = rs2.getString("disciplina");
						    	professor = rs2.getString("professor");
						       	turma = new Turma();
						    	turma.setId(idTurma);
						    	turma.setCodigo(codigo);
						    	turma.setDisciplina(disciplina);
						    	turma.setProfessor(professor);
						    	turmas.add(turma);	
						    }
						    rs2.close();
			    	if(!turmas.isEmpty()) {
			    		a.setTurmas(turmas);
			    	}*/
		    	}
		    	alunos.add(a);		
		    }
		    rs.close();
		    stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return alunos;
		}
		return alunos;
	}
	
	
}
