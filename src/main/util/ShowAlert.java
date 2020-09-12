package main.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ShowAlert {

	public void validacaoAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Atenção");
		alert.setHeaderText(null);
		alert.setContentText("Preencha todos os campos marcados com *.");
		alert.showAndWait();
	}
	
	public boolean sucessoAlert(String mensagem) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Sucesso");
		alert.setHeaderText(null);
		alert.setContentText(mensagem);
		
		Optional<ButtonType> result = alert.showAndWait();
		ButtonType button = result.orElse(ButtonType.CANCEL);

		if (button == ButtonType.OK) {
		    return true;
		} 
		
		return false;
	}
	
}
