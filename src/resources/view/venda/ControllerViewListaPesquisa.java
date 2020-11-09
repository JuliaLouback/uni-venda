package resources.view.venda;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.dao.DaoCaixa;
import main.dao.DaoCliente;
import main.dao.DaoFuncionario;
import main.dao.DaoVenda;
import main.dao.DaoVenda;
import main.entity.Caixa;
import main.entity.Venda;
import main.entity.Cliente;
import main.entity.Funcionario;
import main.util.ShowAlert;

import resources.view.login.ControllerViewLogin;
import resources.view.painel.ControllerViewPainel;
import resources.view.painel.ControllerViewPainelCaixa;

public class ControllerViewListaPesquisa implements Initializable {
	
    @FXML
    private Button btnPesquisa;

    @FXML
    private Button btnAdd;
   
    @FXML
    private Button btnLimpar;
    
    @FXML
    private ComboBox<Cliente> Cliente_combo;

    @FXML
    private ComboBox<Funcionario> Funcionario_combo;

    @FXML
    private DatePicker Data_inicio;

    @FXML
    private DatePicker Data_final;
    
    @FXML
    private TableView<Venda> tabela;

    @FXML
    private TableColumn<Venda, String> Cliente;

    @FXML
    private TableColumn<Venda, String> Funcionario;

    @FXML
    private TableColumn<Venda, String> Data_venda;

    @FXML
    private TableColumn<Venda,String> Valor;

    ArrayList<Venda> listas = new DaoVenda().listarVenda();
    
    @FXML
    void VoltarVenda(ActionEvent event) { 	
		Stage stage = (Stage) btnAdd.getScene().getWindow(); 
	    ControllerViewListaVenda t = new ControllerViewListaVenda();
		t.start(stage);
   
    } 
    
    @FXML
    void Limpar(ActionEvent event) {
    	Cliente_combo.setValue(null);
    	Funcionario_combo.setValue(null);

    	ObservableList<Venda> lista = FXCollections.observableArrayList(listas);

  	    tabela.setItems(lista);
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
  			Stage stage = (Stage) btnAdd.getScene().getWindow(); 
  			ControllerViewPainelCaixa t = new ControllerViewPainelCaixa();
  			t.start(stage);
  		}
  		else {
  			Stage stage = (Stage) btnAdd.getScene().getWindow(); 
  			ControllerViewPainel t = new ControllerViewPainel();
  			t.start(stage);
  		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Cliente.setCellValueFactory(new PropertyValueFactory<Venda, String>("Nome_Cliente"));
		Funcionario.setCellValueFactory(new PropertyValueFactory<Venda, String>("Nome_Funcionario"));
		Valor.setCellValueFactory(new PropertyValueFactory<Venda, String>("Valor_string"));

		Data_venda.setCellValueFactory(new PropertyValueFactory<Venda, String>("Data_string"));
		
	    ObservableList<Venda> lista = FXCollections.observableArrayList(listas);

	    tabela.setItems(lista);
	    tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    
	    ArrayList<Cliente> listaCliente = new DaoCliente().listarCliente();
	    ObservableList<Cliente> listaCli = FXCollections.observableArrayList(listaCliente);
	    Cliente_combo.setItems(listaCli);
	    
	    ArrayList<Funcionario> listaFuncionario = new DaoFuncionario().listarFuncionarios();
	    ObservableList<Funcionario> listaFun = FXCollections.observableArrayList(listaFuncionario);
	    Funcionario_combo.setItems(listaFun);
	}
	
	public void start(Stage primaryStage) {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/view/Venda/ListaPesquisa.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Lista Vendas - Uni Venda");
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/resources/img/money.png"));
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
    void Pesquisa(ActionEvent event) {
    	
    	ArrayList<Venda> listaFinal = new ArrayList<Venda>();
    	
    	
    	if(Cliente_combo.getValue() != null) {
    	
	    	if(Funcionario_combo.getValue() != null && Data_inicio.getValue() != null && Data_final.getValue() != null) {
	    		for (Venda venda : listas) {
	    	        if (venda.getCliente_Cpf().equals(Cliente_combo.getValue().getCpf()) && venda.getFuncionario_Cpf().equals(Funcionario_combo.getValue().getCpf()) && (venda.getData_Local().equals(Data_inicio.getValue()) | venda.getData_Local().equals(Data_final.getValue()))) {
	    	            listaFinal.add(venda);
	    	        }
	    	    }
	    		
	    	} 
	    	else if(Funcionario_combo.getValue() != null && Data_inicio.getValue() != null) {
	    		for (Venda venda : listas) {
	    	        if (venda.getCliente_Cpf().equals(Cliente_combo.getValue().getCpf()) && venda.getFuncionario_Cpf().equals(Funcionario_combo.getValue().getCpf()) && venda.getData_Local().equals(Data_inicio.getValue())) {
	    	            listaFinal.add(venda);
	    	        }
	    	    }
	    		
	    	} 
	    	else if(Funcionario_combo.getValue() != null) {
	    		for (Venda venda : listas) {
	    	        if (venda.getCliente_Cpf().equals(Cliente_combo.getValue().getCpf()) && venda.getFuncionario_Cpf().equals(Funcionario_combo.getValue().getCpf())) {
	    	            listaFinal.add(venda);
	    	        }
	    	    }
	    		
	    	} 
	    	else {
	    		for (Venda venda : listas) {
	    	        if (venda.getCliente_Cpf().equals(Cliente_combo.getValue().getCpf())) {
	    	            listaFinal.add(venda);
	    	        }
	    	    }    		
	    	}
    	}
    	
    	else if(Funcionario_combo.getValue()!= null) {
    		
    		if(Data_inicio.getValue() != null && Data_final.getValue() != null) {
	    		for (Venda venda : listas) {
	    	        if (venda.getFuncionario_Cpf().equals(Funcionario_combo.getValue().getCpf()) && (venda.getData_Local().equals(Data_inicio.getValue()) | venda.getData_Local().equals(Data_final.getValue()))) {
	    	            listaFinal.add(venda);
	    	        }
	    	    }
	    		
	    	} 
	    	else if(Data_inicio.getValue() != null) {
	    		for (Venda venda : listas) {
	    	        if (venda.getFuncionario_Cpf().equals(Funcionario_combo.getValue().getCpf()) && venda.getData_Local().equals(Data_inicio.getValue())) {
	    	            listaFinal.add(venda);
	    	        }
	    	    }
	    		
	    	} 
	    	else {
	    		for (Venda venda : listas) {
	    	        if (venda.getFuncionario_Cpf().equals(Funcionario_combo.getValue().getCpf())) {
	    	            listaFinal.add(venda);
	    	        }
	    	    }
	    		
	    	} 
    	}
    	
    	else if(Data_inicio.getValue()!= null && Data_final.getValue() != null) {
    	
    		for (Venda venda : listas) {
    	        if (venda.getData_Local().equals(Data_inicio.getValue()) | venda.getData_Local().equals(Data_final.getValue())) {
    	            listaFinal.add(venda);
    	        }
    	    }
    	
    	}
    	
    	else if(Data_inicio.getValue()!= null) {
        	
    		for (Venda venda : listas) {
    	        if (venda.getData_Local().equals(Data_inicio.getValue())) {
    	            listaFinal.add(venda);
    	        }
    	    }
    	
    	}
    	
		ObservableList<Venda> lista = FXCollections.observableArrayList(listaFinal);

  	    tabela.setItems(lista);
    }
	
}
