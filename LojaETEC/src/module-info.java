module LojaETEC {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens br.com.etec.model to javafx.graphics, javafx.fxml;
	opens br.com.etec.controller to javafx.graphics, javafx.fxml;
}
