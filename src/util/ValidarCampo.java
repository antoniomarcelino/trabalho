package util;


public class ValidarCampo {
	
	public static boolean checarCampoVazio(String textField)	{
		if(textField.trim().equals("")){
			return true;
		}
		return false;
	}
	
	public static boolean checarAlfa(String textField) {
		if(textField.trim().matches("[a-zA-Z ]*")){
			return true;
		}
		return false;
	}

	public static boolean checarFormatoData(String textField) {
		if(textField.trim().matches("[0-9/]*")){
			return true;
		}
		return false;
	}
	
	public static boolean checarNumerico(String textField) {
		if(textField.trim().matches("[0-9]*")){
			return true;
		}
		return false;
	}
	
	public static boolean checarTamanhoTelefone(String textField)	{
		if(textField.trim().length() == 11){
			return true;
		}
		return false;
	}
	
	public static boolean checarTamanho(String textField, int tamanho)	{
		if(textField.trim().length() <= tamanho){
			return true;
		}
		return false;
	}
	
	
	
}

	

