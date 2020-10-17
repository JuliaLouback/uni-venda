package resources.view.cliente;

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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.dao.DaoCliente;
import main.entity.Cliente;
import main.util.ShowAlert;
import resources.view.login.ControllerViewLogin;
import resources.view.painel.ControllerViewPainel;
import resources.view.painel.ControllerViewPainelCaixa;

public class ControllerViewListaCliente implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private TableView<Cliente> tabela;

    @FXML
    private TableColumn<Cliente, String> Nome;

    @FXML
    private TableColumn<Cliente, String> Cpf;

    @FXML
    private TableColumn<Cliente, String> Email;
    
    ArrayList<Cliente> listas = new DaoCliente().listarCliente();

    ObservableList<Cliente> lista = FXCollections.observableArrayList(listas);

    @FXML
    void AddCliente(ActionEvent event) {
    	 Stage stage = (Stage) btnAdd.getScene().getWindow(); 
	     ControllerViewCliente t = new ControllerViewCliente();
		 t.start(stage);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Nome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Nome"));
		Cpf.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Cpf"));
		Email.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Email"));
		
	    tabela.setItems(lista);
	    tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    addButtonToTable();
	    addButtonExcluir();
	}

	private void addButtonToTable() {
        TableColumn<Cliente, Void> colBtn = new TableColumn("Editar");

        Callback<TableColumn<Cliente, Void>, TableCell<Cliente, Void>> cellFactory = new Callback<TableColumn<Cliente, Void>, TableCell<Cliente, Void>>() {
            @Override
            public TableCell<Cliente, Void> call(final TableColumn<Cliente, Void> param) {
                final TableCell<Cliente, Void> cell = new TableCell<Cliente, Void>() {

                    private final Button btn = new Button("Editar");{
                        btn.setOnAction((ActionEvent event) -> {
                        	
                            Cliente cliente = getTableView().getItems().get(getIndex());
                            
                            try {
	                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/cliente/CadastroCliente.fxml"));
	                            Parent root = loader.load();
	
	                            ControllerViewCliente controllerView = loader.getController();
	                            controllerView.setLabelText(cliente);
	                    
	                            Stage stage = new Stage();
	                            stage.setScene(new Scene(root));
	                            stage.setTitle("Editar Cliente - Uni Venda");
	                			stage.setResizable(false);
	                			stage.centerOnScreen();
	                			stage.getIcons().add(new Image("/resources/img/money.png"));
	                            stage.show();
	                            
	                            Stage stages = (Stage) btn.getScene().getWindow();
	                            stages.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            
                        });
                       
                        btn.setPrefWidth(280);
                        btn.setStyle("-fx-background-color: #28A745;-fx-text-fill:#ffffff; "); 
                       
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

        colBtn.setMinWidth(100);

        tabela.getColumns().add(colBtn);

    }
	
	private void addButtonExcluir() {
        TableColumn<Cliente, Void> colBtn = new TableColumn("Excluir");

        Callback<TableColumn<Cliente, Void>, TableCell<Cliente, Void>> cellFactory = new Callback<TableColumn<Cliente, Void>, TableCell<Cliente, Void>>() {
            @Override
            public TableCell<Cliente, Void> call(final TableColumn<Cliente, Void> param) {
                final TableCell<Cliente, Void> cell = new TableCell<Cliente, Void>() {

                    private final Button btn = new Button("Excluir");{
                        btn.setOnAction((ActionEvent event) -> {
                        	
                            Cliente cliente = getTableView().getItems().get(getIndex());
                            
                            try {
	                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/cliente/ExcluirCliente.fxml"));
	                            Parent root = loader.load();
	
	                            ControllerViewExcluirCliente controllerView = loader.getController();
	                            controllerView.setLabelText(cliente);
	                    
	                            Stage stage = new Stage();
	                            stage.setScene(new Scene(root));
	                            stage.setTitle("Excluir Cliente - Uni Venda");
	                			stage.setResizable(false);
	                			stage.centerOnScreen();
	                			stage.getIcons().add(new Image("/resources/img/money.png"));
	                            stage.show();
	                            
	                            Stage stages = (Stage) btn.getScene().getWindow();
	                            stages.close();
                            } catch (IOException e) {
                                e.printStackTrace();
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

        tabela.getColumns().add(colBtn);

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

	public void start(Stage primaryStage) {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/view/cliente/ListaCliente.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Lista Clientes - Uni Venda");
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/resources/img/money.png"));
			primaryStage.show();
			
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
}
