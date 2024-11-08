package br.com.etec.controller;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Alteração para realizar modificações sem realizar o login AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/br/com/etec/view/telaLogin.fxml"));
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/br/com/etec/view/telaPrincipal.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/br/com/etec/view/application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			primaryStage.initStyle(StageStyle.UNDECORATED);
			
			
			primaryStage.show();//Show tem que ser o último pq ele é que mostra
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}   
