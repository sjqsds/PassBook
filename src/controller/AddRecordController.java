package controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.AddRecord;
import main.Encrypt;
import main.Home;
import main.Main;
import model.Record;


import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddRecordController implements Initializable {

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

        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                name.requestFocus();
            }
        });

        name.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.getKeyCode("Enter")){
                    account.requestFocus();
                }
            }
        });
        account.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.getKeyCode("Enter")){
                    passwd.requestFocus();
                }
            }
        });
        passwd.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.getKeyCode("Enter")){
                    try {
                        addRecord();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        if(Home.record!=null) {
            name.setText(Home.record.getName());
            account.setText(Home.record.getAccount());
            passwd.setText(Home.record.getPasswd());
            Home.record = null;
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
            Home.list.add(record);
            AddRecord.stage.close();
        }
    }

}
