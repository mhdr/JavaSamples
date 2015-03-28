package nasimeshomal;

import com.sun.deploy.util.StringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class MainWindowController {
    @FXML
    private Button ButtonBrowse;

    @FXML
    private ComboBox ComboBoxFrom;

    @FXML
    private ComboBox ComboBoxTo;

    @FXML
    private TextField TextFieldFilePath;

    @FXML
    private HBox HBoxStatusBar;

    private ObservableList<String> comboBoxFromCollection;
    private ObservableList<String> comboBoxToCollection;

    String fileName;
    Charset charsetFrom = Charset.forName("Windows-1256");
    Charset charsetTo = Charset.forName("UTF-8");

    @FXML
    protected void initialize()
    {
        comboBoxFromCollection= FXCollections.observableArrayList();
        comboBoxToCollection= FXCollections.observableArrayList();

        EncodingList list=new EncodingList();

        for (Encoding encoding:list)
        {
            comboBoxFromCollection.add(encoding.getDisplayValue());
            comboBoxToCollection.add(encoding.getDisplayValue());
        }

        ComboBoxFrom.getItems().addAll(comboBoxFromCollection);
        ComboBoxTo.getItems().addAll(comboBoxToCollection);

        // set default selected item
        Encoding encodingFrom= EncodingList.FindByValue("Windows-1256");
        ComboBoxFrom.getSelectionModel().select(encodingFrom.getDisplayValue());
        //


        // set default selected item
        Encoding encodingTo=EncodingList.FindByValue("UTF-8");
        ComboBoxTo.getSelectionModel().select(encodingTo.getDisplayValue());
        //
    }

    @FXML
    protected void ButtonBrowse_Clicked(ActionEvent e) throws IOException, ParseException {
        Stage buttonStage= (Stage) ButtonBrowse.getScene().getWindow();
        FileChooser fileChooser=new FileChooser();

        // load last directory from config
        Config config=new Config();
        String folderPath= config.getLastFolder();

        if (!folderPath.isEmpty())
        {
            File folder=new File(folderPath);

            // if folder deleted later
            if (folder.exists())
            {
                fileChooser.setInitialDirectory(folder);
            }
        }
        //

        File file=fileChooser.showOpenDialog(buttonStage);

        if (file!=null)
        {
            TextFieldFilePath.setText(file.getAbsolutePath());
            ClearStatusbar();
        }
    }

    @FXML
    protected void ButtonConvert_Clicked(ActionEvent e) throws IOException, ParseException {
        // get info from user
        fileName=TextFieldFilePath.getText();
        String comboBoxFromSelected= (String) ComboBoxFrom.getSelectionModel().getSelectedItem();
        String comboBoxToSelected= (String) ComboBoxTo.getSelectionModel().getSelectedItem();
        //

        // charset
        charsetFrom=Charset.forName(EncodingList.FindByDiplayValue(comboBoxFromSelected).getValue());
        charsetTo=Charset.forName(EncodingList.FindByDiplayValue(comboBoxToSelected).getValue());
        //

        // check if selected file exist
        File file=new File(fileName);

        if (file.exists()==false)
        {
            ClearStatusbar();
            AddToStatusbar("Select a file first");
            return;
        }
        //

        // create backup
        String backupName=String.format("%s.%s",file,"bak");
        File backup=new File(backupName);
        FileUtils.copyFile(file,backup);
        //

        // convert charset and save
        String content = FileUtils.readFileToString(file, charsetFrom);
        FileUtils.write(file,content,charsetTo);
        //

        ClearStatusbar();
        AddToStatusbar("Converted successfully");

        Config config=new Config();
        config.setLastFolder(file.getParent());

    }

    private void ClearStatusbar()
    {
        HBoxStatusBar.getChildren().clear();
    }

    private void AddToStatusbar(String msg)
    {
        Label label=new Label(msg);
        label.setPadding(new Insets(0,0,0,10));
        label.setStyle("-fx-font-weight: bold;-fx-alignment: center");
        label.setAlignment(Pos.CENTER_LEFT);
        HBoxStatusBar.getChildren().add(label);
    }
}
