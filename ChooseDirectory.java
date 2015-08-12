package sample;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;

public class ChooseDirectory
{
    public String ChooseDirectory(Stage primaryStage)
    {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open Directory");
        File selectedFile = directoryChooser.showDialog(primaryStage);

        if(selectedFile == null)
            return null;

        BottomLayout.statusBar.setText("Directory chosen successfully");
        return (selectedFile.getAbsolutePath());
    }
}
