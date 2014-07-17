package nasimeshomal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        MainWindow mainWindow=new MainWindow();
        Parent root = mainWindow.InitializeUI();

        primaryStage.setTitle("Basic TCP Socket");
        primaryStage.setScene(new Scene(root,400,200));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
