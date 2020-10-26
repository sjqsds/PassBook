package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Encrypt;
import main.Main;
import model.Record;


import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private TableView<Record> table;
    @FXML
    private TableColumn<Record,String> name;
    @FXML
    private TableColumn<Record,String> account;
    @FXML
    private TableColumn<Record,String> passwd;
    @FXML
    private TableColumn Bj;
    @FXML
    private TableColumn Sc;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'home.fxml'.";
        assert account != null : "fx:id=\"account\" was not injected: check your FXML file 'home.fxml'.";
        assert passwd != null : "fx:id=\"passwd\" was not injected: check your FXML file 'home.fxml'.";

        showList();
    }

    public void addRecord() throws IOException {
        new ChangeScene("addARecord.fxml");
    }


    public void showList(){

        name.setCellValueFactory(new PropertyValueFactory("name"));
        account.setCellValueFactory(new PropertyValueFactory("account"));
        passwd.setCellValueFactory(new PropertyValueFactory("passwd"));


        table.setItems(Main.list);

        //添加按钮进列表
        Bj.setCellFactory((col)->{

                    //UserLoad换成你自己的实体名称
                    TableCell<Record, String> cell = new TableCell<Record, String>(){
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            Button button1 = new Button("编辑");
                            button1.setStyle("-fx-background-color: #00bcff;-fx-text-fill: #ffffff");

                            button1.setOnMouseClicked((col) -> {

                                //获取list列表中的位置，进而获取列表对应的信息数据
                                Record record = Main.list.get(getIndex());
                                Main.list.remove(getIndex());
                                //按钮事件自己添加
                                Main.record = record;
                                try {
                                    new ChangeScene("addARecord.fxml");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                showList();
                            });

                            if (empty) {
                                //如果此列为空默认不添加元素
                                setText(null);
                                setGraphic(null);
                            } else {
                                this.setGraphic(button1);
                            }
                        }
                    };
                    return cell;
                }
        );

        Sc.setCellFactory((col)->{
                    TableCell<Record, String> cell = new TableCell<Record, String>(){

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            //按钮显示文字
                            Button button2 = new Button("删除");
                            //设置按钮颜色
                            button2.setStyle("-fx-background-color: #00bcff;-fx-text-fill: #ffffff");
                            //按钮点击事件
                            button2.setOnMouseClicked((col) -> {
                                //获取list列表中的位置，进而获取列表对应的信息数据
                                Main.list.remove(getIndex());
                                showList();
                            });

                            if (empty) {
                                //如果此列为空默认不添加元素
                                setText(null);
                                setGraphic(null);
                            } else {
                                //加载按钮
                                this.setGraphic(button2);
                            }
                        }
                    };
                    return cell;
                }
        );
    }
}
