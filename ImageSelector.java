package sample;

import javafx.application.Platform;
import javafx.scene.image.Image;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class ImageSelector
{
    public static int count = 0;
    public static String currPath;
    public static  Timer timer;

    public static void startSelectImages(String path)
    {
        currPath = path;
        count = Main.stoppedCount;

        timer = new Timer();
        File folder = new File(path);

        // list the files into an array
        File[] listOfFiles = folder.listFiles();

        // set the first picture

        // check if it is an image
        while(count != listOfFiles.length && !isImage(listOfFiles[count].getAbsolutePath()))
            count++;

        // if it is not a directory that contains only files with not pictures, then don't do it
        if(count != listOfFiles.length)
            Main.currImage.setImage(new Image(listOfFiles[count++].toURI().toString(), Main.IMAGE_WIDTH, Main.IMAGE_HEIGHT, false, false));

        timer.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
                // check if it is an image
                while(count != listOfFiles.length && !isImage(listOfFiles[count].getAbsolutePath()))
                    count++;

                // if its ended
                if(count == listOfFiles.length) {
                    Platform.runLater(() -> BottomLayout.statusBar.setText("Finished")); // fixing with javafx thread

                    Main.currImage.setImage(null);

                    Main.stoppedCount = 0;
                    timer.cancel();
                    return;
                }

                // set the image
                Main.currImage.setImage(new Image(listOfFiles[count].toURI().toString(), Main.IMAGE_WIDTH, Main.IMAGE_HEIGHT, false, false));

                count++;
            }
        }, (int)(Main.speed*1000), (int)(Main.speed*1000)); // 1000 milliseconds = 1 second
    }

    public static boolean isImage(String name)
    {
        String suffix = name.substring(name.length()-3, name.length());

        if(suffix.equals("jpg") || suffix.equals("JPG") ||
        suffix.equals("png") || suffix.equals("PNG") ||
            suffix.equals("gif") || suffix.equals("GIF"))
        {
            return true;
        }

        return false;
    }
}
