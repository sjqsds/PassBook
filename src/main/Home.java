package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Record;

import java.io.*;
import java.util.logging.Handler;

public class Home {

    public static Record record;
    public static ObservableList<Record> list;


    public void showWindow() throws Exception {
        readToList();
        Parent root = FXMLLoader.load(getClass().getResource("/view/home.fxml"));
        Main.stage.setScene(new Scene(root));
        Main.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    writeFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Main.stage.show();
    }

    //将list中的数据写入到文件中
    public static void writeFile() throws IOException {

        FileOutputStream out = new FileOutputStream("passbook");
        OutputStreamWriter writer = new OutputStreamWriter(out,"UTF-8");
        for(Record record : list){
            try {
                String str = record.toString();
                writer.write(new Encrypt().encrypt(str)+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writer.close();
        out.close();
    }

    //从文件从读取全部数据到list列表中
    public void readToList(){
        list = FXCollections.observableArrayList();
        //每次显示清空一次列表
        list.clear();
        File file = new File("passbook");
        if(!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("passbook文件创建成功！");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        BufferedReader buffer = null;
        try {
            Encrypt encrypt = new Encrypt();
            buffer = new BufferedReader(new FileReader(file));
            String str="";
            while ((str = buffer.readLine()) != null){
                str = encrypt.decode(str);
                String[] arry = str.split("\\*\\*");
                Record record = new Record();
                record.setName(arry[1]);
                record.setAccount(arry[2]);
                record.setPasswd(arry[3]);
                list.add(record);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
