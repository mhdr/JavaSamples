package nasimeshomal;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * Created by Mahmood on 7/17/2014.
 */
public class MainWindow {

    private HBox hBoxButtons;
    private Button buttonServer;
    private Button buttonClient;

    public Parent InitializeUI()
    {
        InitializeButtonServer();

        InitializeButtonClient();

        InitializeHBoxButtons();

        return hBoxButtons;
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
    }

    private void InitializeHBoxButtons() {
        hBoxButtons =new HBox();
        hBoxButtons.setPrefSize(400, 200);
        hBoxButtons.maxHeight(Double.MAX_VALUE);
        hBoxButtons.maxWidth(Double.MAX_VALUE);
        hBoxButtons.setSpacing(50);
        hBoxButtons.setAlignment(Pos.CENTER);
        hBoxButtons.getChildren().addAll(buttonServer,buttonClient);
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
    }
}
