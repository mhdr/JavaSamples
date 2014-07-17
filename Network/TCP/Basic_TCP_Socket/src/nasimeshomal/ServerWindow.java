package nasimeshomal;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Mahmood on 7/12/2014.
 */
public class ServerWindow implements ServerEvent{

    private VBox vBoxMain;
    private ScrollPane scrollPaneMain;


    private ServerSocket serverSocket;
    private Server server;

    public Parent InitializeUI()
    {
        InitializeVBoxMain();

        InitializeScrollPaneMain();

        return scrollPaneMain;
    }

    private void InitializeScrollPaneMain() {
        scrollPaneMain=new ScrollPane(vBoxMain);
        scrollPaneMain.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPaneMain.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPaneMain.setPrefSize(605,405);
    }

    private void InitializeVBoxMain() {
        vBoxMain=new VBox();
        vBoxMain.setPrefHeight(400);
        vBoxMain.setPrefWidth(600);

        // autoscroll
        vBoxMain.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                scrollPaneMain.setVvalue(vBoxMain.getHeight()+5);
            }
        });
        //
    }

    public void Load() throws IOException {
        serverSocket=new ServerSocket(9001);

        server=new Server(serverSocket,this);
        server.start();
    }

    public void Unload() throws IOException, InterruptedException {
        if (server!=null)
        {
            server.stop();
        }
    }

    public void InsertMsg(String msg)
    {
        Label label=new Label(msg);

        vBoxMain.getChildren().addAll(label);
    }

    @Override
    public void NewServerMsg(String msg) {
        this.InsertMsg(msg);
    }
}
