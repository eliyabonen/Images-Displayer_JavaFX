package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.ImageView;

import java.io.File;

public class Main extends Application
{
    public static final double DEFAULT_SPEED = 3.0;
    public static final int IMAGE_WIDTH = 550, IMAGE_HEIGHT = 400;
    public static int stoppedCount = 0;
    public static double speed = DEFAULT_SPEED;
    public static ImageView currImage;
    private boolean clicked = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane layout = new BorderPane();

        // imageview
        currImage = new ImageView();
        layout.setMargin(currImage, new Insets(25, 180, 0, 180));
        currImage.setSmooth(true);
        layout.setTop(currImage);

        // add bottom layout
        BottomLayout bottomLayoutObject = new BottomLayout(primaryStage);
        HBox bottomLayout = bottomLayoutObject.getLayout();
        bottomLayout.setAlignment(Pos.BOTTOM_RIGHT);
        layout.setBottom(bottomLayout);

        currImage.setOnMouseClicked(e ->
        {
            clicked = !clicked;

            if(clicked)
            {
                BottomLayout.statusBar.setText("Paused");

                ImageSelector.timer.cancel();
                stoppedCount = ImageSelector.count;
            }
            else
            {
                BottomLayout.statusBar.setText("Running the images display");
                ImageSelector.startSelectImages(ImageSelector.currPath);
            }
        });

        // cancel the timer if the user exited the program
        primaryStage.setOnCloseRequest(e ->
        {
            if(ImageSelector.timer != null)
                ImageSelector.timer.cancel();
        });

        primaryStage.setTitle("Images Displayer");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(layout, 900, 600));
        primaryStage.show();
    }
}
