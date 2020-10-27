package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Record;

import java.io.*;

public class Home {

    public static Record record;
    public static ObservableList<Record> list;


    public void showWindow() throws Exception {
        readToList();
        Parent root = FXMLLoader.load(getClass().getResource("/view/home.fxml"));
        Main.stage.setScene(new Scene(root));
        Main.stage.show();
    }

    //将list中的数据写入到文件中
    public static void writeFile() throws IOException {

        FileOutputStream out = new FileOutputStream("passbook.txt");
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
        File file = new File("passbook.txt");

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
