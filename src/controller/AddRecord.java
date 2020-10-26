package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.Encrypt;
import main.Main;
import model.Record;


import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddRecord implements Initializable {

    @FXML
    private Button add;

    @FXML
    private TextField name;

    @FXML
    private TextField account;

    @FXML
    private TextField passwd;

    @FXML
    private Label tips;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Main.record!=null) {
            name.setText(Main.record.getName());
            account.setText(Main.record.getAccount());
            passwd.setText(Main.record.getPasswd());
            Main.record = null;
        }
    }

    @FXML
    public void addRecord() throws IOException {

        Record record = new Record();

        System.out.println("name:" + name.getText() + "\naccount:" + account.getText() + "\npassword:" + passwd.getText());
        if (name.getText().equals("")) {
            tips.setText("Name cannot be empty!");
        } else if (account.getText().equals("")) {
            tips.setText("Account cannot be empty!");
        } else if (passwd.getText().equals("")) {
            tips.setText("Password cannot be empty!");
        } else {
            record.setName(name.getText());
            record.setAccount(account.getText());
            record.setPasswd(passwd.getText());
            Main.list.add(record);
            new ChangeScene("home.fxml");
        }
    }

}
