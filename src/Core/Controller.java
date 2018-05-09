package Core;
import Auxiliary.PopupWindow;
import Auxiliary.BankUser;
import SQL.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    //Search
    @FXML private ChoiceBox searchChoiceBox;
    @FXML private TextField searchText;
    @FXML private TableView<BankUser> SearchTableView;
    @FXML private TableColumn<BankUser, Integer> colIdS;
    @FXML private TableColumn<BankUser, String> colNameS;
    @FXML private TableColumn<BankUser, String> colSurnameS;
    @FXML private TableColumn<BankUser, String> colCityS;
    @FXML private TableColumn<BankUser, String> colStreetS;
    @FXML private TableColumn<BankUser, String> colHomenumberS;
    @FXML private TableColumn<BankUser, Integer> colPESELS;
    @FXML private TableColumn<BankUser, String> colPhoneS;
    @FXML private TableColumn<BankUser, Integer> colFundsS;

    private ObservableList<BankUser> bankData;
    private ObservableList<BankUser> bankSearchData;

    DB_CONNECTOR db = new DB_CONNECTOR();


    public Controller() {
        System.out.println("Initializing db connection...");
        db.Connect();
    }

    @FXML
    void initialize() {

        //Initialize all users table
        colId.setCellValueFactory(new PropertyValueFactory<BankUser, Integer>("userId"));
        colName.setCellValueFactory( new PropertyValueFactory<BankUser, String>("userName"));
        colSurname.setCellValueFactory( new PropertyValueFactory<BankUser, String>("userSurname"));
        colCity.setCellValueFactory( new PropertyValueFactory<BankUser, String>("userCity"));
        colStreet.setCellValueFactory( new PropertyValueFactory<BankUser, String>("userStreet"));
        colHomenumber.setCellValueFactory( new PropertyValueFactory<BankUser, String>("userHomenumber"));
        colPESEL.setCellValueFactory( new PropertyValueFactory<BankUser, Integer>("userPESEL"));
        colPhone.setCellValueFactory( new PropertyValueFactory<BankUser, String>("userPhone"));
        colFunds.setCellValueFactory(new PropertyValueFactory<BankUser, Integer>("userFunds"));

        //Initialize search table
        colIdS.setCellValueFactory(new PropertyValueFactory<BankUser, Integer>("userId"));
        colNameS.setCellValueFactory( new PropertyValueFactory<BankUser, String>("userName"));
        colSurnameS.setCellValueFactory( new PropertyValueFactory<BankUser, String>("userSurname"));
        colCityS.setCellValueFactory( new PropertyValueFactory<BankUser, String>("userCity"));
        colStreetS.setCellValueFactory( new PropertyValueFactory<BankUser, String>("userStreet"));
        colHomenumberS.setCellValueFactory( new PropertyValueFactory<BankUser, String>("userHomenumber"));
        colPESELS.setCellValueFactory( new PropertyValueFactory<BankUser, Integer>("userPESEL"));
        colPhoneS.setCellValueFactory( new PropertyValueFactory<BankUser, String>("userPhone"));
        colFundsS.setCellValueFactory(new PropertyValueFactory<BankUser, Integer>("userFunds"));

        searchChoiceBox.setItems(FXCollections.observableArrayList(
                "ClientID", "Name", "Surname", "PESEL", "City")
        );

        buildData();
    }


    public void buildData(){

        bankData = FXCollections.observableArrayList();
        ResultSet rs = null;
        rs = db.Show_All();
        try {
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
        db.DeleteUser(del_id.getText());
        del_id.clear();
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
        PopupWindow.display_error();
    }

    @FXML
    private void transfer_money(ActionEvent event) throws SQLException {
        db.TransferMoney(tr_id_s.getText(), tr_id_r.getText(), tr_f.getText());
        tr_id_s.clear(); tr_id_r.clear(); tr_f.clear();
        initialize();
    }

    @FXML
    private void search_user(ActionEvent event) throws SQLException {
        ResultSet sRes = db.SearchUser(searchText.getText(), searchChoiceBox.getValue());
        bankSearchData = FXCollections.observableArrayList();
        while (sRes.next()) {
            BankUser bu = new BankUser();
            bu.userId.set(sRes.getInt(1));
            bu.userName.set(sRes.getString(2));
            bu.userSurname.set(sRes.getString(3));
            bu.userCity.set(sRes.getString(4));
            bu.userStreet.set(sRes.getString(5));
            bu.userHomenumber.set(sRes.getString(6));
            bu.userPESEL.set(sRes.getLong(7));
            bu.userPhone.set(sRes.getString(8));
            bu.userFunds.set(sRes.getInt(9));

            bankSearchData.add(bu);
        }
        SearchTableView.setItems(bankSearchData);
        boolean cando = PopupWindow.display_confirmation();
        System.out.println((cando));
    }

}
