package Model;

import App.*;
import App.Package;
import DB.SqliteDB;
import java.util.List;

public class Model {

    private final SqliteDB db;

    public Model() {
        this.db = new SqliteDB();
        db.connectToDB("myDB.db",false);
    }
///*****Add functions********////

    public void addUser(User user) {
        db.addUser(user);
    }
    public void addPackage(Package aPackage) {
        db.addPackage(aPackage);
    }
    public void addOrder(Order order) {
        db.addOrder(order);
    }
    public void addPayment(Payment payment) {
        db.addPayment(payment);
    }

    //  ******************** Check if Exists ***********************************
    public Boolean isUserExists(User user) { return db.isUserExists(user);}


    //*************** Delete ******************************
    public void deletePackage(Package pack) {db.deletePackage(pack);}
    public void deleteUser(User user){db.deleteUser(user);}


    // ******************** Get ********************************

    public User loadUser(User user) {return db.getUserByEmail(user.getEmail());}
    public String getUserNameByEmail(String email){return db.getUserNameByEmail(email);}
    public Package getPackageByPackageId(int packageId){return db.getPackageByPackageId(packageId);}
    public List<Order> getOrdersByseller_email(String seller_email){return db.getOrdersByseller_email(seller_email);}

    public List<Order> getOrdersByBuyer_email(String buyer_email){return db.getOrdersByBuyer_email(buyer_email);}
    public List<Package> getAllAvailablePackages(){return db.getAllAvailablePackages();}
    public List<Package> getPackageBySimpleSearch(Package pack){return getPackageBySimpleSearch(pack);}


    //*****************Update************************
    public void UpdateUser(User user){db.UpdateUser(user);}
    public void UpdateOrdersSellerStatus(Order ord, boolean sellerStatus) {db.UpdateOrdersSellerStatus(ord,sellerStatus);};
    public void UpdateOrdersBuyerStatus(Order ord, boolean buyerStatus) {db.UpdateOrdersBuyerStatus(ord,buyerStatus);}
    public void UpdatVacationStatus(int vacation_id, boolean vac_status) {db.UpdatVacationStatus(vacation_id,vac_status);}

}
