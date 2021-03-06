package nasimeshomal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        MainWindow mainWindow=new MainWindow();
        Parent root = mainWindow.InitializeUI();

        primaryStage.setTitle("Basic TCP Socket");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {

        try {

            // load config
            Config config=new Config();
            ServerConfig.setServerIPAddress(config.getServerIPAddress());
            ServerConfig.setPortNumber(config.getPortNumber());
            //

        } catch (IOException e) {
            e.printStackTrace();
        }


        launch(args);
    }
}
