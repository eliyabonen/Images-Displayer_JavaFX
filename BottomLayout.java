package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BottomLayout
{
    public HBox layout;
    private Button buttonStart, buttonChooseDirectory;
    private String directoryPath;
    private TextField speedField;
    public static Label statusBar;
    double tmpSpeed = 5;

    public BottomLayout(Stage primaryStage)
    {
        layout = new HBox();

        // status bar
        statusBar = new Label("Waiting for start");
        statusBar.setAlignment(Pos.BOTTOM_LEFT);
        layout.setMargin(statusBar, new Insets(0, 200, 25, 0));

        // start button
        buttonStart = new Button("Start");
        buttonStart.setAlignment(Pos.BOTTOM_RIGHT);
        layout.setMargin(buttonStart, new Insets(0, 25, 25, 0));

        // choose directory button
        buttonChooseDirectory = new Button("Choose Directory");
        buttonChooseDirectory.setAlignment(Pos.BOTTOM_RIGHT);
        layout.setMargin(buttonChooseDirectory, new Insets(0, 10, 25, 0));

        // speed field
        speedField = new TextField();
        speedField.setPromptText("Speed");
        speedField.setAlignment(Pos.BOTTOM_RIGHT);
        speedField.setPrefWidth(50);
        layout.setMargin(speedField, new Insets(0, 10, 25, 0));

        // set from the value from the textfield to the variable
        speedField.setOnKeyReleased(e ->
        {
            try {
                tmpSpeed = Double.parseDouble(speedField.getText());
                BottomLayout.statusBar.setText("Speed set to " + tmpSpeed);
            } catch(Exception ex) {
                BottomLayout.statusBar.setText("Set default speed to " + Main.DEFAULT_SPEED);
                tmpSpeed = Main.DEFAULT_SPEED;
            }

            Main.speed = tmpSpeed;
        });

        // get the directory path according to what the user chose
        buttonChooseDirectory.setOnAction(e -> directoryPath = new ChooseDirectory().ChooseDirectory(primaryStage));

        // start the images display
        buttonStart.setOnAction(e ->
        {
            BottomLayout.statusBar.setText("Running the images display");
            ImageSelector.startSelectImages(directoryPath);
        });

        layout.getChildren().addAll(statusBar, speedField, buttonChooseDirectory, buttonStart);
    }

    public HBox getLayout()
    {
        return layout;
    }
}
