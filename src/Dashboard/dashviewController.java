/**
 * Sample Skeleton for 'dashview.fxml' Controller Class
 */

package Dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class dashviewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    
    @FXML
    void doCarParkingGrid(MouseEvent event) 
    {
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("CarParkingtable/carparkingTableview.fxml")); 
			Scene scene = new Scene(root,600,600);
			
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//to hide the opened window
			 
			   /*Scene scene1=(Scene)txtMobile.getScene();
			   scene1.getWindow().hide();*/
			 
	    } 
	catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    
    @FXML
    void doRegisteration(MouseEvent event) 
    {
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("CarParkingCustomerReg/CustomerRegisterationview.fxml")); 
			Scene scene = new Scene(root,600,500);
			
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Customer Registeration Form....");
			
			//to hide the opened window
			 
			   /*Scene scene1=(Scene)txtMobile.getScene();
			   scene1.getWindow().hide();*/
			 
	    } 
	catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void doVehicleEntry(MouseEvent event)
    {
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("VehicleEntry/vehicleEntryview.fxml")); 
			Scene scene = new Scene(root,650,658);
			
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Vehicle Entry Form....");
			
			//to hide the opened window
			 
			   /*Scene scene1=(Scene)txtMobile.getScene();
			   scene1.getWindow().hide();*/
			 
	    } 
	catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void doVehicleEntryGrid(MouseEvent event)
    {
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("VehicleEntrytable/vehicleEntryTableview.fxml")); 
			Scene scene = new Scene(root,600,600);
			
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//to hide the opened window
			 
			   /*Scene scene1=(Scene)txtMobile.getScene();
			   scene1.getWindow().hide();*/
			 
	    } 
	catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void doVehicleExit(MouseEvent event) 
    {
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("VehicleExit/VehicleExitview.fxml")); 
			Scene scene = new Scene(root,600,450);
			
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Vehicle Exit Form....");
			
			//to hide the opened window
			 
			   /*Scene scene1=(Scene)txtMobile.getScene();
			   scene1.getWindow().hide();*/
			 
	    } 
	catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void dopLayout(MouseEvent event)
    {
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("ParkingLayout/ParkingLayoutview.fxml")); 
			Scene scene = new Scene(root,600,450);
			
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Parking Layout....");
			
			//to hide the opened window
			 
			   /*Scene scene1=(Scene)txtMobile.getScene();
			   scene1.getWindow().hide();*/
			 
	    } 
	catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    
    @FXML
    void doSMS(MouseEvent event)
    {
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Message/smsview.fxml")); 
			Scene scene = new Scene(root,600,400);
			
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Message Service....");
			
			//to hide the opened window
			 
			   /*Scene scene1=(Scene)txtMobile.getScene();
			   scene1.getWindow().hide();*/
			 
	    } 
	catch(Exception e)
		{
			e.printStackTrace();
		}
    }


  
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() 
    {

    }
}
