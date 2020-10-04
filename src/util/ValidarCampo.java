package util;


import javafx.scene.control.TextField;


public class ValidarCampo {
	
	public static boolean checarCampoVazio(TextField textField)	{
		if(textField.getText().trim().equals("")){
			return true;
		}
		return false;
	}
	
	public static boolean checarAlfa(TextField textField) {
		if(textField.getText().trim().matches("[a-zA-Z ]*")){
			return true;
		}
		return false;
	}
	
	public static boolean checarFormatoData(TextField textField) {
		if(textField.getText().trim().matches("[0-9/]*")){
			return true;
		}
		return false;
	}
	
	public static boolean checarNumerico(TextField textField) {
		if(textField.getText().trim().matches("[0-9]*")){
			return true;
		}
		return false;
	}
	
	public static boolean checarTamanhoTelefone(TextField textField)	{
		if(textField.getText().trim().length() == 11){
			return true;
		}
		return false;
	}
	
	public static boolean checarTamanho(TextField textField, int tamanho)	{
		if(textField.getText().trim().length() <= tamanho){
			return true;
		}
		return false;
	}
	
	
	
}

	

