package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
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
    public void loadIn() throws Exception {
        System.out.println(username.getText() + " " + password.getText());

        if(username.getText().equals("")) {
            prompt.setText("请输入用户名");
        } else {
            // 判断密码是否为空
            if(password.getText().equals("")) {
                prompt.setText("请输入密码");
            }else if(username.getText().equals("username")  && password.getText().equals("password")) {
                new ChangeScene("home.fxml");
            }else{
                prompt.setText("用户名或密码错误！");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
