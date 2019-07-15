import controllers.CateringAppManager;
import views.MainController;
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
      FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("views/main.fxml"));
      Parent main = mainLoader.load();
      Scene mainScene = new Scene(main);
      MainController mainController = mainLoader.getController();
      primaryStage.setScene(mainScene);
      primaryStage.setWidth(800);
      primaryStage.setHeight(600);
      primaryStage.setMaximized(true);
      primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
