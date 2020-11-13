package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurmaDAO {

private Connection conexao;
	
	public TurmaDAO() {
		conexao = ConexaoBD.obterConexao();
	}
	
	public boolean inserir(Turma t) {
		PreparedStatement stmt;
		String sql = "INSERT INTO turma (`codigo`, `disciplina`, `professor`)" + 
				"VALUES (?, ?, ?)";
		
		
		try {
			stmt = conexao.prepareStatement(sql);
		    stmt.setString(1, t.getCodigo());
		    stmt.setString(2, t.getDisciplina());
		    stmt.setString(3, t.getProfessor());
		    stmt.execute();
		    
		
		    
		    stmt.close();  
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	    
		return true;
	}
	
	
public boolean deletar(Turma t) throws SQLException {
		
		PreparedStatement stmt = null;
		String sql = "delete from turma where id = ?";
		
		try {
			stmt = conexao.prepareStatement(sql);
		    stmt.setInt(1, t.getId());
		    int rowsAffected = stmt.executeUpdate();
		    if( rowsAffected > 0) {
		    	stmt.close();
		    	return true;
		    }
		   
		} catch(SQLException e){
			e.printStackTrace();
			
			
		}
		return false;	
	}

public List<String> getProfessores(){
	ArrayList<String> professores = new ArrayList<>();
	PreparedStatement stmt;
	String str_nome = "";
	String str_materia = "";
	String sql = "SELECT funcionario.nome, materia.nome\r\n" + 
			"FROM funcionario, materia, turma\r\n" + 
			"WHERE funcionario.id = professor AND materia.id = materia AND funcionario.cargo = \"professor\"";
	try {
		stmt = conexao.prepareStatement(sql);
	    ResultSet rs = stmt.executeQuery();
	    
	    while(rs.next()) {
	    	
	    	str_nome = rs.getString("funcionario.nome");
	    	str_materia = rs.getString("materia.nome");
	    	
	    	if(str_nome != null) 
	    		professores.add(str_nome);
	    	
	    	if(str_materia != null) 
	    		professores.add(str_materia);
	    		  		
	    }
	    rs.close();
	    stmt.close();
	} catch (SQLException e) {
		e.printStackTrace();
		return professores;
	}
	return professores;
	
}

	
	public List<Turma> consultar(String pesquisa) {
		ArrayList<Turma> turmas = new ArrayList<>();
		PreparedStatement stmt;
		Turma t = null;
		String sql = "";
		
		if(pesquisa.length() == 0) {
			sql = "select * from turma";
		}
		else
		{
			sql = "select * from turma where disciplina like '" + pesquisa + "%'";
		}
		 
		
		
		int id;
		String codigo;
	    String disciplina;
	    String professor;
	    
		
		try {
			stmt = conexao.prepareStatement(sql);
		    ResultSet rs = stmt.executeQuery();
		    
		    while(rs.next()) {
		    	id = rs.getInt("id");
		    	codigo = rs.getString("codigo");
		    	disciplina = rs.getString("disciplina");
		    	professor = rs.getString("professor");
		    	
		    	
		    	if(codigo != null) {
		    		t = new Turma();
			    	t.setId(id);
			    	t.setCodigo(codigo);
			    	t.setDisciplina(disciplina);
			    	t.setProfessor(professor);
			    	
		    	}
		    	turmas.add(t);		
		    }
		
		    
		    rs.close();
		    stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return turmas;
		}
		return turmas;
	}
	
public boolean atualizar(Turma t) throws SQLException {
		
		PreparedStatement stmt = null;
		boolean resultado = true;
		String sql_update = "UPDATE turma SET codigo = ?, disciplina = ?, professor =? WHERE id = ?";
	    
	    
	        try {
	 
	        	stmt = conexao.prepareStatement(sql_update);
		           stmt.setString(1, t.getCodigo());
		           stmt.setString(2, t.getDisciplina());
		           stmt.setString(3, t.getProfessor());
		           stmt.setInt(4, t.getId());
		          
	           
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
	
	
}
