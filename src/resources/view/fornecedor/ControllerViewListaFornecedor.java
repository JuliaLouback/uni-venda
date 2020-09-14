package resources.view.fornecedor;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.javafx.fxml.LoadListener;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.dao.DaoFornecedor;
import main.entity.Fornecedor;
import resources.view.painel.ControllerViewPainel;

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
	    tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    addButtonToTable();
	    addButtonExcluir();
		
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
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/resources/img/money.png"));
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
                            
                            try {
	                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/fornecedor/CadastroFornecedor.fxml"));
	                            Parent root = loader.load();
	
	                            ControllerViewFornecedor controllerView = loader.getController();
	                            controllerView.setLabelText(fornecedor);
	                    
	                            Stage stage = new Stage();
	                            stage.setScene(new Scene(root));
	                            stage.setTitle("Editar Fornecedor - Uni Venda");
	                            stage.centerOnScreen();
	                			stage.getIcons().add(new Image("/resources/img/money.png"));
	                			stage.setResizable(false);
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
        TableColumn<Fornecedor, Void> colBtn = new TableColumn("Excluir");

        Callback<TableColumn<Fornecedor, Void>, TableCell<Fornecedor, Void>> cellFactory = new Callback<TableColumn<Fornecedor, Void>, TableCell<Fornecedor, Void>>() {
            @Override
            public TableCell<Fornecedor, Void> call(final TableColumn<Fornecedor, Void> param) {
                final TableCell<Fornecedor, Void> cell = new TableCell<Fornecedor, Void>() {

                    private final Button btn = new Button("Excluir");{
                        btn.setOnAction((ActionEvent event) -> {
                        	
                            Fornecedor fornecedor = getTableView().getItems().get(getIndex());
                            
                            try {
	                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/fornecedor/ExcluirFornecedor.fxml"));
	                            Parent root = loader.load();
	
	                            ControllerViewExcluirFornecedor controllerView = loader.getController();
	                            controllerView.setLabelText(fornecedor);
	                    
	                            Stage stage = new Stage();
	                            stage.setScene(new Scene(root));
	                            stage.setTitle("Excluir Fornecedor - Uni Venda");
	                            stage.centerOnScreen();
	                			stage.getIcons().add(new Image("/resources/img/money.png"));
	                			stage.setResizable(false);

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
		 Stage stage = (Stage) btnAdd.getScene().getWindow(); 
	     ControllerViewPainel t = new ControllerViewPainel();
		 t.start(stage);
	 }

}

