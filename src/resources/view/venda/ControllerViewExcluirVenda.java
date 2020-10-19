package resources.view.venda;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.dao.DaoCliente;
import main.dao.DaoVenda;
import main.entity.Venda;
import main.entity.Venda_Pagamento;
import main.entity.Venda_Produto;
import main.util.ShowAlert;
import resources.view.cliente.ControllerViewListaCliente;
import resources.view.login.ControllerViewLogin;
import resources.view.painel.ControllerViewPainel;
import resources.view.painel.ControllerViewPainelCaixa;

public class ControllerViewExcluirVenda implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private TableView<Venda_Produto> tabelaProduto;

    @FXML
    private TableColumn<Venda_Produto, String> TProduto;

    @FXML
    private TableColumn<Venda_Produto, Integer> TQuantidade;

    @FXML
    private TableColumn<Venda_Produto, Float> TValot;

    @FXML
    private TableView<Venda_Pagamento> TabelaPag;

    @FXML
    private TableColumn<Venda_Pagamento, String> TForma;

    @FXML
    private TableColumn<Venda_Pagamento, Float> TValorP;

    @FXML
    private Label Nome_Cliente;

    @FXML
    private Label Nome_Funcionario;

    @FXML
    private Label Data_Venda;

    @FXML
    private Label Cpf_Cliente;

    @FXML
    private Label Cpf_Funcionario;

    @FXML
    private Label Valor_total;

    private Integer id_final;
    
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
    void btnBack(ActionEvent event) {
    	Stage stage = (Stage) btnBack.getScene().getWindow(); 
	    ControllerViewListaVenda t = new ControllerViewListaVenda();
		t.start(stage);
    }

    @FXML
    void btnExcluir(ActionEvent event) {
    	
    	if(new DaoVenda().excluirVendaProduto(id_final)) {
        	
    		if(new DaoVenda().excluirVendaPagamento(id_final)) {
    			
    			if(new DaoVenda().excluirVenda(id_final)) {
        			
			    	new ShowAlert().sucessoAlert("Venda excluida com sucesso!");
			    	
					Stage stage = (Stage) btnBack.getScene().getWindow(); 
				    ControllerViewListaVenda t = new ControllerViewListaVenda();
					t.start(stage);
    			}
    		}
    		
    	}
    }

	public void setLabelText(Venda venda) {
		Nome_Cliente.setText(venda.getNome_Cliente());
		Nome_Funcionario.setText(venda.getNome_Funcionario());
		Cpf_Cliente.setText(venda.getCliente_Cpf());
		Cpf_Funcionario.setText(venda.getFuncionario_Cpf());
		Data_Venda.setText(venda.getData_string());
		Valor_total.setText(venda.getValor_string());
		id_final = venda.getId_venda();
		listarProduto(venda.getId_venda());
		listarPagamento(venda.getId_venda());

	}
	
	  public void listarProduto(int Id_venda) {
		  ArrayList<Venda_Produto> listas = new DaoVenda().listarVendaProduto(Id_venda);

		  
		    ObservableList<Venda_Produto> lista = FXCollections.observableArrayList(listas);

		    tabelaProduto.setItems(lista);
		    
		}
	  
	  public void listarPagamento(int Id_venda) {
		  ArrayList<Venda_Pagamento> listas = new DaoVenda().listarVendaPagamento(Id_venda);

		  
		    ObservableList<Venda_Pagamento> lista = FXCollections.observableArrayList(listas);

		    TabelaPag.setItems(lista);
		    
		}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TProduto.setCellValueFactory(new PropertyValueFactory<Venda_Produto, String>("Nome"));
		TQuantidade.setCellValueFactory(new PropertyValueFactory<Venda_Produto, Integer>("Quantidade"));
		TValot.setCellValueFactory(new PropertyValueFactory<Venda_Produto, Float>("Valor_Total"));

		tabelaProduto.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    
		TForma.setCellValueFactory(new PropertyValueFactory<Venda_Pagamento, String>("Nome"));
		TValorP.setCellValueFactory(new PropertyValueFactory<Venda_Pagamento, Float>("Valor"));

		TabelaPag.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    
	}
	  
	  
	    

}
