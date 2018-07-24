/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system.config;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import library.system.model.DbConfig;
import library.system.util.DbConfigLoader;

/**
 * FXML Controller class
 *
 * @author Sithu
 */
public class DbconfigController implements Initializable {

    @FXML
    private TextField hostField;
    @FXML
    private Spinner<Integer> portSpinner;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DbConfig dbConfig = DbConfigLoader.loadDbConfig();

        if(dbConfig == null){
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(3300, 3350, 3306);
            portSpinner.setValueFactory(valueFactory);
        }else{
            hostField.setText(dbConfig.getHost());
            usernameField.setText(dbConfig.getUser());
            passwordField.setText(dbConfig.getPassword());
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(3300, 3350, dbConfig.getPort());
            portSpinner.setValueFactory(valueFactory);
        }
        

        

    }

    @FXML
    private void saveDatabaseConfig(ActionEvent event) {

        String host = hostField.getText();
        String port = portSpinner.getValue().toString();
        String user = usernameField.getText();
        String password = passwordField.getText();
        
        DbConfigLoader.saveDbConfig(new DbConfig(host, Integer.parseInt(port), user, password));
        
        Stage currentStage = (Stage)hostField.getScene().getWindow();
        currentStage.close();
        
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        
        Stage currentStage = (Stage)hostField.getScene().getWindow();
        currentStage.close();
    }

}
