/**
 * Sample Skeleton for 'parkingLayoutTableview.fxml' Controller Class
 */

package ParkingLayouttable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import ParkingLayouttable.ParkingLayoutBean;
import connectdb.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class parkingLayoutTableviewController 
{
    Connection con;
    PreparedStatement pst;
    ObservableList<ParkingLayoutBean> list;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML
    private ComboBox<String> combo;

    @FXML // fx:id="tbl"
    private TableView<ParkingLayoutBean> tbl; // Value injected by FXMLLoader
    
    @FXML
    void doFetch(ActionEvent event) 
    {
    	try {
			
     		 PreparedStatement	pst=con.prepareStatement("select * from playout");
     		
     		 fetchAllRecords(pst);
         	 
     	    tbl.setItems(list);
  		} 
     	 catch (SQLException e) 
     	 {
  			
  			e.printStackTrace();
  		}
    }
    
    void fetchAllRecords(PreparedStatement pst)
    {
    	list=FXCollections.observableArrayList();
    	try{
        	ResultSet table= pst.executeQuery();
        		
        		while(table.next())
        		{
        			int flr=table.getInt("Floor");//col. name acc. to table
        			int cap=table.getInt("Capacity");
        			int vt=table.getInt("VehicleType");
        			int occ=table.getInt("Occupied");
        			System.out.println(flr+"  "+cap+"  "+vt+"  "+occ);
        			ParkingLayoutBean sb=new ParkingLayoutBean(flr,cap,vt,occ);
        			list.addAll(sb);
        			
        		}
        	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    	
    }
    
    @FXML
    void doFind(ActionEvent event) 
    {
    	String flr=combo.getSelectionModel().getSelectedItem();
        try{
        	pst=con.prepareStatement("select * from playout where Floor=?");
        	pst.setString(1,flr );
        	fetchAllRecords(pst);
       	 
    	    tbl.setItems(list);
		} 
    	 catch (SQLException e) 
    	 {
			
			e.printStackTrace();
		}
    }

    void fillColumns()
    {
    	TableColumn<ParkingLayoutBean, Integer> flr=new TableColumn<ParkingLayoutBean, Integer>("Floor No.");//Dikhava Title
    	flr.setCellValueFactory(new PropertyValueFactory<>("flr"));//bean field name, no link with table col name

    	TableColumn<ParkingLayoutBean, Integer> cap=new TableColumn<ParkingLayoutBean, Integer>("Capacity");//Dikhava Title
    	cap.setCellValueFactory(new PropertyValueFactory<>("cap"));//bean field name, no link with table col name
    	
    	TableColumn<ParkingLayoutBean, Integer> vt=new TableColumn<ParkingLayoutBean, Integer>("Vehicle Type");//Dikhava Title
    	vt.setCellValueFactory(new PropertyValueFactory<>("vt"));//bean field name, no link with table col name

    	TableColumn<ParkingLayoutBean, Integer> oc=new TableColumn<ParkingLayoutBean, Integer>("Occupied");//Dikhava Title
    	oc.setCellValueFactory(new PropertyValueFactory<>("oc"));//bean field name, no link with table col name
    		
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(flr,cap,vt,oc);
    }
    
    void fillFlor()
    {
    	ArrayList<String> FlrAry=new ArrayList<>();
    	try{
        	pst=con.prepareStatement("select distinct Floor from playout");
        	ResultSet table= pst.executeQuery();
        		
        		while(table.next())
        		{
        		   String fno=table.getString("Floor");//col. name acc. to table
        			FlrAry.add(fno);
        			
        		}
        	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    	combo.getItems().addAll(FlrAry);
    }
    
    @FXML
    void doExport(ActionEvent event) 
    {
        
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() 
    {
        con=DBConnection.doConnect();
        fillFlor();
        fillColumns();
    }
}
