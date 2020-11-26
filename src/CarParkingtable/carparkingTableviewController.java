/**
 * Sample Skeleton for 'carparkingTableview.fxml' Controller Class
 */

package CarParkingtable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import connectdb.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;






public class carparkingTableviewController 
{
    Connection con;
    PreparedStatement pst;
    ObservableList<carparkingBean> list;
    
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML // fx:id="combo"
    private ComboBox<String> combo; // Value injected by FXMLLoader


    @FXML // fx:id="tbl"
    private TableView<carparkingBean> tbl; // Value injected by FXMLLoader

    @FXML
    void doFetch(ActionEvent event)
    {
    	try {
			
      		 PreparedStatement	pst=con.prepareStatement("select * from customereg");
      		
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
        			String mno=table.getString("Mobile_No");//col. name acc. to table
        			String name=table.getString("Name");
        			String add=table.getString("Address");
        			String cty=table.getString("City");
        			System.out.println(mno+"  "+name+"  "+add+"  "+cty);
        			carparkingBean sb=new carparkingBean(mno,name,add,cty);
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
    	TableColumn<carparkingBean, String> mn=new TableColumn<carparkingBean, String>("Mobile number");//Dikhava Title
    	mn.setCellValueFactory(new PropertyValueFactory<>("mn"));//bean field name, no link with table col name

    	TableColumn<carparkingBean, String> nm=new TableColumn<carparkingBean, String>("Name");//Dikhava Title
    	nm.setCellValueFactory(new PropertyValueFactory<>("nm"));//bean field name, no link with table col name
    	
    	TableColumn<carparkingBean, String> add=new TableColumn<carparkingBean, String>("Address");//Dikhava Title
    	add.setCellValueFactory(new PropertyValueFactory<>("add"));//bean field name, no link with table col name

    	TableColumn<carparkingBean, String> ct=new TableColumn<carparkingBean, String>("City");//Dikhava Title
    	ct.setCellValueFactory(new PropertyValueFactory<>("ct"));//bean field name, no link with table col name
    		
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(mn,nm,add,ct);
    }
    
    @FXML
    void doSearch(ActionEvent event) 
    {
    	String mn=combo.getSelectionModel().getSelectedItem();
        try{
        	pst=con.prepareStatement("select * from customereg where Mobile_No=?");
        	pst.setString(1,mn );
        	fetchAllRecords(pst);
       	 
    	    tbl.setItems(list);
		} 
    	 catch (SQLException e) 
    	 {
			
			e.printStackTrace();
		}
    }

    void fillRolls()
    {
    	ArrayList<String> MobAry=new ArrayList<>();
    	try{
        	pst=con.prepareStatement("select distinct Mobile_No from customereg");
        	ResultSet table= pst.executeQuery();
        		
        		while(table.next())
        		{
        			String mno=table.getString("Mobile_No");//col. name acc. to table
        			MobAry.add(mno);
        			
        		}
        	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    	combo.getItems().addAll(MobAry);
    }
    
    @FXML
    void doExport(ActionEvent event) throws IOException 
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
             for (carparkingBean p : list)
             {
 				text = p.getMn()+ "," + p.getNm()+ "," + p.getAdd()+ "," + p.getCt()+"\n";
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
        fillRolls();
    }
}
