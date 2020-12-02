package xkcd;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import java.io.IOException;
import javafx.fxml.FXML;
import org.json.JSONObject;

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
    public Label comicTitle, comicDate;

    public int n;

    private void println(Object obj) {
        System.out.println(obj);
    }

    private void setImg() throws IOException, InterruptedException {
        n  = Integer.parseInt(comicNumber.getText());
        setLabels(Util.getComicInfo(n));
        Image comic = new Image(Util.getComic(n));
        comicImage.setFitWidth(640);
        comicImage.setFitHeight(640);
        comicImage.setPreserveRatio(true);
        comicImage.setImage(comic);
    }

    private void setLabels(JSONObject comicInfo) {
        comicTitle.setText(Util.getElement(comicInfo, "title"));
        comicTitle.setStyle("-fx-font-size: 30");
        comicTitle.setTranslateX(160);
        comicTitle.setTranslateY(64);

        comicDate.setText(Util.getDate(comicInfo));
        comicDate.setTranslateY(96);
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
