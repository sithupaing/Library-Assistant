/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system.memberlist;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.system.dao.MemberDAO;
import library.system.model.Member;

/**
 * FXML Controller class
 *
 * @author Sithu
 */
public class MemberlistController implements Initializable {

    @FXML
    private TableView<Member> memberTable;
    @FXML
    private MenuItem editItem;
    @FXML
    private MenuItem deleteItem;
    @FXML
    private TableColumn<Member, Integer> idColumn;
    @FXML
    private TableColumn<Member, String> nameColumn;
    @FXML
    private TableColumn<Member, String> mobileColumn;
    @FXML
    private TableColumn<Member, String> addressColumn;
    
    private MemberDAO memberDAO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        memberDAO = new MemberDAO();
        initColum();
        loadTableData();
    }    

    @FXML
    private void loadMemberEditView(ActionEvent event) {
    }

    @FXML
    private void deleteMember(ActionEvent event) {
        
    }

    private void initColum() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        mobileColumn.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    private void loadTableData() {
        
        
        try {
            ObservableList<Member> members = memberDAO.getMembers();
             memberTable.getItems().setAll(members);
        } catch (SQLException ex) {
            Logger.getLogger(MemberlistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
    }
    
}
