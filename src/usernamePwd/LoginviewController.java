/**
 * Sample Skeleton for 'Loginview.fxml' Controller Class
 */

package usernamePwd;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import connectdb.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginviewController {
	Connection con;
	PreparedStatement pst;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="pwd"
    private PasswordField pwd; // Value injected by FXMLLoader

    @FXML // fx:id="un"
    private TextField un; // Value injected by FXMLLoader

    @FXML // fx:id="rm"
    private CheckBox rm; // Value injected by FXMLLoader
    
    Stage dialogStage = new Stage();
    Scene scene;

    @FXML
    void doLogIn(ActionEvent event)
    {
    	 String uid = un.getText();
         String pw = pwd.getText();
     
         
         try{
             pst=con.prepareStatement("select * from admintbl where Username=? and Password=?");
             pst.setString(1, uid);
             pst.setString(2, pw);
             ResultSet tbl= pst.executeQuery();
             if(!tbl.next())
             {
                 infoBox("Please enter correct Username and Password", null, "Failed");
             }
             else
             {
                 infoBox("Login Successfull",null,"Success" );
                /* Node node = (Node)event.getSource();
                 dialogStage = (Stage) node.getScene().getWindow();*/
                 dialogStage.close();
                 
                 Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Dashboard/dashview.fxml")); 
     			Scene scene = new Scene(root,800,750);
     			
     			Stage primaryStage=new Stage();
     			primaryStage.setScene(scene);
     			primaryStage.show();
               //  scene = new Scene(FXMLLoader.load(getClass().getResource("FXMLMenu.fxml")));
                // dialogStage.setScene(scene);
                // dialogStage.show();
             }
         }
         catch(Exception e){
             e.printStackTrace();
         }
         
     }
     
     
     public static void infoBox(String Message, String headerText, String title)
     {
         Alert al = new Alert(AlertType.CONFIRMATION);
         al.setContentText(Message);
         al.setTitle(title);
         al.setHeaderText(headerText);
         al.showAndWait();
     }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize()
    {    
        con=DBConnection.doConnect();
    }
}
