package main.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Principal extends Application
{


	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/view/fornecedor/ListaFornecedor.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Lista Fornecedores - Uni Venda");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}

	public static void main(String[] args) {
		launch(args);
		
		/*ArrayList<Fornecedor> listUsuarios = new DaoFornecedor().listarFornecedores();
		for(Fornecedor umUsuario : listUsuarios)
			System.out.println(umUsuario.toString());*/
		
		
	}
}
