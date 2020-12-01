package xkcd;

import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import java.io.IOException;
import javafx.fxml.FXML;

public class Controller {
    @FXML
    public Button downloadButton;
    public SplitPane splitPane;
    public ImageView comicImage;
    public AnchorPane imagePane;
    public TextField comicNumber;
    public VBox root;
    public MenuBar menuBar;
    public Menu viewMenu, helpMenu;

    public int n;

    private void setImg() throws IOException, InterruptedException {
        n  = Integer.parseInt(comicNumber.getText());
        Image comic = new Image(Util.getComic(n));
        comicImage.setFitWidth(comic.getWidth() * 0.75);
        comicImage.setFitHeight(comic.getHeight() * 0.75);
        comicImage.setImage(comic);
        comicImage.setPreserveRatio(true);
    }

    @FXML
    public void showButtonClicked(ActionEvent event) throws Exception {
        if(event.getSource() == downloadButton && !comicNumber.getText().isEmpty()) {
            setImg();
        }
    }

    public void setKeybindings (KeyEvent e) {
        assert comicNumber != null;

        comicNumber.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();

            if(key == KeyCode.UP) {
                comicNumber.setText(String.valueOf(Integer.parseInt(comicNumber.getText()) + 1));
            }

            else if(key == KeyCode.DOWN) {
                comicNumber.setText(String.valueOf(Integer.parseInt(comicNumber.getText()) - 1));
            }

            else if(key == KeyCode.ENTER) {
                try {
                    setImg();
                }

                catch(InterruptedException | IOException ignore) {

                }
            }
        });
    }
}
