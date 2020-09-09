package resources.view.fornecedor;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.dao.DaoFornecedor;
import main.entity.Fornecedor;

public class ControllerViewListaFornecedor implements Initializable{

    @FXML
    private TableView<Fornecedor> tabela;

    @FXML
    private TableColumn<Fornecedor, String> Nome_empresa;

    @FXML
    private TableColumn<Fornecedor, String> Cnpj;

    @FXML
    private TableColumn<Fornecedor, String> Email;


    @FXML
    private Button btnAdd;

   
	ArrayList<Fornecedor> listas = new DaoFornecedor().listarFornecedores();

    ObservableList<Fornecedor> lista = FXCollections.observableArrayList(listas);
    		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Nome_empresa.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("Nome_empresa"));
		Cnpj.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("Cnpj"));
		Email.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("Email"));
		
	    tabela.setItems(lista);
	    addButtonToTable();
		
	}
	
	 @FXML
	 void AddFornecedor(ActionEvent event) {
		 Stage stage = (Stage) btnAdd.getScene().getWindow(); 
	     ControllerViewFornecedor t = new ControllerViewFornecedor();
		 t.start(stage);
	 }

	public void start(Stage primaryStage) {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/view/fornecedor/ListaFornecedor.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Lista Fornecedores - Uni Venda");
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	private void addButtonToTable() {
        TableColumn<Fornecedor, Void> colBtn = new TableColumn("Editar");

        Callback<TableColumn<Fornecedor, Void>, TableCell<Fornecedor, Void>> cellFactory = new Callback<TableColumn<Fornecedor, Void>, TableCell<Fornecedor, Void>>() {
            @Override
            public TableCell<Fornecedor, Void> call(final TableColumn<Fornecedor, Void> param) {
                final TableCell<Fornecedor, Void> cell = new TableCell<Fornecedor, Void>() {

                    private final Button btn = new Button("Editar");{
                        btn.setOnAction((ActionEvent event) -> {
                            Fornecedor fornecedor = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + fornecedor);
                            
                            Stage stage = (Stage) btn.getScene().getWindow(); 
                            stage.setUserData(fornecedor);
                   	     	ControllerViewFornecedor forn = new ControllerViewFornecedor();
                   	     	forn.start(stage);
                        });
                        btn.setMinWidth(100);
                        btn.setStyle("-fx-background-color: #e0e0e0; "); 
                        
                      
            			
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

        tabela.getColumns().add(colBtn);

    }

}

