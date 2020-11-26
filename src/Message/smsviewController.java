/**
 * Sample Skeleton for 'smsview.fxml' Controller Class
 */

package Message;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import connectdb.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class smsviewController 
{
    
	Connection con;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtmob"
    private TextField txtmob; // Value injected by FXMLLoader

    @FXML // fx:id="txtsms"
    private TextArea txtsms; // Value injected by FXMLLoader

    @FXML
    void doSMS(ActionEvent event) 
    {
    	System.out.println("******");
    	String resp=sms.SST_SMS.bceSunSoftSend(txtmob.getText(),txtsms.getText() );
    	if(resp.contains("successfully"))
			System.out.println("Sent...");
	else
		if(resp.contains("Unknown"))
			System.out.println("Check Internet connection");
		else
			System.out.println("Invalid Mobile Number");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize()
    {
        con=DBConnection.doConnect();    
    }
}
