//package application;
//
//import javafx.beans.property.IntegerProperty;
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;
//
//public class displayData {
//    private final StringProperty NmClId;
//    private final StringProperty SocInxClId;
//    private final IntegerProperty TDonClId;
//
//    
//    public displayData(){
//    	NmClId = new SimpleStringProperty(this, "NmClId");
//    	SocInxClId = new SimpleStringProperty(this, "SocInxClId");
//    	TDonClId = new SimpleIntegerProperty(this, "TDonClId");
//    
//    }
// 
//    public StringProperty NmClIdProperty() { return NmClId; }
//    public String getNmClId() { return NmClId.get(); }
//    public void setNmClId(String newNmClId) { NmClId.set(newNmClId); }
// 
//    public StringProperty SocInxClIdProperty() { return SocInxClId; }
//    public String getSocInxClId() { return SocInxClId.get(); }
//    public void setSocInxClId(String newSocInxClId) { SocInxClId.set(newSocInxClId); }
// 
//    public IntegerProperty TDonClIdProperty() { return TDonClId; }
//    public int getTDonClId() { return TDonClId.get(); }
//    public void setTDonClId(int newTDonClId) { TDonClId.set(newTDonClId); }
//    
//	
//	
//}
