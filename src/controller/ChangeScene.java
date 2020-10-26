package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.WindowEvent;
import main.Main;

import java.io.IOException;

public class ChangeScene {

    public  ChangeScene(String fxml) throws IOException {
        String src = "/view/" + fxml;
        Parent root = FXMLLoader.load(getClass().getResource(src));
        Main.stage.setMaximized(false);
        Main.stage.setTitle("PassBook");
        Main.stage.setScene(new Scene(root));
        if(fxml=="home.fxml"){
            Main.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    try {
                        Main.writeFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                }
            });
        }else if(fxml=="addARecord.fxml"){
            Main.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    try {
                        new ChangeScene("home.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        Main.stage.show();
    }
}
