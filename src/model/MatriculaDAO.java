package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MatriculaDAO {
	
private Connection conexao;
	
	public MatriculaDAO() {
		conexao = ConexaoBD.obterConexao();
	}
	
public boolean deletar(Matricula m) {
		
		PreparedStatement stmt;
		String sql = "delete from inclusao where aluno = ? and turma = ?";
		
		try {
			stmt = conexao.prepareStatement(sql);
		    stmt.setInt(1, m.getAluno());
		    stmt.setInt(2, m.getTurma());
		    stmt.execute();
		    stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean inserir(Matricula m) {
		PreparedStatement stmt;
		String sql = "INSERT INTO inclusao (`aluno`, `turma`)" + 
				"VALUES (?, ?)";
		
		
		try {
			stmt = conexao.prepareStatement(sql);
		    stmt.setInt(1, m.getAluno());
		    stmt.setInt(2, m.getTurma());
		    
		    stmt.execute();
		    
		  
		    
		    stmt.close();  
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	    
		return true;
	}

}
