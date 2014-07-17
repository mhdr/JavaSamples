package nasimeshomal;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * Created by Mahmood on 7/17/2014.
 */
public class MainWindow {

    private HBox hBoxMain;
    private Button buttonServer;
    private Button buttonClient;

    public Parent InitializeUI()
    {
        InitializeHBoxMain();

        InitializeButtonServer();

        InitializeButtonClient();


        return hBoxMain;
    }

    private void InitializeButtonClient() {
        buttonClient=new Button("Client");
        buttonClient.maxWidth(Double.MAX_VALUE);
        buttonClient.maxHeight(Double.MAX_VALUE);
        buttonClient.setPrefSize(100,40);

        buttonClient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final ClientWindow clientWindow=new ClientWindow();
                Parent root= clientWindow.InitializeUI();

                Stage stage=new Stage();
                stage.setTitle("Client");
                stage.setScene(new Scene(root));

                stage.setOnShown(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        try {
                            clientWindow.Load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                stage.show();
            }
        });

        hBoxMain.getChildren().addAll(buttonClient);
    }

    private void InitializeHBoxMain() {
        hBoxMain=new HBox();
        hBoxMain.prefHeight(200);
        hBoxMain.prefWidth(400);
        hBoxMain.maxHeight(Double.MAX_VALUE);
        hBoxMain.maxWidth(Double.MAX_VALUE);
        hBoxMain.setSpacing(50);
        hBoxMain.setAlignment(Pos.CENTER);
    }

    private void InitializeButtonServer() {
        buttonServer=new Button("Server");
        buttonServer.maxWidth(Double.MAX_VALUE);
        buttonServer.maxHeight(Double.MAX_VALUE);
        buttonServer.setPrefSize(100,40);

        buttonServer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final ServerWindow serverWindow=new ServerWindow();
                Parent root= serverWindow.InitializeUI();

                Stage stage=new Stage();
                stage.setTitle("Server");
                stage.setScene(new Scene(root));


                stage.setOnShown(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        try {
                            serverWindow.Load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        try {
                            serverWindow.Unload();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });

                stage.show();
            }
        });

        hBoxMain.getChildren().addAll(buttonServer);
    }
}
