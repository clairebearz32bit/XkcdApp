package xkcd.src;

import javafx.application.Platform;
import javafx.event.EventHandler;
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
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

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
    public ToggleGroup comicScale;
    public RadioMenuItem halfScale, threeQuarterScale, fullScale, fiveQuarterScale;

    public int n;
    public double comicSize = 1;

    private void println(Object obj) {
        System.out.println(obj);
    }

    private void setImg() throws IOException, InterruptedException {
        n  = Integer.parseInt(comicNumber.getText());
        setLabels(Util.getComicInfo(n));
        Image comic = new Image(Util.getComic(n));
        comicImage.setFitHeight(640);
        comicImage.setFitWidth(640);
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

                catch(InterruptedException | IOException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    public void setMenu() {
        aboutItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://github.com/clairebearz32bit/xkcdApp"));
                    } catch (IOException | URISyntaxException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });

        quitItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });
    }

    public void setComicScale() {
        halfScale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                comicSize = 0.5;
            }
        });

        threeQuarterScale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                comicSize = 0.75;
            }
        });

        fullScale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                comicSize = 1;
            }
        });

        halfScale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                comicSize = 1.25;
            }
        });
    }
}
