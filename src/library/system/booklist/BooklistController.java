/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system.booklist;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import library.system.dao.BookDAO;
import library.system.editbook.EditbookController;
import library.system.model.Book;

/**
 * FXML Controller class
 *
 * @author Sithu
 */
public class BooklistController implements Initializable {

    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, Integer> idColumn;
    @FXML
    private TableColumn<Book, String> titileColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> publisherColumn;

    private BookDAO bookDAO;
    @FXML
    private MenuItem editItem;
    @FXML
    private MenuItem deleteItem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookDAO = new BookDAO();
        initColumn();
        loadTableData();
    }

    private void initColumn() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titileColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
    }

    private void loadTableData() {

//            ObservableList<Book> list = FXCollections.observableArrayList();
//            
//            list.add(new Book(1,"Android Development","John","American Press"));
//            list.add(new Book(2,"Java Development","Steven","Oxford Press"));
//            list.add(new Book(3,"Pyhton Development","Sithu","Myanmar Book store"));
        try {
            ObservableList<Book> list = bookDAO.getBooks();
            bookTable.getItems().setAll(list);
        } catch (SQLException ex) {
            Logger.getLogger(BooklistController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void deleteBook(ActionEvent event) {

        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();

        if (selectedBook == null) {
            System.out.println("Please select  the book which you want to delete.");
            return;
        }

        try {
            bookDAO.deleteBook(selectedBook.getId());
            bookTable.getItems().remove(selectedBook);
        } catch (SQLException ex) {
            Logger.getLogger(BooklistController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void loadEditView(ActionEvent event) throws IOException {

        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();

        if (selectedBook == null) {
            System.out.println("Please select  the book which you want to delete.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/system/editbook/editbook.fxml"));
        Parent root = loader.load();
        EditbookController controller = loader.getController();
        controller.setInitData(selectedBook);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initOwner(bookTable.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
        
        loadTableData();
        
    }

}
