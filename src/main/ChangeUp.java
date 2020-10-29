package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ChangeUp extends Application {
    public static Stage stage = new Stage();
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/changeUP.fxml"));
        primaryStage.getIcons().add(new Image("/META-INF/passbook.jpg"));
        primaryStage.setTitle("PassBook");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void showWindow(){
        try {
            start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
