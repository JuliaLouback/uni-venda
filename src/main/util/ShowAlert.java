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
	
	public void validacao() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Atenção");
		alert.setHeaderText(null);
		alert.setContentText("Preencha e-mail e senha!");
		alert.showAndWait();
	}
	
	public void validacaoVenda() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Atenção");
		alert.setHeaderText(null);
		alert.setContentText("Selecione o Cliente e/ou adicione produtos a lista!");
		alert.showAndWait();
	}
	
	public void validacaoVendaForma() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Atenção");
		alert.setHeaderText(null);
		alert.setContentText("Selecione as formas de pagamento e/ou valor inserido menor que o valor total!");
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
	
	public void erroAlert(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	public void informationAlert(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Sucesso");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	public boolean confirmationAlert(String mensagem) {
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
