package DB;

import App.*;
import App.Package;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class SqliteDB {

    private Connection dbConnection;

    public void connectToDB(String path, Boolean deleteDB) {
        try {
            Class.forName("org.sqlite.JDBC");
            if (deleteDB)
                new File(path).delete();
            dbConnection = DriverManager.getConnection("jdbc:sqlite:" + path);

            createUsersTable();
            createPackageTable();
            createOrdersTable();
            createPaymentsTable();

            System.out.println("db init");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    //**************** Create Tables **************************//

    private void createUsersTable() throws SQLException {
        execute("CREATE TABLE IF NOT EXISTS Users (\n" +
                "first_name text NOT NULL,\n" +
                "last_name text NOT NULL,\n" +
                "email text PRIMARY KEY \n" +
                "password text NOT NULL,\n" +
                "birth_date text,\n" +
                "city text NOT NULL,\n" +
                "sign_up_date text,\n" +
                ");");
    }


    private void createPackageTable() throws SQLException {
        execute("CREATE TABLE IF NOT EXISTS Packages (\n" +
                "vacation_status int, \n" +
                "seller_email text NOT NULL,\n" +
                "vacation_id int PRIMARY KEY,\n" +
                "from text NOT NULL,\n" +
                "to text NOT NULL,\n" +
                "checkin text,\n" +
                "checkout text,\n" +
                "airline text NOT NULL,\n" +
                "back_flight int,\n" +
                "hand_bag text NOT NULL,\n" +
                "checked_bag text NOT NULL ,\n" +
                "connec_flight text NOT NULL,\n" +
                "vacation_type text NOT NULL,\n" +
                "ticket_type text NOT NULL,\n" +
                "hotel int,\n" +
                "hotel_type text,\n" +
                "hotel_raiting int ,\n" +
                "num_of_tickets int,\n" +
                "original_price int,\n" +
                "sale_price int,\n" +
                "off int,\n" +
                "CONSTRAINT FK_Packages FOREIGN KEY (seller_email) REFERENCES Users(email)) ;"
        );
    }


    private void createOrdersTable() throws SQLException {
        execute("CREATE TABLE IF NOT EXISTS Orders (\n" +
                "seller_email text,\n" +
                "buyer_email text,\n" +
                "vacation_id int,\n" +
                "seller_status int,\n" +
                "buyer_status int,\n" +
                "CONSTRAINT PK_Orders PRIMARY KEY (seller_email,buyer_email,vacation_id)), \n" +
                "CONSTRAINT FK_OrderSeller FOREIGN KEY (seller_email) REFERENCES Users(email)), \n" +
                "CONSTRAINT FK_OrderBuyer FOREIGN KEY (buyer_email) REFERENCES Users(email));"
        );
    }


    private void createPaymentsTable() throws SQLException {
        execute("CREATE TABLE IF NOT EXISTS Payments (\n" +
                "seller_email text,\n" +
                "buyer_email text,\n" +
                "vacation_id int,\n" +
                "payment_email text NOT NULL,\n" +
                "payment_password text NOT NULL,\n" +
                "payment_date text,\n" +
                "CONSTRAINT PK_Payments PRIMARY KEY (seller_email,buyer_email,vacation_id)), \n" +
                "CONSTRAINT FK_PaymentsSeller FOREIGN KEY (seller_email) REFERENCES Users(email)), \n" +
                "CONSTRAINT FK_PaymentsBuyer FOREIGN KEY (buyer_email) REFERENCES Users(email)),\n" +
                "CONSTRAINT FK_PaymentsVacation FOREIGN KEY (vacation_id) REFERENCES Packages(vacation_id)) \n" +
                ";");
    }


    //*******************Add *****************************************//


    public void addUser(User user) {
        addUser(user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getPassword(), user.toStringBirth_date(), user.getCity(), user.toStringSignup_date());
    }

    private void addUser(String first_name, String last_name, String email, String password, String birth_date, String city, String sign_up_date) {
        try {
            String query = "INSERT INTO Users \n" +
                    "VALUES ('" + first_name + "','" + last_name + "','" + email + "','" + password + "','" + birth_date + "','" + city + "','" + sign_up_date + "');";
            execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addPackage(Package vac) {
        try {
            int vacation_status = vac.getVacation_status() ? 1 : 0;
            String seller_email = vac.getSeller();
            int vacation_id = vac.getVacation_id();
            String from = vac.getFrom();
            String to = vac.getto();
            String checkin = vac.toStringCheckin();
            String checkout = vac.toStringCheckout();
            String airline = vac.getAirline();
            int back_flight = vac.getBackFlight() ? 1 : 0;
            String hand_bag = vac.getHand_bag();
            String checked_bag = vac.getChecked_bag();
            String connec_flight = vac.getConnec_flight();
            String vacation_type = vac.getVacation_type();
            String ticket_type = vac.getTicket_type();

            int hotel;
            String hotel_type;
            int hotel_raiting;
            if (vac.getHotel()) {
                hotel = 1;
                hotel_type = vac.getHotel_type();
                hotel_raiting = vac.getHotel_raiting();
            } else {
                hotel = 0;
                hotel_type = null;
                hotel_raiting = -1;
            }

            int num_of_tickets = vac.getNum_of_tickets();
            int original_price = vac.getOriginal_price();
            int sale_price = vac.getSale_price();
            int off = vac.getOff();

            String query = String.format("INSERT INTO Packages VALUES(%d,'%s',%d,'%s','%s','%s','%s','%s',%d,'%s','%s','%s','%s','%s',%d,'%s',%d,%d,%d,%d,%d)",
                    vacation_status, seller_email, vacation_id, from, to, checkin, checkout,
                    airline, back_flight, hand_bag, checked_bag, connec_flight, vacation_type, ticket_type, hotel, hotel_type,
                    hotel_raiting, num_of_tickets, original_price, sale_price, off);
            execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addOrder(Order order) {

        try {

            String seller_email = order.getSeller_email();
            String buyer_email = order.getBuyer_email();
            int vacation_id = order.getVacation_id();
            int seller_status = order.getSeller_status() ? 1 : 0;
            int buyer_status = order.getBuyer_status() ? 1 : 0;

            String query = String.format("INSERT INTO Orders VALUES('%s',%s', %d, %d, %d)", seller_email, buyer_email, vacation_id, seller_status, buyer_status);
            execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addPayment(Payment payment) {

        try {
            String payment_date = payment.toStringPayment_date();

            String query = String.format("INSERT INTO Payments VALUES('%s',%s', %d, '%s','%s','%s')",
                    payment.getSeller_email(), payment.getBuyer_email(), payment.getVacation_id(),
                    payment.getPayment_email(), payment.getPayment_password(), payment_date);
            execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //*************** Delete ******************************

    public void deleteUser(User user) {
        try {
            execute("DELETE FROM Users WHERE Users.email = '" + user.getEmail() + "' ;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePackage(Package pack) {
        try {
            execute("DELETE FROM Packages WHERE Packages.vacation_id = '" + pack.getVacation_id() + " ;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(Order order) {
        try {
            execute("DELETE FROM Orders WHERE Orders.seller_email = " + order.getSeller_email() +
                    " AND Orders.buyer_email = " + order.getBuyer_email() + " AND Orders.vacation_id = " +
                    "'" + order.getVacation_id() + "' ;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUsers() {
        try {
            execute("DELETE FROM Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrders() {
        try {
            execute("DELETE FROM Orders");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePackages() {
        try {
            execute("DELETE FROM Packages");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //***************Update****************//


    public void UpdateUser(User user) {
        UpdateUsers(user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getPassword(), user.toStringBirth_date(), user.getCity(), user.toStringSignup_date());
    }

    private void UpdateUsers(String first_name, String last_name, String email, String password, String birth_date, String city, String sign_up_date) {
        try {
            String query = "UPDATE Users SET first_name='" + first_name +
                    "', last_name='" + last_name + "',password='" + password + "',birth_date='" + birth_date + "',city='" + city + "',sign_up_date='" + sign_up_date + "'" +
                    "WHERE email = '" + email + "');";
            execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void UpdateOrdersSellerStatus(Order ord, boolean sellerStatus) {
        try {
            String seller_email = ord.getSeller_email();
            String buyer_email = ord.getBuyer_email();
            int vacation_id = ord.getVacation_id();
            int status = sellerStatus ? 1 : 0;

            String query = "UPDATE Orders SET seller_status='" + status + "'" +
                    "WHERE seller_email = '" + seller_email + "'AND buyer_email='" + buyer_email + "'AND vacation_id='" + vacation_id + "');";
            execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateOrdersBuyerStatus(Order ord, boolean buyerStatus) {
        try {
            String seller_email = ord.getSeller_email();
            String buyer_email = ord.getBuyer_email();
            int vacation_id = ord.getVacation_id();
            int status = buyerStatus ? 1 : 0;

            String query = "UPDATE Orders SET buyer_status='" + status + "'" +
                    "WHERE seller_email = '" + seller_email + "'AND buyer_email='" + buyer_email + "'AND vacation_id='" + vacation_id + "');";
            execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void UpdatVacationStatus(int vacation_id, boolean vac_status) {
        try {
            int status = vac_status ? 1 : 0;
            String query = "UPDATE Packages SET vacation_status='" + status + "'" +
                    "WHERE vacation_id = '" + vacation_id + "');";
            execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ******************** Get ********************************


    public User getUserByEmail(String email) {
        try {
            Statement st = dbConnection.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * \n" +
                    "FROM Users \n" +
                    "WHERE Users.email = '" + email + "' ;");
            return getUserFromRow(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public String getUserNameByEmail(String email) {
        try {
            Statement st = dbConnection.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT first_name, last_name\n" +
                    "FROM Users \n" +
                    "WHERE Users.email = '" + email + "' ;");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            return first_name + " " + last_name;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Package getPackageByPackageId(int packageId) {
        try {
            Statement st = dbConnection.createStatement();
            ResultSet resSet = st.executeQuery("SELECT * FROM Packages as p " +
                    "WHERE p.vacation_id = '" + packageId + "';");
            return getPackageFromRow(resSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Order> getOrdersByseller_email(String seller_email) {
        try {
            Statement st = dbConnection.createStatement();
            ResultSet resSet = st.executeQuery("SELECT * FROM Orders " +
                    "WHERE Orders.seller_email = '" + seller_email + "';");
            List<Order> orders = new ArrayList<>();
            while (resSet.next()) {
                orders.add(getOrderFromRow(resSet));
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Order> getOrdersByBuyer_email(String buyer_email) {
        try {
            Statement st = dbConnection.createStatement();
            ResultSet resSet = st.executeQuery("SELECT * FROM Orders " +
                    "WHERE Orders.seller_email = '" + buyer_email + "';");

            List<Order> orders = new ArrayList<>();
            while (resSet.next()) {
                orders.add(getOrderFromRow(resSet));
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Package> getAllAvailablePackages() {
        try {
            Statement st = dbConnection.createStatement();
            ResultSet resSet = st.executeQuery("SELECT * FROM Packages WHERE Packages.vacation_status=1;");
            List<Package> packages = new ArrayList<>();
            while (resSet.next()) {
                packages.add(getPackageFromRow(resSet));
            }

            return packages;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Package> getPackageBySimpleSearch(Package pack) {

        try {
            Statement st = dbConnection.createStatement();
            ResultSet resSet = st.executeQuery("SELECT * FROM Packages as P" +
                    "WHERE P.from = '" + pack.getHand_bag() +
                    "'AND P.to='" + pack.getto() +
                    "'AND P.checkin='" + pack.toStringCheckin() +
                    "'AND P.checkout='" + pack.toStringCheckout() + "'AND P.vacation_status=1;");

            List<Package> packages = new ArrayList<>();
            while (resSet.next()) {
                packages.add(getPackageFromRow(resSet));
            }
            return packages;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //**********get from row*******************///

    private User getUserFromRow(ResultSet resultSet) throws SQLException {
        String first_name = resultSet.getString("first_name");
        String last_name = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String sbirth_date = resultSet.getString("birth_date");
        String city = resultSet.getString("city");
        String Ssign_up_date = resultSet.getString("sign_up_date");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate birth_date = LocalDate.parse(sbirth_date, formatter);
        LocalDate sign_up_date = LocalDate.parse(Ssign_up_date, formatter);
        return new User(first_name, last_name, email, password, birth_date, city, sign_up_date);
    }


    private Package getPackageFromRow(ResultSet resSet) throws SQLException {
        boolean vacation_status = resSet.getInt("vacation_status") == 1;

        String seller_email = resSet.getString("seller_email");
        int vacation_id = resSet.getInt("vacation_id");

        String from = resSet.getString("from");
        String to = resSet.getString("to");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate checkin = LocalDate.parse(resSet.getString("checkin"), formatter);
        LocalDate checkout = LocalDate.parse(resSet.getString("checkout"), formatter);

        String airline = resSet.getString("airline");
        boolean back_flight = resSet.getInt("back_flight") == 1;

        String hand_bag = resSet.getString("hand_bag");
        String checked_bag = resSet.getString("checked_bag");

        String connec_flight = resSet.getString("connec_flight");

        String vacation_type = resSet.getString("vacation_type");
        String ticket_type = resSet.getString("ticket_type");

        boolean hotel = resSet.getInt("hotel") == 1;
        String hotel_type = resSet.getString("hotel_type");
        int hotel_raiting = resSet.getInt("hotel_raiting");

        int num_of_tickets = resSet.getInt("num_of_tickets");
        boolean separately = false;

        int original_price = resSet.getInt("original_price");
        int sale_price = resSet.getInt("sale_price");
        int off = resSet.getInt("off");

        Package p = new Package(seller_email, vacation_id, from, to,
                checkin, checkout, airline, back_flight, hand_bag, checked_bag,
                connec_flight, vacation_type, ticket_type, hotel, hotel_type, hotel_raiting,
                num_of_tickets, separately, original_price, sale_price, off);
        return p;
    }


    private Order getOrderFromRow(ResultSet resultSet) throws SQLException {
        String seller_email = resultSet.getString("seller_email");
        String buyer_email = resultSet.getString("buyer_email");
        int vacation_id = resultSet.getInt("vacation_id");
        Boolean seller_status = resultSet.getInt("seller_status") == 1;
        Boolean buyer_status = resultSet.getInt("buyer_status") == 1;


        Order ord = new Order(seller_email, buyer_email, vacation_id, buyer_status);
        ord.setSeller_status(seller_status);
        return ord;
    }


//    ******************** Check if Exists ***********************************

    public Boolean isUserExists(User user) {

        try {
            Statement st = dbConnection.createStatement();
            String query = "SELECT * FROM Users as u WHERE u.email = '" + user.getEmail() + "' ;";
            ResultSet resSet = st.executeQuery(query);
            User resUser = getUserFromRow(resSet);
            return resUser.getPassword().equals(user.getPassword());
        } catch (SQLException e) {
            return false;
        }
    }


//*******************//

    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void execute(String sql) throws SQLException {
        Statement st = dbConnection.createStatement();
        st.execute(sql);
    }
}