package controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.ChangeUp;
import main.Encrypt;


import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class ChangeUPController implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private TextField password1;

    @FXML
    private TextField password2;

    @FXML
    private Label prompt;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Properties props = new Properties();
        File file = new File("user.properties");
        try {
            props.load(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String username0 = props.getProperty("username");
        username.setText(new Encrypt().decode(username0));

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
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
                    password1.requestFocus();
                }
            }
        });
        password1.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.getKeyCode("Enter")){
                    password2.requestFocus();
                }
            }
        });
        password2.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.getKeyCode("Enter")){
                    changeUP();
                }
            }
        });
    }

    public void changeUP() {
        Properties props = new Properties();
        File file = new File("user.properties");
        try {
            props.load(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String password0 = props.getProperty("password");
        String pwd1 = password1.getText();
        String pwd2 = password2.getText();
        if(!password0.equals(new Encrypt().encrypt(password.getText()))){
            prompt.setText("旧密码错误！");
            password.clear();
        }else if(!pwd1.equals(pwd2)){
            prompt.setText("密码不一致，请重新输入");
            password1.clear();
            password2.clear();
            password1.requestFocus();
        }else{
            OutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                props.setProperty("username", new Encrypt().encrypt(username.getText()));
                props.setProperty("password", new Encrypt().encrypt(pwd1));
                // 以适合使用 load 方法加载到 Properties 表中的格式，
                // 将此 Properties 表中的属性列表（键和元素对）写入输出流
                props.store(fos, "Update '");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ChangeUp.stage.close();
        }
    }
}
