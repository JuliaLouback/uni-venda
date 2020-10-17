package resources.view.produto;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.Validator;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.dao.DaoCategoria;
import main.dao.DaoFornecedor;
import main.dao.DaoProduto;
import main.entity.Categoria;
import main.entity.Produto;
import main.entity.Fornecedor;
import main.util.MaskFieldUtil;
import main.util.ShowAlert;
import resources.view.login.ControllerViewLogin;
import resources.view.painel.ControllerViewPainel;

public class ControllerViewProduto implements Initializable{

    @FXML
    private ComboBox<String>  Unidade_medida;

    @FXML
    private TextField Id_produto;

    @FXML
    private TextField Valor_unitario;

    @FXML
    private TextField Estoque_minimo;

    @FXML
    private TextField Estoque_atual;

    @FXML
    private TextField Peso_liquido;

    @FXML
    private TextField Peso_bruto;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private TextField Estoque_maximo;

    @FXML
    private TextField Nome;

    @FXML
    private ComboBox<Categoria> Categoria;

    @FXML
    private ComboBox<Fornecedor> Fornecedor;
    @FXML
    private Label labelChange;
    
    private Produto Produtos = new Produto();
    
    @FXML
    void addProduto(ActionEvent event) {
    	
    	if(Produtos.getNome() != null && !Produtos.getNome().isEmpty()) {
    		editar();
    	} else {
    		adicionar();
    	}
    	
    }

	public void start(Stage primaryStage) {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/view/Produto/CadastroProduto.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Cadastro Produto - Uni Venda");
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/resources/img/money.png"));
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	 @FXML
	 void BackButton(ActionEvent event) {
		 Stage stage = (Stage) btnBack.getScene().getWindow(); 
	     ControllerViewListaProduto t = new ControllerViewListaProduto();
		 t.start(stage);
		 
	 }
	 
	 public void setLabelText(Produto Produto){
		 this.Produtos = Produto;
		 this.labelChange.setText("Editar"); 
		 this.btnAdd.setText("Editar");
		 
		 this.Id_produto.setEditable(false);
		 
		 this.Id_produto.setText(String.valueOf(Produto.getId_produto()));
		 this.Nome.setText(Produto.getNome());
		 this.Valor_unitario.setText(String.valueOf(Produto.getValor_unitario()));
		 this.Estoque_minimo.setText(String.valueOf(Produto.getEstoque_minimo()));
		 this.Estoque_maximo.setText(String.valueOf(Produto.getEstoque_maximo()));
		 this.Estoque_atual.setText(String.valueOf(Produto.getEstoque_atual()));
		 this.Peso_bruto.setText(String.valueOf(Produto.getPeso_bruto()));
		 this.Peso_liquido.setText(String.valueOf(Produto.getPeso_liquido()));
		 this.Unidade_medida.setValue(Produto.getUnidade_medida());
		 this.Fornecedor.setValue(new DaoFornecedor().listarFornecedor(Produto.getFornecedor_Cnpj()));
		 this.Categoria.setValue(new DaoCategoria().listarCategoria(Produto.getId_categoria()));
		  
	  }
	 
	 public void adicionar() {
		
		if(validacaoCampos()) {
			
			Fornecedor forn = new Fornecedor();
			forn = Fornecedor.getValue();
			
			Categoria cat = new Categoria();
			cat = Categoria.getValue();
			
			Produto Produto = new Produto();
	    	
	    	Produto.setId_produto(Integer.parseInt(Id_produto.getText()));
	    	Produto.setNome(Nome.getText());
	    	Produto.setUnidade_medida(Unidade_medida.getValue().toString());
	    	Produto.setEstoque_minimo(Integer.parseInt(Estoque_minimo.getText()));
	    	Produto.setEstoque_maximo(Integer.parseInt(Estoque_maximo.getText()));
	    	Produto.setEstoque_atual(Integer.parseInt(Estoque_atual.getText()));
	    	Produto.setPeso_bruto(Float.parseFloat(Peso_bruto.getText()));
	    	Produto.setPeso_liquido(Float.parseFloat(Peso_liquido.getText()));
	    	Produto.setValor_unitario(Float.parseFloat(Valor_unitario.getText()));
	    	Produto.setFornecedor_Cnpj(forn.getCnpj());
	    	Produto.setId_categoria(cat.getId_categoria());


	    	if(new DaoProduto().inserirProduto(Produto)) {
	    
	    		new ShowAlert().sucessoAlert("Produto adicionado com sucesso!"); 
		    	Stage stage = (Stage) btnBack.getScene().getWindow(); 
			    ControllerViewListaProduto t = new ControllerViewListaProduto();
				t.start(stage);
	    	}
		}
	
	 }
	 
	 
	 private void editar() {
		if(validacaoCampos()) {
			Fornecedor forn = new Fornecedor();
			forn = Fornecedor.getValue();
			
			Categoria cat = new Categoria();
			cat = Categoria.getValue();
			
			Produto Produto = new Produto();
	    	
	    	Produto.setId_produto(Integer.parseInt(Id_produto.getText()));
	    	Produto.setNome(Nome.getText());
	    	Produto.setUnidade_medida(Unidade_medida.getValue().toString());
	    	Produto.setEstoque_minimo(Integer.parseInt(Estoque_minimo.getText()));
	    	Produto.setEstoque_maximo(Integer.parseInt(Estoque_maximo.getText()));
	    	Produto.setEstoque_atual(Integer.parseInt(Estoque_atual.getText()));
	    	Produto.setPeso_bruto(Float.parseFloat(Peso_bruto.getText()));
	    	Produto.setPeso_liquido(Float.parseFloat(Peso_liquido.getText()));
	    	Produto.setValor_unitario(Float.parseFloat(Valor_unitario.getText()));

	    	Produto.setFornecedor_Cnpj(forn.getCnpj());
	    	Produto.setId_categoria(cat.getId_categoria());
	    	
	    	new DaoProduto().alterarProduto(Produto);
	    	
		    if(new ShowAlert().sucessoAlert("Produto editado com sucesso!")) {
			    Stage stage = (Stage) btnBack.getScene().getWindow(); 
			    ControllerViewListaProduto t = new ControllerViewListaProduto();
				t.start(stage);
		    }
		   
		}
	 }
	 

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Categoria> listas = new DaoCategoria().listarCategoria();

	    ObservableList<Categoria> lista = FXCollections.observableArrayList(listas);
	    
	    Categoria.setItems(lista);
	    
	    ArrayList<Fornecedor> listasF = new DaoFornecedor().listarFornecedores();

	    ObservableList<Fornecedor> listaF = FXCollections.observableArrayList(listasF);
	    
	    Fornecedor.setItems(listaF);
	    
	    Unidade_medida.getItems().setAll("Quilo (KG)","Grama (GR)", "Litro (L)", "Mililitro (ML)");
	}
	
	public boolean validacaoCampos() {
		if(Id_produto.getText().isEmpty() | Nome.getText().isEmpty() | Valor_unitario.getText().isEmpty() | Valor_unitario.getText().isEmpty()|
			Estoque_minimo.getText().isEmpty() | Estoque_maximo.getText().isEmpty() | Estoque_maximo.getText().isEmpty() | Peso_bruto.getText().isEmpty()|
			Peso_liquido.getText().isEmpty()) {
			
			new ShowAlert().validacaoAlert();
			
			return false;
		}
		
		return true;
	}
	
	 @FXML
	 void VoltarPainel(ActionEvent event) {
		 Stage stage = (Stage) btnBack.getScene().getWindow(); 
	     ControllerViewPainel t = new ControllerViewPainel();
		 t.start(stage);
	 }
	 
	 @FXML
     void Sair(ActionEvent event) {
		 System.clearProperty("Cpf");
		 System.clearProperty("Nome");
		 System.clearProperty("Cargo");
	
		 if(new ShowAlert().sucessoAlert("Tem certeza que deseja realizar o logout?")) {
			 Stage stage = (Stage) btnBack.getScene().getWindow(); 
		     ControllerViewLogin t = new ControllerViewLogin();
			 t.start(stage);
		 }
    }
}
