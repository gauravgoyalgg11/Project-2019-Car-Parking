/**
 * Sample Skeleton for 'vehicleEntryTableview.fxml' Controller Class
 */

package VehicleEntrytable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import CarParkingtable.carparkingBean;
import VehicleEntrytable.VehicleEntryBean;
import connectdb.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

public class vehicleEntryTableviewController 
{

	Connection con;
	PreparedStatement pst;
	ObservableList <VehicleEntryBean> list;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tbl"
    private TableView<VehicleEntryBean> tbl; // Value injected by FXMLLoader

    @FXML // fx:id="combovn"
    private ComboBox<String> combovn; // Value injected by FXMLLoader

    @FXML // fx:id="fd"
    private DatePicker fd; // Value injected by FXMLLoader

    @FXML // fx:id="td"
    private DatePicker td; // Value injected by FXMLLoader

    @FXML // fx:id="combost"
    private ComboBox<Integer> combost; // Value injected by FXMLLoader

    @FXML
    void doBrowse(ActionEvent event) 
    {
    	Integer st=combost.getSelectionModel().getSelectedItem();
        try{
        	pst=con.prepareStatement("select * from parking where Status=?");
        	pst.setInt(1,st );
        	fetchAllRecords(pst);
       	 
    	    tbl.setItems(list);
		} 
    	 catch (SQLException e) 
    	 {
			
			e.printStackTrace();
		}
    }
    
    void fillst()
    {
    	ArrayList<Integer> stAry=new ArrayList<>();
    	try{
        	pst=con.prepareStatement("select distinct Status from parking");
        	ResultSet table= pst.executeQuery();
        		
        		while(table.next())
        		{
        			Integer st=table.getInt("Status");//col. name acc. to table
        			stAry.add(st);
        			
        		}
        	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    	combost.getItems().addAll(stAry);
    }

    @FXML
    void doFetchAll(ActionEvent event) 
    {
    	try {
			
     		 pst=con.prepareStatement("select * from parking");
     		
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
        			int rid=table.getInt("Rid");//col. name acc. to table
        			String cus=table.getString("Customer_type");
        			String vn=table.getString("Vehicle_no");
        			int vt=table.getInt("Vehicle_Type");
        			int st=table.getInt("Status");
        			String ed=table.getString("Entry_Date");
        			String mn=table.getString("Mobile_No");
        			String et=table.getString("Entry_Time");
        			int flr=table.getInt("Floor");
        			float bill=table.getFloat("Bill");
        			System.out.println(rid+"  "+cus+"  "+vn+"  "+vt+"  "+st+"  "+ed+"  "+mn+"  "+et+"  "+flr+"  "+bill);
        			VehicleEntryBean sb=new VehicleEntryBean(rid,vt,st,flr,cus,vn,ed,mn,et,bill);
        			list.addAll(sb);
        			
        		}
        	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    	
    }
    
    void fillColumns()
    {
    	TableColumn<VehicleEntryBean, Integer> rid=new TableColumn<VehicleEntryBean, Integer>("Record Id");//Dikhava Title
    	rid.setCellValueFactory(new PropertyValueFactory<>("rid"));//bean field name, no link with table col name

    	TableColumn<VehicleEntryBean, String> cus=new TableColumn<VehicleEntryBean, String>("Customer Type");//Dikhava Title
    	cus.setCellValueFactory(new PropertyValueFactory<>("cus"));//bean field name, no link with table col name
    	
    	TableColumn<VehicleEntryBean, String> vn=new TableColumn<VehicleEntryBean, String>("Vehicle No");//Dikhava Title
    	vn.setCellValueFactory(new PropertyValueFactory<>("vn"));//bean field name, no link with table col name

    	TableColumn<VehicleEntryBean, Integer> vt=new TableColumn<VehicleEntryBean, Integer>("Vehicle Type");//Dikhava Title
    	vt.setCellValueFactory(new PropertyValueFactory<>("vt"));//bean field name, no link with table col name
    		
    	TableColumn<VehicleEntryBean, Integer> st=new TableColumn<VehicleEntryBean, Integer>("Status");//Dikhava Title
    	st.setCellValueFactory(new PropertyValueFactory<>("st"));//bean field name, no link with table col name

    	TableColumn<VehicleEntryBean, String> ed=new TableColumn<VehicleEntryBean, String>("Entry Date");//Dikhava Title
    	ed.setCellValueFactory(new PropertyValueFactory<>("ed"));//bean field name, no link with table col name
    	
    	TableColumn<VehicleEntryBean, String> mn=new TableColumn<VehicleEntryBean, String>("Mobile No");//Dikhava Title
    	mn.setCellValueFactory(new PropertyValueFactory<>("mn"));//bean field name, no link with table col name

    	TableColumn<VehicleEntryBean, String> et=new TableColumn<VehicleEntryBean, String>("Entry Time");//Dikhava Title
    	et.setCellValueFactory(new PropertyValueFactory<>("et"));//bean field name, no link with table col name
    	
    	TableColumn<VehicleEntryBean, Integer> flr=new TableColumn<VehicleEntryBean, Integer>("Floor No");//Dikhava Title
    	flr.setCellValueFactory(new PropertyValueFactory<>("flr"));//bean field name, no link with table col name
    	
    	TableColumn<VehicleEntryBean, Float> bill=new TableColumn<VehicleEntryBean, Float>("Bill");//Dikhava Title
    	bill.setCellValueFactory(new PropertyValueFactory<>("bill"));//bean field name, no link with table col name
    	
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(rid,cus,vn,vt,st,ed,mn,et,flr,bill);
    }
    
    @FXML
    void doFind(ActionEvent event)
    {
    	String vn=combovn.getSelectionModel().getSelectedItem();
        try{
        	pst=con.prepareStatement("select * from parking where Vehicle_no=?");
        	pst.setString(1,vn );
        	fetchAllRecords(pst);
       	 
    	    tbl.setItems(list);
		} 
    	 catch (SQLException e) 
    	 {
			
			e.printStackTrace();
		}
    }
    
    void fillvno()
    {
    	ArrayList<String> vnAry=new ArrayList<>();
    	try{
        	pst=con.prepareStatement("select distinct Vehicle_no from parking");
        	ResultSet table= pst.executeQuery();
        		
        		while(table.next())
        		{
        			String vno=table.getString("Vehicle_no");//col. name acc. to table
        			vnAry.add(vno);
        			
        		}
        	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    	combovn.getItems().addAll(vnAry);
    }


    @FXML
    void doSearch(ActionEvent event)throws Exception
    {
    	LocalDate local=fd.getValue();
    	java.sql.Date st=java.sql.Date.valueOf(local);
    	
    	LocalDate local1=td.getValue();
    	java.sql.Date en=java.sql.Date.valueOf(local1);
    	
    	pst=con.prepareStatement("select * from parking where Entry_Date between ? and ?");
    	pst.setDate(1, st);
    	pst.setDate(2, en);
    	
    	
    	fetchAllRecords(pst);
      	 
	    tbl.setItems(list);
    	
    }
    
    @FXML
    void doExport(ActionEvent event) throws Exception
    {
    	Writer writer = null;
        try {
        	FileChooser chooser=new FileChooser();
	    	
        	chooser.setTitle("Select Path:");
        	
        	chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Files", "*.*")
                    
                );
        	 File file=chooser.showSaveDialog(null);
        	String filePath=file.getAbsolutePath();
        	if(!(filePath.endsWith(".csv")||filePath.endsWith(".CSV")))
        	{
        		filePath=filePath+".csv";
        	}
        	 file = new File(filePath);
        	 
        	 
        	 
            writer = new BufferedWriter(new FileWriter(file));
            String text=" Mobile No.,Name,Address,City,Picture\n";
            writer.write(text);
            for (VehicleEntryBean p : list)
            {
				text = p.getRid()+ "," + p.getVt()+ "," + p.getSt()+ "," + p.getFlr()+ "," +p.getCus()+
					"," +p.getVn()+ "," +p.getEd()+ "," +p.getMn()+ "," +p.getEt()+ "," +p.getBill()+"\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
           
            writer.flush();
             writer.close();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize()
    {
        con=DBConnection.doConnect();
        fillColumns();
        fillvno();
        fillst();
    }
}
