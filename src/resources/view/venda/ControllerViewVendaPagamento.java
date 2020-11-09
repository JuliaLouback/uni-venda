package resources.view.venda;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
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
import main.dao.DaoCaixa;
import main.dao.DaoCategoria;
import main.dao.DaoCliente;
import main.dao.DaoFornecedor;
import main.dao.DaoPagamento;
import main.dao.DaoProduto;
import main.dao.DaoVenda;
import main.entity.*;
import resources.view.login.ControllerViewLogin;
import resources.view.painel.ControllerViewPainel;
import resources.view.painel.ControllerViewPainelCaixa;
import resources.view.painel.ControllerViewPainelRH;

public class ControllerViewVendaPagamento implements Initializable {

    @FXML
    private Label labelChange;
    
    @FXML
    private Label labelTotalRestante;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private ComboBox<Cliente> Cliente;

    @FXML
    private ComboBox<Pagamento> Forma_pagamento;

    @FXML
    private TableView<Venda_Pagamento> TabelaForma;

    @FXML
    private TableColumn<Venda_Pagamento, Float> Valor;

    @FXML
    private Button btnAddPagamento;

    @FXML
    private TableColumn<Venda_Pagamento, String> Nome;
    
   
    @FXML
    private TextField Valor_Forma;
    
    @FXML
    private Label labelTotal;

    private List<Venda_Produto> listaVendaP = new ArrayList<Venda_Produto>();		
    
    private List<Venda_Pagamento> listaVendaF = new ArrayList<Venda_Pagamento>();		
    
    private float ValorSomado = 0;
    
    private float ValorTotal = 0;

    @FXML
    void BackButton(ActionEvent event) {
    	try {
   		 FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Venda/CadastroVenda.fxml"));
            Parent root = loader.load();

            ControllerViewVenda controllerView = loader.getController();
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
    		
    		Venda venda = new Venda();
    		venda.setCliente_Cpf(Cliente.getValue().getCpf());
    		venda.setFuncionario_Cpf(System.getProperty("Cpf"));
    		venda.setValor_total(ValorTotal);
    		venda.setData_venda(LocalDateTime.now());
    		
    		long id = new DaoVenda().inserirVenda(venda);
    		
    		for(Venda_Produto vendaP : listaVendaP) {
    			vendaP.setVenda_Id_Venda(id);
    			new DaoVenda().inserirVendaProduto(vendaP);
    			
    			Produto produto = vendaP.getProduto();
    			produto.setEstoque_atual(produto.getEstoque_atual() - vendaP.getQuantidade());
    			new DaoProduto().alterarProdutoQuantidade(produto);
        	}
    		
    		for(Venda_Pagamento vendaF : listaVendaF) {
        		vendaF.setVenda_Id_Venda(id);
        		new DaoVenda().inserirVendaPagamento(vendaF);
        	}
    		
    		Caixa caixa = new Caixa();
    		Caixa caixaUm = new DaoCaixa().listarUmCaixaCpf(System.getProperty("Cpf"));
    		
	    	caixa.setValor_final(caixaUm.getValor_final() + ValorTotal);
	    	caixa.setFuncionario_Cpf(System.getProperty("Cpf"));
	    	
	    	new DaoCaixa().alterarCaixa(caixa);
    		
    		new ShowAlert().informationAlert("Venda efetuada com sucesso!");
        	
    		Stage stage = (Stage) btnBack.getScene().getWindow(); 
		    ControllerViewListaVenda t = new ControllerViewListaVenda();
			t.start(stage);
    	}
    }
    
    @FXML
    void adicionarForma(ActionEvent event) {
    	if(validacaoCampos()) {
    		
    		System.out.println(ValorSomado);
    		System.out.println(Valor_Forma.getText());
    		if(Float.parseFloat(Valor_Forma.getText()) > ValorSomado) {
    			new ShowAlert().erroAlert("O valor inserido é maior que o valor total restante!");
    		} else {
    			
    			if(verificarListaObj(Forma_pagamento.getValue())) {
    				new ShowAlert().erroAlert("Forma de Pagamento já adicionado!");
    			} else {
    			    	    	
    	    		ValorSomado = ValorSomado - Float.parseFloat(Valor_Forma.getText());
    	    		
    	    		Venda_Pagamento vendaF = new Venda_Pagamento();
    	    		vendaF.setPagamento(Forma_pagamento.getValue());
    	    		vendaF.setValor(Float.parseFloat(Valor_Forma.getText()));
    	    		vendaF.setNome(Forma_pagamento.getValue().getNome());
    	    		
    	    		listaVendaF.add(vendaF);
    	    		listarPagamento();
    			}
    		}
    	}
    }
    
    public void listarPagamento() {
		Nome.setCellValueFactory(new PropertyValueFactory<Venda_Pagamento, String>("Nome"));
		Valor.setCellValueFactory(new PropertyValueFactory<Venda_Pagamento, Float>("Valor"));

	    TabelaForma.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    
	    ObservableList<Venda_Pagamento> lista = FXCollections.observableArrayList(listaVendaF);

	    TabelaForma.setItems(lista);
	    
	    labelTotalRestante.setText("R$ "+ String.format("%.02f", ValorSomado));
	}
    
    public boolean validacaoCampos() {
		if(Forma_pagamento.getValue() == null | Valor_Forma.getText().isEmpty()) {
			
			new ShowAlert().validacaoAlert();
			
			return false;
		}
		
		return true;
	}
   
    public boolean validacaoCamposVenda() {
		if(Cliente.getValue() == null | listaVendaF.size() == 0 | ValorSomado != 0) {
			
			new ShowAlert().validacaoVendaForma();
			
			return false;
		}
		
		return true;
	}
    
    
    public boolean verificarListaObj(Pagamento pagamento) {
    	
    	for(Venda_Pagamento vendaF : listaVendaF) {
    		if(vendaF.getPagamento().getId_pagamento() == pagamento.getId_pagamento()) {
    			return true;
    		}
    	}
    	
    	return false;
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

	    ArrayList<Pagamento> listaPagamento = new DaoPagamento().listarPagamento();
	    ObservableList<Pagamento> listaPag = FXCollections.observableArrayList(listaPagamento);
	    Forma_pagamento.setItems(listaPag);

	    addButtonExcluir();
	}
	
	public void setLabelText(List<Venda_Produto> VendaP, float valor_total, Cliente cliente){
		 Cliente.setValue(cliente);
		 labelTotal.setText("R$ "+ valor_total);
		 listaVendaP = VendaP;
		 ValorSomado = valor_total;
		 ValorTotal = valor_total;
		 labelTotalRestante.setText("R$ "+ valor_total);
	}

	private void addButtonExcluir() {
        TableColumn<Venda_Pagamento, Void> colBtn = new TableColumn("Excluir");

        Callback<TableColumn<Venda_Pagamento, Void>, TableCell<Venda_Pagamento, Void>> cellFactory = new Callback<TableColumn<Venda_Pagamento, Void>, TableCell<Venda_Pagamento, Void>>() {
            @Override
            public TableCell<Venda_Pagamento, Void> call(final TableColumn<Venda_Pagamento, Void> param) {
                final TableCell<Venda_Pagamento, Void> cell = new TableCell<Venda_Pagamento, Void>() {

                    private final Button btn = new Button("Excluir");{
                        btn.setOnAction((ActionEvent event) -> {
                        	
                            Venda_Pagamento VendaF = getTableView().getItems().get(getIndex());
                            
                            if(new ShowAlert().sucessoAlert("Tem certeza que deseja excluir esse pagamento?")) { 
                            	ValorSomado = ValorSomado + VendaF.getValor();
                            	listaVendaF.remove(VendaF);
                            	listarPagamento();
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

        TabelaForma.getColumns().add(colBtn);

    }
}
