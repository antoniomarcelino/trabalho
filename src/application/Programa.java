package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Programa extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/view/tela_inicial.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("PROGRAMA");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		
		
	}
	
	public static void main(String args[]) {
		launch(args);
	}

}
