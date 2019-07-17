import controllers.CateringAppManager;
import views.*;
import models.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
      CateringAppManager.initialize();
      FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("views/event.fxml"));
      Parent main = mainLoader.load();
      Scene mainScene = new Scene(main);
      primaryStage.setScene(mainScene);
      primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
