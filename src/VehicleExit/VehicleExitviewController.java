/**
 * Sample Skeleton for 'VehicleExitview.fxml' Controller Class
 */

package VehicleExit;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import connectdb.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class VehicleExitviewController {
	Connection con;
	PreparedStatement pst;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="combovn"
    private ComboBox<String> combovn; // Value injected by FXMLLoader

    @FXML // fx:id="flr"
    private TextField flr; // Value injected by FXMLLoader

    @FXML // fx:id="vt"
    private TextField vt; // Value injected by FXMLLoader

    @FXML // fx:id="et"
    private TextField et; // Value injected by FXMLLoader

    @FXML // fx:id="ed"
    private TextField ed; // Value injected by FXMLLoader

    @FXML // fx:id="ct"
    private TextField ct; // Value injected by FXMLLoader

    @FXML // fx:id="cd"
    private TextField cd; // Value injected by FXMLLoader

    @FXML // fx:id="amt"
    private TextField amt; // Value injected by FXMLLoader

    @FXML // fx:id="doUpdate"
    private Button doUpdate; // Value injected by FXMLLoader

    @FXML
    void doBill(ActionEvent event) throws Exception 
    {
          int tm=datetime();       //tm=total minutes
          int hr=tm/60;
          int min=tm%60;
          float tamt = 0;
      	  String vn=combovn.getSelectionModel().getSelectedItem();
          pst=con.prepareStatement("select Customer_type,Vehicle_Type from parking where Vehicle_no=?");
      	  pst.setString(1, vn);
      	  ResultSet tbl=pst.executeQuery();
      	  boolean jasus=false;
      	  while(tbl.next())
      	  {
      		  jasus=true;
      		  String ct=tbl.getString("Customer_type");
      		  String vt=tbl.getString("Vehicle_Type");
      		  if(ct.equals("Regular"))
      		  {
      			  if(min>=30 && vt.equals("2"))
      				  tamt=hr*15;
      			  else
      				  tamt=hr*10;
      		  }
      		  
      		  if(ct.equals("Random"))
      		  {
      			  if(min>=30 && vt.equals("4"))
      				  tamt=hr*50;
      			  else
      				  tamt=hr*30;
      		  }
      		  amt.setText(String.valueOf(tamt));
      	  }
      	  if(jasus==false)
      		  System.out.println("Invalid Vehicle no....");
      	  
      	  doGenBill(tamt);
      		  
    }
    
    void doGenBill(float tamt)throws Exception
    {
    	pst=con.prepareStatement("update parking set Bill=? where Vehicle_no=?");
    	pst.setString(1,String.valueOf(tamt));
    	pst.setString(2, vno);
    	pst.executeUpdate();
    }
    
    int datetime()
    {
    	LocalDate date=LocalDate.parse(ed.getText());
    	LocalTime time=LocalTime.parse(et.getText());
    	LocalDate curdate=LocalDate.parse(cd.getText());
    	LocalTime curtime=LocalTime.parse(ct.getText());
    	int dify=Math.abs(curdate.getYear()-date.getYear());
    	System.out.println("year:"+dify);
    	
    	int difmon=Math.abs(curdate.getMonthValue()-date.getMonthValue());
    	System.out.println("Month:"+difmon);
    	
    	int difday=Math.abs(curdate.getDayOfMonth()-date.getDayOfMonth());
    	System.out.println("Day:"+difday);
    	
    	int etm=time.getMinute();
    	int ctm=curtime.getMinute();
    	int difm=Math.abs(curtime.getMinute()-time.getMinute());
    	System.out.println("Minutes:"+difm);
    	
    	int difh=Math.abs(curtime.getHour()-time.getHour());
    	System.out.println("Hours:"+difh);
    	
    	int tm=(dify*365*24*60)+(difmon*30*24*60)+(difday*24*60)+(difh*60)+difm;
    	System.out.println("Total Minutes:"+tm);
    	return tm;
    	
    }
    
    LocalDate dat;
    String dt;
    String	vno;
    String	flor;
    @FXML
    void doFetch(ActionEvent event) throws SQLException 
    {
    	String vn=combovn.getSelectionModel().getSelectedItem();
        try
        {
        	pst=con.prepareStatement("select * from parking where Vehicle_no=?");
        	pst.setString(1, vn);
        	dat=LocalDate.now();
        	LocalTime tim=LocalTime.now();
        	ResultSet tbl=pst.executeQuery();
        	boolean jasus=false;
        	
        	while(tbl.next())
        	{
        		jasus=true;
        		vno=tbl.getString("Vehicle_no");
        		flor=tbl.getString("Floor");
        		dt=tbl.getString("Entry_Date");
        		String tm=tbl.getString("Entry_Time");
        		String vtyp=tbl.getString("Vehicle_Type");
        		
        		ed.setText(dt+"");  //we can also use pr+"" instead of (String.valueOf
        		flr.setText(flor+"");
        		et.setText(String.valueOf(tm));
        		vt.setText(vtyp+"");
        		cd.setText(dat+"");
        		ct.setText(tim+"");
        	}
        	if(jasus==false)
        	{
        		System.out.println("Invalid Vehicle no");
        	}
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        doupdate(vno,flor );
    }
    void fillvclno()
    {
    	ArrayList<String> vclAry=new ArrayList<>();
    	try{
        	pst=con.prepareStatement("select distinct Vehicle_no from parking");
        	ResultSet table= pst.executeQuery();
        		
        		while(table.next())
        		{
        			String vno=table.getString("Vehicle_no");//col. name acc. to table
        			vclAry.add(vno);
        			
        		}
        	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    	combovn.getItems().addAll(vclAry);
    }
    
    @FXML
    void doUpdate(ActionEvent event) throws SQLException 
    {
        /*pst=con.prepareStatement("update parking set Status=0 where Vehicle_no=?");
        pst.setString(1, vno);
        pst.executeUpdate();
        doupdate();*/
    }
    void doupdate(String vn,String flr) throws SQLException
    {
    	 pst=con.prepareStatement("update parking set Status=0 where Vehicle_no=?");
         pst.setString(1, vn);
         pst.executeUpdate();
    	
    	pst=con.prepareStatement("update playout set Capacity=Capacity+1,Occupied=Occupied-1 where Floor=?");
    	pst.setString(1, flr);
    	int count=pst.executeUpdate();
        System.out.println(count+" Records Updated....");    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() 
    {
        con=DBConnection.doConnect();   
        fillvclno();
    }
}
