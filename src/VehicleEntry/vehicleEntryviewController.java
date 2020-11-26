/**
 * Sample Skeleton for 'vehicleEntryview.fxml' Controller Class
 */

package VehicleEntry;

import java.net.URL; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import connectdb.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;

public class vehicleEntryviewController {
    Connection con;
    PreparedStatement pst;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="rad2w"
    private RadioButton rad2w; // Value injected by FXMLLoader

    @FXML // fx:id="r"
    private ToggleGroup r; // Value injected by FXMLLoader

    @FXML // fx:id="rad4w"
    private RadioButton rad4w; // Value injected by FXMLLoader

    @FXML // fx:id="fno"
    private ListView<String> fno; // Value injected by FXMLLoader

    @FXML // fx:id="fmpty"
    private ListView<String> fmpty; // Value injected by FXMLLoader

    @FXML // fx:id="rndm"
    private RadioButton rndm; // Value injected by FXMLLoader

    @FXML // fx:id="reg"
    private RadioButton reg; // Value injected by FXMLLoader

    @FXML // fx:id="txtmn"
    private TextField txtmn; // Value injected by FXMLLoader

    @FXML // fx:id="txtvn"
    private TextField txtvn; // Value injected by FXMLLoader
    
    @FXML
    private ToggleGroup r1;
    
    @FXML
    private TextField txtrec;

    @FXML
    private DatePicker dt;
    
    @FXML
    private TextField tm;
	private int f;


    @FXML
    void cuReg(ActionEvent event) 
    {
        
    }

    @FXML
    void cuRndm(ActionEvent event) {

    }

    @FXML
    void doNew(ActionEvent event) 
    {
        
    }

    @FXML
    void doRad2w(ActionEvent event) 
    {
    	fno.getItems().clear();
    	fmpty.getItems().clear();
    	int typ;
        if(rad2w.isSelected()==true)
        {	
             typ=2;
             ArrayList<String> fary=new ArrayList<>();
             try
             {
            	 pst=con.prepareStatement("Select * from playout where VehicleType=?");
            	 pst.setInt(1, typ);
            	 ResultSet tbl=pst.executeQuery();
            	 while(tbl.next())
            	 {
            		 String flr=tbl.getString("Floor");
            		 fary.add(flr);
            		 Integer c=tbl.getInt("Capacity");
            		 Integer o=tbl.getInt("Occupied");
            		 Integer avail=c-o;
            		 fmpty.getItems().add(avail+"");
            	 }
             }
             catch(Exception e)
             { 	 
             }
             fno.getItems().addAll(fary);
             
        }
    }

    @FXML
    void doRad4w(ActionEvent event) 
    {
    	fno.getItems().clear();
    	fmpty.getItems().clear();
    	int typ;
        if(rad4w.isSelected()==true)
        {	
             typ=4;
             ArrayList<String> fary=new ArrayList<>();
             try
             {
            	 pst=con.prepareStatement("Select * from playout where VehicleType=?");
            	 pst.setInt(1, typ);
            	 ResultSet tbl=pst.executeQuery();
            	 while(tbl.next())
            	 {
            		 String flr=tbl.getString("Floor");
            		 fary.add(flr);
            		 Integer c=tbl.getInt("Capacity");
            		 Integer o=tbl.getInt("Occupied");
            		 Integer avail=c-o;
            		 fmpty.getItems().add(avail+"");
            	 }
             }
             catch(Exception e)
             { 	 
             }
             fno.getItems().addAll(fary);
            /* try
             {
            	 pst=con.prepareStatement("");
             }*/
        }
    }

    
    @FXML
    void doSave(ActionEvent event) throws SQLException 
    {
    	int typ=0;
        if(rad2w.isSelected()==true)
        	typ=2;
        if(rad4w.isSelected()==true)
        	typ=4;
        
        String ctyp="";
        if(reg.isSelected()==true)
        	ctyp="Regular";
        if(rndm.isSelected()==true)
        	ctyp="Random";
    	String vn=txtvn.getText();
    	String mn=txtmn.getText();
    	int flrr=Integer.parseInt(fno.getSelectionModel().getSelectedItem());
    	//int rec=Integer.parseInt(txtrec.getText());
   // 	LocalDate local=dt.getValue();
    //	java.sql.Date dat=java.sql.Date.valueOf(local);
    	
    	
    	try
    	{
			PreparedStatement pst=con.prepareStatement("insert into parking  values(null,?,?,?,?,curdate(),?,curtime(),?,?)");
			//pst.setInt(1,rec);    //here 1 implies the first question mark(?) where ? implies to the first field of employee 
			pst.setString(1,ctyp); //to add more columns, make changes in these
			pst.setString(2,vn);
			pst.setInt(3, typ);
			pst.setInt(4, 1);
			//pst.setDate(6,dat);
			pst.setString(5, mn);
			pst.setInt(6, flrr);
			pst.setInt(7, 0);
			//pst.setString(8, tim);
			pst.executeUpdate();//fire query in table
			System.out.println("Saved....");
		} 
    	catch (SQLException e)
    	{	
			e.printStackTrace();
		}
    	doUpdate(flrr);
    }
    void doUpdate(int flr) throws SQLException 
    {
            pst=con.prepareStatement("update playout set Capacity=Capacity-1,Occupied=Occupied+1 where Floor=?" );
            pst.setInt(1, flr);
            pst.executeUpdate();
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
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize()
    {
        con=DBConnection.doConnect();
    }
}
