package controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.Encrypt;
import main.Home;


import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class LandController implements Initializable {

    @FXML
    private Button onload;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    private Label prompt;

    @FXML
    public void loadIn() {

        if(username.getText().equals("")) {
            prompt.setText("请输入用户名");
        } else {
            // 判断密码是否为空
            if(password.getText().equals("")) {
                prompt.setText("请输入密码");
            }else {
                Properties props = new Properties();
                File file = new File("user.properties");
                try {
                    props.load(new FileReader(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String username0 = props.getProperty("username");
                String password0 = props.getProperty("password");

                if(username.getText().equals(new Encrypt().decode(username0))  && password.getText().equals(new Encrypt().decode(password0))) {
                    try {
                        changeWindow();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    prompt.setText("用户名或密码错误！");
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                username.requestFocus();
            }
        });

        username.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.getKeyCode("Enter")){
                    password.requestFocus();
                }
            }
        });
        password.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.getKeyCode("Enter")){
                    try {
                        loadIn();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void changeWindow() throws Exception {
        Home home = new Home();
        home.showWindow();
    }
}
