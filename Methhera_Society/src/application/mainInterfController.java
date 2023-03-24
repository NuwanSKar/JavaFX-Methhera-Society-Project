package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class mainInterfController implements Initializable {
	@FXML
	private TextField nameInput;
	@FXML
	private TextField ageInput;
	@FXML
	private TextField idInput;
	@FXML
	private TextField distInput;
	@FXML
	private TextField yearInput;
	@FXML
	private TextField phoneNumber;
	@FXML
	private Label showRegNo;
	
	@FXML
	private TextField search;
	@FXML
	private TextField search1;
	@FXML
    private Label showAG;
    @FXML
    private Label showDCT;
    @FXML
    private Label showID;
    @FXML
    private Label showNM;
    @FXML
    private Label showPN;
    @FXML
    private Label showDON;
	
	
	@FXML
	private TextField societyIdInput;
	@FXML
	private TextField amountInput;
	
//	@FXML
//	private TableView<displayData> tableData;
//	@FXML
//	private TableColumn<displayData, String> NmClId;
//	@FXML
//	private TableColumn<displayData, String> SocInxClId;
//	@FXML
//	private TableColumn<displayData, Integer> TDonClId;




	
	
	
	
	
	public String generateSocietyId() {	

		String databaseName = "sqltest";
		String url = "jdbc:mysql://localhost:3306/" + databaseName;
		String userName = "root";
		String userPassword = "root";
		String query = "SELECT COUNT(*) from methherasociety";
		int no1 = 1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection1 = DriverManager.getConnection(url, userName, userPassword );
			Statement statement1 = connection1.createStatement();
			ResultSet result1 = statement1.executeQuery(query);   	
			while(result1.next()) {				
				no1 += result1.getInt(1);
			}
			connection1.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		int year = Integer.parseInt(yearInput.getText());
		String Alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();
		char c = Alph.charAt(rnd.nextInt(Alph.length()));
		String strYr = Integer.toString(year);
		
		String indNo = null;
		if (no1<10) {
			indNo = "00"+String.valueOf(no1);
		}
		else if ((10<=no1) && (no1<100)) {
			indNo = "0"+String.valueOf(no1);
		}
		else if ((100<=no1) && (no1<1000)) {
			indNo = String.valueOf(no1);
		}
		
		String societyIdI = String.valueOf(Integer.parseInt(strYr.substring(strYr.length()-2))) + indNo +c;
		return societyIdI;
	}
	
	
	
		
	
	
	public void regButton(ActionEvent event) {
		
		if (nameInput.getText().isBlank() || ageInput.getText().isBlank() || idInput.getText().isBlank() || distInput.getText().isBlank() ||  yearInput.getText().isBlank() || phoneNumber.getText().isBlank()) {
			showRegNo.setText("Please Fill all the spaces");
			Alert alert1 = new Alert(Alert.AlertType.WARNING);
			alert1.setTitle("Error!");
			alert1.setHeaderText("Please Fill all the spaces");
			alert1.setContentText("All the fields are manditory to fill ");
			alert1.showAndWait();
		}
	
		String nameI = nameInput.getText();
		int ageI = Integer.parseInt(ageInput.getText());
		String idI = idInput.getText();
		String distI = distInput.getText();
		int yearI = Integer.parseInt(yearInput.getText());
		String generateSocietyId = generateSocietyId();
		int phoneNo = (int) Long.parseLong(phoneNumber.getText());
		
		String databaseName = "sqltest";
		String url = "jdbc:mysql://localhost:3306/" + databaseName;
		String userName = "root";
		String userPassword = "root";
		String query = "INSERT INTO methherasociety (Society_id, Name, National_id, District, Age, Reg_year, Phone_no ) values (?,?,?,?,?,?,?)";
		try {			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection1 = DriverManager.getConnection(url, userName, userPassword );
			
			PreparedStatement pst = connection1.prepareStatement(query);
			pst.setString(1, generateSocietyId);
			pst.setString(2,nameI);
			pst.setString(3,idI);
			pst.setString(4, distI);
			pst.setInt(5,ageI);
			pst.setInt(6,yearI);
			pst.setInt(7,phoneNo);
			pst.executeUpdate();
				Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
				alert1.setTitle("Succesfully registerd a new member!");
				alert1.setHeaderText("NEW member added");
				alert1.setContentText("Your Methhera register no is: " + generateSocietyId);
				alert1.showAndWait();
			
			showRegNo.setText("Your Methhera register no is: " + generateSocietyId);
						
			nameInput.setText(null);;			
			ageInput.setText(null);			
			idInput.setText(null);			
			distInput.setText(null);
			yearInput.setText(null);
			phoneNumber.setText(null);
			
			connection1.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	
	
	
	
	
	

	
	
	
	
	
	public void setSearch(ActionEvent event) {
		String sh1 = search.getText();
		String databaseName = "sqltest";
		String url = "jdbc:mysql://localhost:3306/" + databaseName;
		String userName = "root";
		String userPassword = "root";
		String query = "SELECT * from methherasociety"; 
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection1 = DriverManager.getConnection(url, userName, userPassword );
			Statement statement1 = connection1.createStatement();
		
			ResultSet result1 = statement1.executeQuery(query);   	
			String id;	
			String name;
			int age;
			int phoneNo;
			String nationalID;
			String district;
			int amount;	
			int cont = -1;
			while(result1.next()) {	
				id = result1.getString(2);
				if (id.equals(sh1)) {
					name = result1.getString(3);
					showNM.setText(":  " + name);
					age = result1.getInt(6);
					showAG.setText(String.valueOf(":  " + age));
					phoneNo = result1.getInt(9);
					showPN.setText(String.valueOf(":  " + phoneNo));
					nationalID = result1.getString(4);
					showID.setText(":  " + nationalID);
					district = result1.getString(5);
					showDCT.setText(":  " + district);
					amount = result1.getInt(8);
					showDON.setText(String.valueOf(":  " + amount));	
					cont = 1;
				}
			}
			if (cont ==-1) {
				Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
				alert1.setTitle("Member doesnot Exist");
				alert1.setHeaderText("Please check the ID no or Register");
				alert1.setContentText("No: " + sh1 +" does not EXIST");
				alert1.showAndWait();	
			}
			connection1.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	
	
	
	
	
	
	public void donateButton() {
		if ((societyIdInput.getText()).isBlank() || String.valueOf(Integer.parseInt(amountInput.getText())).isBlank()) {
			Alert alert1 = new Alert(Alert.AlertType.WARNING);
			alert1.setTitle("Error!");
			alert1.setHeaderText("Please Fill all the spaces");
			alert1.setContentText("All the fields are manditory to fill ");
			alert1.showAndWait();
		}
		else {
			String socId = societyIdInput.getText();
			int amountInp = Integer.parseInt(amountInput.getText());
			String databaseName = "sqltest";
			String url = "jdbc:mysql://localhost:3306/" + databaseName;
			String userName = "root";
			String userPassword = "root";
			String query = "Select * FROM methherasociety" ;
			
			try {	
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection1 = DriverManager.getConnection(url, userName, userPassword );
				PreparedStatement pst = connection1.prepareStatement(query);
				ResultSet result1 = pst.executeQuery(query);

				int getAmount = -1;	
				while (result1.next()) {								
					if (result1.getString(2).equals(socId)) {    
						getAmount = result1.getInt(8);
						break;															
					}
					else {
					}		
				}
				if (getAmount==-1){
					Alert alert1 = new Alert(Alert.AlertType.WARNING);
					alert1.setTitle("Member does not Exist");
					alert1.setHeaderText("Please check for the Society Id no again or register");
					alert1.showAndWait();
				}
				else {
					societyIdInput.setText(null);			
					amountInput.setText(null);			
					int totalAmount = getAmount + amountInp;	
					String query1 = "UPDATE methherasociety SET Amount = (?) WHERE Society_id = (?)";
									
					try {			
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection connection2 = DriverManager.getConnection(url, userName, userPassword );						
						PreparedStatement pst1 = connection2.prepareStatement(query1);
						pst1.setInt(1, totalAmount);
						pst1.setString(2, socId);
						pst1.executeUpdate();
						connection2.close();
							Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
							alert1.setTitle("Value added SUCESFULLY");
							alert1.setHeaderText("The value of "+ amountInp +" was added sucessfully");
							alert1.showAndWait();
						connection2.close();
						}
					catch (Exception e) {
						System.out.println(e);
						}
				connection1.close();
				}	
			}
			catch (Exception e) {
				System.out.println(e);
				System.out.println("Test 1234");
			}
		}	
		
	}

	
	
	
	
	
	

	public void unRegister(ActionEvent event) {
		
		String sh1 = search1.getText();
		String databaseName = "sqltest";
		String url = "jdbc:mysql://localhost:3306/" + databaseName;
		String userName = "root";
		String userPassword = "root";
		String query = "SELECT * from methherasociety"; 
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection1 = DriverManager.getConnection(url, userName, userPassword );
			Statement statement1 = connection1.createStatement();
		
			ResultSet result1 = statement1.executeQuery(query);   	
			String id;	
			int cont = -1;
			while(result1.next()) {	
				id = result1.getString(2);
				if (id.equals(sh1)) {
					Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
					alert2.setTitle("UNREGISTER");
					alert2.setContentText("ARe you sure you want to unregister "+sh1);
					Connection connection2 = DriverManager.getConnection(url, userName, userPassword );
					PreparedStatement pst2 = connection2.prepareStatement("UPDATE methherasociety SET Society_id = (?) WHERE Society_id = (?)");
					pst2.setString(1, "");
					pst2.setString(2, sh1);
					pst2.executeUpdate();
						Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
						alert1.setTitle("Unregistered SUCCESFULLY");
//						alert1.setHeaderText("");
						alert1.setContentText("Society ID: " + sh1 +" Unregistered from the Society Methhera");
						alert1.showAndWait();
					cont = 1;
				}
			}
			if (cont ==-1) {
				Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
				alert1.setTitle("Member doesnot Exist");
				alert1.setHeaderText("Please check the ID no or Register");
				alert1.setContentText("No: " + sh1 +" does not EXIST");
				alert1.showAndWait();
			}
			connection1.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}	
	}
	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	 

	
	



	
	
	
	
	

}

	
	
	
	

