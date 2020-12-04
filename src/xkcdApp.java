package xkcd.src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Claire K.
 * @version 0.0.1 Alpha Build
 */

public class xkcdApp extends Application {
    final String title = "Java xkcd Client - v0.0.1";

    /**
     * @see javafx.stage.Window
     * JavaFX default initialization method.
     */

    @Override
    public void start(Stage stage) throws Exception {
        initApp(stage);
        stage.show();
    }

    /**
     * Helper method to reduce function calls in the {@link #start(Stage)} method.
     */

    private void initApp(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("resources/view/style.fxml"));
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.getIcons().add(new Image(xkcdApp.class.getResourceAsStream("resources/images/icon.png")));
        stage.setTitle(title);
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
