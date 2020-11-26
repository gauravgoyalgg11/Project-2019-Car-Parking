/**
 * Sample Skeleton for 'CustomerRegisterationview.fxml' Controller Class
 */

package CarParkingCustomerReg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import connectdb.DBConnection;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

public class CustomerRegisterationviewController {
	Connection con;
	PreparedStatement pst;
	File selectedFile;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private ComboBox<String> txtmob;

    @FXML // fx:id="txtcity"
    private TextField txtcity; // Value injected by FXMLLoader

    @FXML // fx:id="txtad"
    private TextField txtad; // Value injected by FXMLLoader

    @FXML // fx:id="txtname"
    private TextField txtname; // Value injected by FXMLLoader
    
    @FXML
    private ImageView imgvw;
    
    @FXML
    private TextField txtmn;

    String pth;
    @FXML
    void doBrowse(ActionEvent event) 
    {
    	 FileChooser fc=new FileChooser();
         selectedFile=fc.showOpenDialog(null);
         
         if(selectedFile!=null)
         {
         	pth=selectedFile.getAbsolutePath();
         	
         	try
         	{
         		BufferedImage bi=ImageIO.read(selectedFile);
         		WritableImage wi=SwingFXUtils.toFXImage(bi,null);
         		imgvw.setImage(wi);
         	}
         	catch(Exception e)
         	{
         		e.printStackTrace();
         	}
         }
         else
         {
         	System.out.println("file isn not valid");
         }
    }

    @FXML
    void doFetchOne(ActionEvent event) 
    {
    	String mn=txtmob.getSelectionModel().getSelectedItem();
        try
        {
        	pst=con.prepareStatement("select * from customereg where Mobile_No=?");
        	pst.setString(1, mn);
        	ResultSet tbl=pst.executeQuery();
        	boolean jasus=false;
        	
        	while(tbl.next())
        	{
        		jasus=true;
        		String mno=tbl.getString("Mobile_No");
        		String name=tbl.getString("Name");
        		String ad=tbl.getString("Address");
        		String ct=tbl.getString("City");
        		//String img=tbl.getString("Pic");
        		
        		FileOutputStream fos=new FileOutputStream("image.jpg");
        		Blob blob=tbl.getBlob("Picture");
        		int len=(int) blob.length();
        		
        		byte[] buf=blob.getBytes(1, len);
        		fos.write(buf,0,len);
        		Image img=new Image("file:image.jpg");
        		imgvw.setImage(img);
        		imgvw.setFitWidth(135);
        		imgvw.setFitHeight(135);
        		
        		txtname.setText(name);
        		txtad.setText(ad+"");  //we can also use pr+"" instead of (String.valueOf)
        		txtcity.setText(String.valueOf(ct));
        	//	imgvw.setId(img+"");
        	}
        	if(jasus==false)
        	{
        		System.out.println("Invalid id/roll no");
        	}
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }

    @FXML
    void doNew(ActionEvent event)
    {
        txtmob.setPromptText("");
        txtname.setText("");
        txtad.setText("");
        txtcity.setText("");
    }

    @FXML
    void doSave(ActionEvent event)
    {
    	String mn=txtmob.getSelectionModel().getSelectedItem();
    	String mno=txtmn.getText();
    	String name=txtname.getText();
    	String ad=txtad.getText();
    	String ct=txtcity.getText();
    	
    	try
    	{
			PreparedStatement pst=con.prepareStatement("insert into customereg values(?,?,?,?,?)");
			FileInputStream fis=new FileInputStream(selectedFile);
			pst.setBinaryStream(3,(InputStream) fis, (int)selectedFile.length());
			pst.setString(1,mno);    //here 1 implies the first question mark(?) where ? implies to the first field of employee 
			pst.setString(2,name);
			pst.setString(3,ad); //to add more columns, make changes in these
			pst.setString(4,ct);
			pst.setString(5, pth);
		//	pst.setBinaryStream(5,(InputStream) fis, (int)selectedFile.length());
			pst.executeUpdate();//fire query in table
			System.out.println("Saved....");
		} 
    	catch (Exception e)
    	{	
			e.printStackTrace();
		}
    }

    @FXML
    void doUpdate(ActionEvent event) 
    {
    	String mn=txtmob.getSelectionModel().getSelectedItem();
    	String name=txtname.getText();
    	String ad=txtad.getText();
    	String ct=txtcity.getText();	
    	
    	/*String sdob="";
    	LocalDate local;
		if(date.getValue()==null)
    	{
    		sdob=date.getEditor().getText();
    		 local = LocalDate.parse(sdob);       //conversion of date from string to local date
    	}
    	else
    	{
    		local=date.getValue();
    	}
    	java.sql.Date dob=java.sql.Date.valueOf(local);*/
    	
    	try
    	{
		PreparedStatement pst=con.prepareStatement("update customereg set Name=?,Address=?,City=? where Mobile_No=?");
			pst.setString(1,name);
			pst.setString(2,ad); //to add more columns, make changes in these
			pst.setString(3,ct);
			pst.setString(4,mn); //here we write mn at 4 because position of ? of Mobile No. in above query is at 4th position
			int count=pst.executeUpdate();//fire query in table
			System.out.println(count+" Records Updated....");
		} 
    	catch (SQLException e)
    	{	
			e.printStackTrace();
		}
    }
    
    void fillRolls()
    {
    	ArrayList<String> mobAry=new ArrayList<>();
    	try{
        	pst=con.prepareStatement("select distinct Mobile_No from customereg");
        	ResultSet table= pst.executeQuery();
        		
        		while(table.next())
        		{
        			String mno=table.getString("Mobile_No");//col. name acc. to table
        			mobAry.add(mno);
        			
        		}
        	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    	txtmob.getItems().addAll(mobAry);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize()
    {  
    	con=DBConnection.doConnect();
    	fillRolls();
    }
}
