package resources.view.venda;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.util.ShowAlert;
import main.dao.DaoCategoria;
import main.dao.DaoCliente;
import main.dao.DaoPagamento;
import main.dao.DaoProduto;
import main.entity.*;
import resources.view.categoria.ControllerViewExcluirCategoria;
import resources.view.login.ControllerViewLogin;
import resources.view.painel.ControllerViewPainel;
import resources.view.painel.ControllerViewPainelCaixa;
import resources.view.painel.ControllerViewPainelRH;
import resources.view.produto.ControllerViewProduto;

public class ControllerViewVenda implements Initializable {

    @FXML
    private Label labelChange;
    
    @FXML
    private Label labelTotal;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private ComboBox<Cliente> Cliente;

    @FXML
    private Button btnAddPagamento;
    
    @FXML
    private ComboBox<Produto> Produto;

    @FXML
    private Button btnAddProduto;

    @FXML
    private TextField Quantidade_Produto;

    @FXML
    private TableView<Venda_Produto> TabelaProduto;
    
    @FXML
    private TableColumn<Venda_Produto, Integer> Quantidade;
    
    @FXML
    private TableColumn<Venda_Produto, String> NomeP;
    
    @FXML
    private TableColumn<Venda_Produto, Float> Valor_Total;
    
    private List<Venda_Produto> listaVendaP = new ArrayList<Venda_Produto>();		
    
    private float ValorTotal = 0;

    @FXML
    void BackButton(ActionEvent event) {
    	Stage stage = (Stage) btnAdd.getScene().getWindow(); 
	    ControllerViewListaVenda t = new ControllerViewListaVenda();
		t.start(stage);
    }

    @FXML
    void Sair(ActionEvent event) {
    	 System.clearProperty("Cpf");
		 System.clearProperty("Nome");
		 System.clearProperty("Cargo");
	
		 if(new ShowAlert().sucessoAlert("Tem certeza que deseja realizar o logout?")) {
			 Stage stage = (Stage) btnAdd.getScene().getWindow(); 
		     ControllerViewLogin t = new ControllerViewLogin();
			 t.start(stage);
		 }
    }

    @FXML
    void VoltarPainel(ActionEvent event) {
    	 String cargo = System.getProperty("Cargo");
	    	
 	    if(cargo.equals("Caixa")) {
 			Stage stage = (Stage) btnBack.getScene().getWindow(); 
 			ControllerViewPainelCaixa t = new ControllerViewPainelCaixa();
 			t.start(stage);
 		}
 		else {
 			Stage stage = (Stage) btnBack.getScene().getWindow(); 
 			ControllerViewPainel t = new ControllerViewPainel();
 			t.start(stage);
 		}
    }

    @FXML
    void adicionarVenda(ActionEvent event) {
    	if(validacaoCamposVenda()) {
    		
    		try {
	    		 FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Venda/CadastroVendaPagamento.fxml"));
	             Parent root = loader.load();
	
	             ControllerViewVendaPagamento controllerView = loader.getController();
	             controllerView.setLabelText(listaVendaP, ValorTotal, Cliente.getValue());
	     
	             Stage stage = new Stage();
	             stage.setScene(new Scene(root));
	             stage.setTitle("Venda - Uni Venda");
	             stage.centerOnScreen();
	 			 stage.getIcons().add(new Image("/resources/img/money.png"));
	 			 stage.setResizable(false);
	             stage.show();
	             
	             Stage stages = (Stage) btnAdd.getScene().getWindow();
	             stages.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }
   
    
    @FXML
    void adicionarProduto(ActionEvent event) {
    	if(validacaoCampos()) {
    		
    		if(Integer.parseInt(Quantidade_Produto.getText()) > Produto.getValue().getEstoque_atual()) {
    			new ShowAlert().erroAlert("O produto "+Produto.getValue().getNome()+" possui apenas " + Produto.getValue().getEstoque_atual() + " peças no estoque!");
    		} else {
    			
    			if(verificarListaObj(Produto.getValue())) {
    				new ShowAlert().erroAlert("Produto já adicionado!");
    			} else {
    				
    				Float valor_total = Produto.getValue().getValor_unitario() * Integer.parseInt(Quantidade_Produto.getText());
	    			
    	    		BigDecimal bd = new BigDecimal(valor_total).setScale(2, RoundingMode.HALF_EVEN);
    				
    	    		Venda_Produto vendaP = new Venda_Produto();
    	    		vendaP.setNome(Produto.getValue().getNome());
    	    		vendaP.setProduto(Produto.getValue());
    	    		vendaP.setQuantidade(Integer.parseInt(Quantidade_Produto.getText()));
    	    		vendaP.setValor_Total(bd.floatValue());
    	    		
    	    		ValorTotal = ValorTotal + bd.floatValue();
    	    		
    	    		listaVendaP.add(vendaP);
    	    		listarProduto();
    			}
    		}
    	}
    }
    
    public boolean verificarListaObj(Produto produto) {
    	
    	for(Venda_Produto vendaP : listaVendaP) {
    		if(vendaP.getProduto().getId_produto() == produto.getId_produto()) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public void listarProduto() {
		NomeP.setCellValueFactory(new PropertyValueFactory<Venda_Produto, String>("Nome"));
		Quantidade.setCellValueFactory(new PropertyValueFactory<Venda_Produto, Integer>("Quantidade"));
		Valor_Total.setCellValueFactory(new PropertyValueFactory<Venda_Produto, Float>("Valor_Total"));

	    TabelaProduto.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    
	    ObservableList<Venda_Produto> lista = FXCollections.observableArrayList(listaVendaP);

	    TabelaProduto.setItems(lista);
	    
	    labelTotal.setText("R$ "+ String.format("%.02f", ValorTotal));
	}
    
    public boolean validacaoCampos() {
		if(Produto.getValue() == null | Quantidade_Produto.getText().isEmpty()) {
			
			new ShowAlert().validacaoAlert();
			
			return false;
		}
		
		return true;
	}
    
    public boolean validacaoCamposVenda() {
		if(Cliente.getValue() == null | listaVendaP.size() == 0) {
			
			new ShowAlert().validacaoVenda();
			
			return false;
		}
		
		return true;
	}
    
    public void start(Stage primaryStage) {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/view/Venda/CadastroVenda.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Cadastro Venda - Uni Venda");
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/resources/img/money.png"));
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Cliente> listaCliente = new DaoCliente().listarCliente();
	    ObservableList<Cliente> listaCli = FXCollections.observableArrayList(listaCliente);
	    Cliente.setItems(listaCli);
	    
	    ArrayList<Produto> listaProduto = new DaoProduto().listarProdutoCombo();
	    ObservableList<Produto> listaPro = FXCollections.observableArrayList(listaProduto);
	    Produto.setItems(listaPro);
	    
	    labelTotal.setText("");
	    
	    addButtonExcluir();

	}

	private void addButtonExcluir() {
        TableColumn<Venda_Produto, Void> colBtn = new TableColumn("Excluir");

        Callback<TableColumn<Venda_Produto, Void>, TableCell<Venda_Produto, Void>> cellFactory = new Callback<TableColumn<Venda_Produto, Void>, TableCell<Venda_Produto, Void>>() {
            @Override
            public TableCell<Venda_Produto, Void> call(final TableColumn<Venda_Produto, Void> param) {
                final TableCell<Venda_Produto, Void> cell = new TableCell<Venda_Produto, Void>() {

                    private final Button btn = new Button("Excluir");{
                        btn.setOnAction((ActionEvent event) -> {
                        	
                            Venda_Produto VendaP = getTableView().getItems().get(getIndex());
                            
                            if(new ShowAlert().sucessoAlert("Tem certeza que deseja excluir esse produto?")) { 
                            	ValorTotal = ValorTotal - VendaP.getValor_Total();
                       		    labelTotal.setText("R$ "+ String.format("%.02f", ValorTotal));
                            	listaVendaP.remove(VendaP);
                            	listarProduto();
                            }
                            
                        });

                        
                        btn.setPrefWidth(280);
                        btn.setStyle("-fx-background-color:#e04b59;-fx-text-fill:#ffffff;"); 
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        colBtn.setMinWidth(90);

        TabelaProduto.getColumns().add(colBtn);

    }
	
	public void setLabelText(List<Venda_Produto> VendaP, float valor_total, Cliente cliente){
		 Cliente.setValue(cliente);
		 labelTotal.setText("R$ "+ String.format("%.02f", valor_total));
		 listaVendaP = VendaP;
		 ValorTotal = valor_total;
		 listarProduto();
	}
}
