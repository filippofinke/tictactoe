package ch.supsi.tictactoe;

import ch.supsi.tictactoe.controller.*;
import ch.supsi.tictactoe.model.*;
import ch.supsi.tictactoe.player.ComputerPlayer;
import ch.supsi.tictactoe.player.HumanPlayer;
import ch.supsi.tictactoe.service.LocalizationService;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.net.URL;

import javafx.fxml.FXMLLoader;
public class AppFx extends Application {

    private final PreferencesModel preferencesModel;
    private final GameModel gameModel;

    public AppFx() {
        preferencesModel = PreferencesModel.getInstance();
        LocalizationModel localizationModel = LocalizationModel.getInstance();
        localizationModel.init("i18n.translations", preferencesModel.getLocale());
        LocalizationService.init(localizationModel);

        gameModel = GameModel.getInstance();
    }

    @Override
    public void start(Stage stage) throws Exception {

        URL editMenuFxmlUrl = getClass().getResource("/editmenu.fxml");
        if (editMenuFxmlUrl == null) {
            return;
        }

        FXMLLoader editMenuLoader = new FXMLLoader(editMenuFxmlUrl);
        editMenuLoader.setControllerFactory(c -> new EditMenuController(preferencesModel));
        Menu editMenu = editMenuLoader.load();
        editMenu.textProperty().bind(LocalizationService.createStringBinding("ui.menu.edit"));

        URL fileMenuFxmlUrl = getClass().getResource("/filemenu.fxml");
        if (fileMenuFxmlUrl == null) {
            return;
        }

        FXMLLoader fileMenuLoader = new FXMLLoader(fileMenuFxmlUrl);
        fileMenuLoader.setControllerFactory(c -> new FileMenuController(gameModel));
        Menu fileMenu = fileMenuLoader.load();
        fileMenu.textProperty().bind(LocalizationService.createStringBinding("ui.menu.file"));

        URL helpMenuFxmlUrl = getClass().getResource("/helpmenu.fxml");
        if (helpMenuFxmlUrl == null) {
            return;
        }


        FXMLLoader helpMenuLoader = new FXMLLoader(helpMenuFxmlUrl);
        helpMenuLoader.setControllerFactory(c -> new HelpMenuController(PropertiesModel.getInstance()));
        Menu helpMenu = helpMenuLoader.load();
        helpMenu.textProperty().bind(LocalizationService.createStringBinding("ui.menu.help"));


        URL gridFxmlUrl = getClass().getResource("/grid.fxml");
        if (gridFxmlUrl == null) {
            return;
        }

        FXMLLoader gridFxmlLoader = new FXMLLoader(gridFxmlUrl);
        Parent grid = gridFxmlLoader.load();

        URL infoBarFxmlUrl = getClass().getResource("/infobar.fxml");
        if (infoBarFxmlUrl == null) {
            return;
        }

        FXMLLoader infoBarLoader = new FXMLLoader(infoBarFxmlUrl);
        Parent infoBar = infoBarLoader.load();
        ((InfoBarController) infoBarLoader.getController())
                .showMessage(LocalizationService.createStringBinding("user.message.welcome"));


        HumanPlayer player1 = new HumanPlayer( gridFxmlLoader.getController());
        ((GameController)gridFxmlLoader.getController()).setPlayer(player1);
        ComputerPlayer player2 = new ComputerPlayer();
        gameModel.addPlayer(player1);
        gameModel.addPlayer(player2);

        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        menuBar.getMenus().addAll(
                fileMenu,
                editMenu,
                helpMenu
        );

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(grid);
        borderPane.setBottom(infoBar);

        // create a new scene with the border pane
        Scene scene = new Scene(borderPane);

        // put the scene onto the stage
        stage.setTitle("Tic Tac Toe");
        stage.setResizable(false);
        stage.setScene(scene);

        // on close
        stage.setOnCloseRequest(e -> {
            // consume the event
            e.consume();

            preferencesModel.save();
            // quit
            FileMenuController fileMenuController = fileMenuLoader.getController();
            fileMenuController.quit(null);
        });

        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
