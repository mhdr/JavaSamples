package nasimeshomal;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;

/**
 * Created by Mahmood on 7/17/2014.
 */
public class ClientWindow implements ClientEvent{

    private VBox vBoxMain;
    private HBox hBoxTop;
    private Label labelName;
    private TextField textFieldName;
    private Button buttonSend;
    private ListView listViewLog;
    private ObservableList<String> listViewLogItems;

    private Client client;

    public Parent InitializeUI()
    {
        InitializeLabelName();

        InitializeTextFieldName();

        InitializeButtonSend();

        InitializeHBoxTop();

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
        vBoxMain.setPrefSize(600,400);
        vBoxMain.getChildren().addAll(hBoxTop,listViewLog);
    }

    private void InitializeHBoxTop() {
        hBoxTop=new HBox();
        hBoxTop.setSpacing(10);
        hBoxTop.setAlignment(Pos.CENTER_LEFT);
        hBoxTop.setPadding(new Insets(10,2,10,2));
        hBoxTop.getChildren().addAll(labelName,textFieldName,buttonSend);
    }

    private void InitializeButtonSend() {
        buttonSend=new Button("Send");
        buttonSend.setPrefSize(100,30);
        buttonSend.setDefaultButton(true);
        buttonSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String msg=textFieldName.getText();
                    if (msg.length()>0)
                    {
                        client.SendMsg(msg);
                        textFieldName.setText("");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void InitializeTextFieldName() {
        textFieldName=new TextField();
        textFieldName.setPrefSize(250,30);
    }

    private void InitializeLabelName() {
        labelName=new Label("Name : ");
    }

    public void Load() throws IOException {
        client=new Client("127.0.0.1",9001,this);
    }

    public void InsertMsg(String msg)
    {
        listViewLogItems.addAll(msg);
    }

    @Override
    public void NewClientMsg(String msg) {
        this.InsertMsg(msg);
    }
}
