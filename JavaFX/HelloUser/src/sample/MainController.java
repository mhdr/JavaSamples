package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private Button buttonShow;

    @FXML
    private TextField textFieldFirstName;

    @FXML
    private TextField textFieldLastName;

    @FXML
    protected void ShowMsg(ActionEvent e)
    {
        String firstName=textFieldFirstName.getText();
        String lastName=textFieldLastName.getText();
        String msg=String.format("Hello %s %s",firstName,lastName);
        System.out.println(msg);
    }
}
