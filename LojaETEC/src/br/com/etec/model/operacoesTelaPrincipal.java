package br.com.etec.model;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;

public class operacoesTelaPrincipal {
	@FXML
	private ComboBox<String> cmbTipo;
	@FXML
	private ComboBox<String> cmbMarca;
	@FXML
	private ComboBox<String> cmbModelo;
	@FXML
	private Button btnTipo;
	@FXML
	private Button btnMarca;
	@FXML
	private Button btnModelo;

	
	// Inicia os valores das combobox
	public void initialize(URL url, ResourceBundle rb) {
		
		ObservableList<String> lista = FXCollections.observableArrayList("Tênis", "Chinelo");
		cmbTipo.setItems(lista);
		
		ObservableList<String> lista2 = FXCollections.observableArrayList("Nike", "Adidas");
		cmbMarca.setItems(lista2);
		
		ObservableList<String> lista3 = FXCollections.observableArrayList("TEN001 (Jordan)",
				"TEN002 (Shox)", "CHN001 (And 1)", "TEA001 (Swift Run)", "CHA001 (Adinova)");
		cmbModelo.setItems(lista3);
	}

}
