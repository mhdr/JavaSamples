package nasimeshomal;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Mahmood on 7/12/2014.
 */
public class ServerWindow implements ServerEvent{

    private VBox vBoxMain;
    private ListView<String> listViewLog;
    private ObservableList<String> listViewLogItems;


    private ServerSocket serverSocket;
    private Server server;

    public Parent InitializeUI()
    {
        InitializeListViewLog();

        InitializeVBoxMain();

        return vBoxMain;
    }

    private void InitializeListViewLog() {
        listViewLog=new ListView();
        listViewLogItems= FXCollections.observableArrayList();
        listViewLog.setItems(listViewLogItems);
        // autoscroll
        listViewLogItems.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                int lastItem=listViewLogItems.size()-1;
                listViewLog.scrollTo(lastItem);
            }
        });
        //
    }

    private void InitializeVBoxMain() {
        vBoxMain=new VBox();
        vBoxMain.setPrefHeight(400);
        vBoxMain.setPrefWidth(600);
        vBoxMain.getChildren().addAll(listViewLog);
    }

    public void Load() throws IOException {
        serverSocket=new ServerSocket(ServerConfig.getPortNumber());

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
        listViewLogItems.addAll(msg);
    }

    @Override
    public void NewServerMsg(String msg) {
        this.InsertMsg(msg);
    }
}
