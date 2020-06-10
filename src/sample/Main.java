package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller controller=loader.getController();
        controller.setCloseRequeast(primaryStage);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 10*31-1+200, 20*31-1));
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
        controller.setFocus();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
