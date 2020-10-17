package resources.view.pagamento;

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
import main.dao.DaoPagamento;
import main.entity.Pagamento;
import main.util.ShowAlert;
import resources.view.login.ControllerViewLogin;
import resources.view.painel.ControllerViewPainel;

public class ControllerViewListaPagamento implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private TableView<Pagamento> tabela;

    @FXML
    private TableColumn<Pagamento, String> Nome;

    @FXML
    private TableColumn<Pagamento, Integer> Id_pagamento;
    
    ArrayList<Pagamento> listas = new DaoPagamento().listarPagamento();

    ObservableList<Pagamento> lista = FXCollections.observableArrayList(listas);

    @FXML
    void AddPagamento(ActionEvent event) {
    	 Stage stage = (Stage) btnAdd.getScene().getWindow(); 
	     ControllerViewPagamento t = new ControllerViewPagamento();
		 t.start(stage);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Nome.setCellValueFactory(new PropertyValueFactory<Pagamento, String>("Nome"));
		Id_pagamento.setCellValueFactory(new PropertyValueFactory<Pagamento, Integer>("Id_pagamento"));
		
	    tabela.setItems(lista);
	    tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    addButtonToTable();
	    addButtonExcluir();
	}

	private void addButtonToTable() {
        TableColumn<Pagamento, Void> colBtn = new TableColumn("Editar");

        Callback<TableColumn<Pagamento, Void>, TableCell<Pagamento, Void>> cellFactory = new Callback<TableColumn<Pagamento, Void>, TableCell<Pagamento, Void>>() {
            @Override
            public TableCell<Pagamento, Void> call(final TableColumn<Pagamento, Void> param) {
                final TableCell<Pagamento, Void> cell = new TableCell<Pagamento, Void>() {

                    private final Button btn = new Button("Editar");{
                        btn.setOnAction((ActionEvent event) -> {
                        	
                            Pagamento Pagamento = getTableView().getItems().get(getIndex());
                            
                            try {
	                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Pagamento/CadastroPagamento.fxml"));
	                            Parent root = loader.load();
	
	                            ControllerViewPagamento controllerView = loader.getController();
	                            controllerView.setLabelText(Pagamento);
	                    
	                            Stage stage = new Stage();
	                            stage.setScene(new Scene(root));
	                            stage.setTitle("Editar Pagamento - Uni Venda");
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
        TableColumn<Pagamento, Void> colBtn = new TableColumn("Excluir");

        Callback<TableColumn<Pagamento, Void>, TableCell<Pagamento, Void>> cellFactory = new Callback<TableColumn<Pagamento, Void>, TableCell<Pagamento, Void>>() {
            @Override
            public TableCell<Pagamento, Void> call(final TableColumn<Pagamento, Void> param) {
                final TableCell<Pagamento, Void> cell = new TableCell<Pagamento, Void>() {

                    private final Button btn = new Button("Excluir");{
                        btn.setOnAction((ActionEvent event) -> {
                        	
                            Pagamento Pagamento = getTableView().getItems().get(getIndex());
                            
                            try {
	                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Pagamento/ExcluirPagamento.fxml"));
	                            Parent root = loader.load();
	
	                            ControllerViewExcluirPagamento controllerView = loader.getController();
	                            controllerView.setLabelText(Pagamento);
	                    
	                            Stage stage = new Stage();
	                            stage.setScene(new Scene(root));
	                            stage.setTitle("Excluir Pagamento - Uni Venda");
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
		 Stage stage = (Stage) btnAdd.getScene().getWindow(); 
	     ControllerViewPainel t = new ControllerViewPainel();
		 t.start(stage);
	 }

	public void start(Stage primaryStage) {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/view/Pagamento/ListaPagamento.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Lista Pagamentos - Uni Venda");
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
