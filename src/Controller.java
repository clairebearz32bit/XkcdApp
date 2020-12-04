package xkcd.src;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
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
    public MenuItem aboutItem, quitItem;

    private void setLabels(JSONObject comicInfo, String title) {
        comicTitle.setText(title);
        comicTitle.setStyle("-fx-font-size: 30");
        comicTitle.setTranslateX(160);
        comicTitle.setTranslateY(64);

        if(comicInfo != null) {
            comicDate.setText(Util.getDate(comicInfo));
            comicDate.setTranslateY(96);
        }
    }

    public void setIntOnly() {
        comicNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^[0-9]*") || newValue.matches("[a-zA-Z]*")) {
                comicNumber.setText(newValue.replaceAll("[^0-9]*", ""));
            }
        });
    }

    private void setComic() throws Exception {
        int n = Integer.parseInt(comicNumber.getText());
        Image comic;

        try {
            setLabels(Util.getComicInfo(n), Util.getComicInfo(n).getString("title"));
            comic = new Image(Util.getComic(n));
        } catch(Exception e) {
            comic = new Image(xkcdApp.class.getResourceAsStream("resources/images/404.png"));
            String current = "The current comic's number is " + Util.getComicInfo(0).getInt("num");
            setLabels(null, current + ".");
        }

        comicImage.setFitHeight(640);
        comicImage.setFitWidth(640);
        comicImage.setPreserveRatio(true);
        comicImage.setImage(comic);
    }

    public void showComic() throws Exception {
        if(!comicNumber.getText().isEmpty()) {
            setComic();
        }
    }

    public void setKeybindings () {
        setIntOnly();

        comicNumber.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
            int changed = Integer.parseInt(comicNumber.getText());

            if(key == KeyCode.UP) {
                comicNumber.setText(String.valueOf(changed + 1));
            }

            else if(key == KeyCode.DOWN) {
                comicNumber.setText(String.valueOf(changed - 1));
            }

            comicNumber.positionCaret(comicNumber.getText().length());

            if(key == KeyCode.ENTER) {
                try {
                    setComic();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    public void setMenu() {
        aboutItem.setOnAction(event -> Util.openUrl("https://github.com/clairebearz32bit/xkcdApp"));
        quitItem.setOnAction(event -> Platform.exit());
    }
}
