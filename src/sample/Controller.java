package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Controller {

    // Add User
    @FXML private TextField add_n;
    @FXML private TextField add_sn;
    @FXML private TextField add_c;
    @FXML private TextField add_cst;
    @FXML private TextField add_cn;
    @FXML private TextField add_p;
    @FXML private TextField add_num;
    @FXML private TextField add_f;

    // Delete user
    @FXML private TextField del_id;
    @FXML private TextField del_p;

    // Withdraw money
    @FXML private TextField with_id;
    @FXML private TextField with_f;

    // Deposit money
    @FXML private TextField dep_id;
    @FXML private TextField dep_f;

    // Transfer money
    @FXML private TextField tr_id_s;
    @FXML private TextField tr_id_r;
    @FXML private TextField tr_f;

    //Show all users
    @FXML private TableView<BankUser> AllUsersTableview;
    @FXML private TableColumn<BankUser, Integer> colId;
    @FXML private TableColumn<BankUser, String> colName;
    @FXML private TableColumn<BankUser, String> colSurname;
    @FXML private TableColumn<BankUser, String> colCity;
    @FXML private TableColumn<BankUser, String> colStreet;
    @FXML private TableColumn<BankUser, String> colHomenumber;
    @FXML private TableColumn<BankUser, Integer> colPESEL;
    @FXML private TableColumn<BankUser, String> colPhone;
    @FXML private TableColumn<BankUser, Integer> colFunds;

    private ObservableList<BankUser> bankData;

    DB_CONNECTOR db = new DB_CONNECTOR();


    public Controller() {
        System.out.println("Initializing db connection...");
        db.Connect();
        //initialize();
    }

    @FXML
    void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<BankUser, Integer>("userId"));
        colName.setCellValueFactory( new PropertyValueFactory<BankUser, String>("userName"));
        colSurname.setCellValueFactory( new PropertyValueFactory<BankUser, String>("userSurname"));
        colCity.setCellValueFactory( new PropertyValueFactory<BankUser, String>("userCity"));
        colStreet.setCellValueFactory( new PropertyValueFactory<BankUser, String>("userStreet"));
        colHomenumber.setCellValueFactory( new PropertyValueFactory<BankUser, String>("userHomenumber"));
        colPESEL.setCellValueFactory( new PropertyValueFactory<BankUser, Integer>("userPESEL"));
        colPhone.setCellValueFactory( new PropertyValueFactory<BankUser, String>("userPhone"));
        colFunds.setCellValueFactory(new PropertyValueFactory<BankUser, Integer>("userFunds"));

//        AllUsersTableview.getSelectionModel().setCellSelectionEnabled(true);
//        AllUsersTableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        buildData();
    }

    public void buildData(){

        bankData = FXCollections.observableArrayList();
        try {
            ResultSet rs = db.Show_All();
            while (rs.next()) {
                BankUser bu = new BankUser();
                bu.userId.set(rs.getInt(1));
                bu.userName.set(rs.getString(2));
                bu.userSurname.set(rs.getString(3));
                bu.userCity.set(rs.getString(4));
                bu.userStreet.set(rs.getString(5));
                bu.userHomenumber.set(rs.getString(6));
                bu.userPESEL.set(rs.getLong(7));
                bu.userPhone.set(rs.getString(8));
                bu.userFunds.set(rs.getInt(9));

                bankData.add(bu);
            }
            AllUsersTableview.setItems(bankData);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML
    private void show_all(ActionEvent event) {
        db.Show_All();
    }

    @FXML
    private void add_user(ActionEvent event) throws SQLException {
        db.Add_User(add_n.getText(), add_sn.getText(), add_c.getText(), add_cst.getText(),
                add_cn.getText(), add_p.getText(), add_num.getText(), add_f.getText());
        add_n.clear(); add_sn.clear(); add_c.clear(); add_cst.clear(); add_cn.clear();
        add_p.clear(); add_num.clear(); add_cn.clear(); add_f.clear();
        initialize();
    }

    @FXML
    private void del_user(ActionEvent event) throws SQLException {
        db.DeleteUser(del_id.getText(), del_p.getText());
        del_p.clear(); del_id.clear();
        initialize();
    }

    @FXML
    private void withdraw_money(ActionEvent event) throws SQLException {
        db.WithdrawMoney(with_id.getText(), with_f.getText());
        with_id.clear(); with_f.clear();
        initialize();
    }

    @FXML
    private void deposit_money(ActionEvent event) throws SQLException {
        db.DepositMoney(dep_id.getText(), dep_f.getText());
        dep_id.clear(); dep_f.clear();
        initialize();
    }

    @FXML
    private void transfer_money(ActionEvent event) throws SQLException {
        db.TransferMoney(tr_id_s.getText(), tr_id_r.getText(), tr_f.getText());
        tr_id_s.clear(); tr_id_r.clear(); tr_f.clear();
        initialize();
    }
}
