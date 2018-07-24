/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system.addbook;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import library.system.dao.BookDAO;
import library.system.model.Book;

/**
 * FXML Controller class
 *
 * @author Sithu
 */
public class AddbookController implements Initializable {

    @FXML
    private JFXTextField idField;
    @FXML
    private JFXTextField titleField;
    @FXML
    private JFXTextField authorField;
    @FXML
    private JFXTextField publisherField;
    @FXML
    private JFXButton saveBtn;
    
    private BookDAO bookDAO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookDAO = new BookDAO();
    }    

    @FXML
    private void saveBookInfo(ActionEvent event) {
        int id = 0;
        try{
            String idStr = idField.getText();
            if(!idStr.isEmpty()){
               id = Integer.parseInt(idField.getText());  
            }    
        }catch(NumberFormatException e){
            System.out.println("Invalid input for id field.");
            return;
        }
        
        String title = titleField.getText();
        String author = authorField.getText();
        String publisher = publisherField.getText();
        
        if(title.isEmpty()||author.isEmpty()||publisher.isEmpty()){
            System.out.println("Please fill all required fields.");
            return;
        }
        
        Book book = new Book(id,title,author,publisher);
        
        try {
            bookDAO.saveBook(book);
            System.out.println("Success");
        } catch (SQLException ex) {
            System.out.println("Failed.");
            Logger.getLogger(AddbookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
