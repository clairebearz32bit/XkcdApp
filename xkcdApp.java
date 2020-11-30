package xkcd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class xkcdApp extends Application{
    final String title = "Java xkcd Client - Alpha v0.0.1";

    @Override
    public void start(Stage stage) throws Exception {
        initApp(stage);
        stage.show();
    }

    private void initApp(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("style.fxml"));
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
