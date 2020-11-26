/**
 * Sample Skeleton for 'ParkingLayoutview.fxml' Controller Class
 */

package ParkingLayout;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import connectdb.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;

public class ParkingLayoutviewController {
	Connection con;
	PreparedStatement pst;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="rd2"
    private RadioButton rd2; // Value injected by FXMLLoader

    @FXML // fx:id="rd"
    private ToggleGroup rd; // Value injected by FXMLLoader

    @FXML // fx:id="rd4"
    private RadioButton rd4; // Value injected by FXMLLoader

    @FXML // fx:id="txtflr"
    private TextField txtflr; // Value injected by FXMLLoader

    @FXML // fx:id="txtcap"
    private TextField txtcap; // Value injected by FXMLLoader
    
    @FXML
    private TextField txtslot;
    
    

    
    @FXML
    private TextField ocupslots;

    
    
    @FXML
    void doCheckSlots(ActionEvent event) 
    {
    	/*boolean jasus=false;
        if(rd2.isSelected()==true)
        {
        	int cap = 0,os = 0;
        	int flr=Integer.parseInt(txtflr.getText());
        	PreparedStatement pst=con.prepareStatement("select * from playout where Floor=?");
        	pst.setInt(1,flr);
        	ResultSet tbl=pst.executeQuery();
        	boolean jasus=false;
        	while(tbl.next())
        	{
        		jasus=true;
        		int fr=tbl.getInt("Floor");
        		cap=tbl.getInt("Capacity");
        		os=tbl.getInt("Occupied");
        		
        		txtflr.setText(String.valueOf(fr));
        		txtcap.setText(cap+"");  //we can also use pr+"" instead of (String.valueOf)
        		ocupslots.setText(String.valueOf(os));
        	}
        	int rs=cap-os;
        	txtslot.setText(String.valueOf(rs));
        	
        }
        	if(rd4.isSelected()==true)
            {
        		int cap4=0,os4=0;
            	int flr4=Integer.parseInt(txtflr.getText());
            	PreparedStatement pst1=con.prepareStatement("select * from playout where Floor=?");
            	pst1.setInt(1,flr4);
            	ResultSet tbl1=pst1.executeQuery();
            	boolean jasus1=false;
            	while(tbl1.next())
            	{
            		jasus=true;
            		int fr4=tbl1.getInt("Floor");
            		cap4=tbl1.getInt("Capacity");
            		os4=tbl1.getInt("Occupied");
            		
            		txtflr.setText(String.valueOf(fr4));
            		txtcap.setText(cap4+"");  //we can also use pr+"" instead of (String.valueOf)
            		ocupslots.setText(String.valueOf(os4));
            	}
            	int rs=cap4-os4;
            	txtslot.setText(String.valueOf(rs));
            }
            	
        	if(jasus==false)
        	{
        		System.out.println("Invalid Floor no.");
        	}
    */    
    }
    


    @FXML
    void doClose(ActionEvent event) 
    {
    	Alert confirm=new Alert(AlertType.CONFIRMATION);
    	confirm.setTitle("Closing..");
    	confirm.setContentText("Are You Sure?");
    	Optional<ButtonType> res= confirm.showAndWait();
    	if(res.get()==ButtonType.OK)
    			System.exit(1);
    }

    @FXML
    void doNew(ActionEvent event) 
    {
        txtflr.setText("");
        txtcap.setText("");
    }

    @FXML
    void doRad2(ActionEvent event)
    {
    	
    }

    @FXML
    void doRad4(ActionEvent event) 
    {
    }

    @FXML
    void doSave(ActionEvent event) 
    {
        int flr=Integer.parseInt(txtflr.getText());
        int cap=Integer.parseInt(txtcap.getText());
        int typ = 0;
        int ocup=0;
        if(rd2.isSelected()==true)
        	typ=2;
        if(rd4.isSelected()==true)
        	typ=4;
        try
    	{
			PreparedStatement pst=con.prepareStatement("insert into playout values(?,?,?,?)");
			pst.setInt(1,flr);    //here 1 implies the first question mark(?) where ? implies to the first field of employee 
			pst.setInt(2,cap);
			pst.setInt(3,typ); //to add more columns, make changes in these
			pst.setInt(4,ocup);
			pst.executeUpdate();//fire query in table
			System.out.println("Saved....");
		} 
    	catch (SQLException e)
    	{	
			e.printStackTrace();
		}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        con=DBConnection.doConnect();
    }
}
