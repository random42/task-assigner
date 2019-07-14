import controllers.CateringAppManager;
import views.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

    private CateringAppManager app;

    @Override
    public void start(Stage primaryStage) throws Exception {

    	System.out.println("a");
      this.app = CateringAppManager.getInstance();
      System.out.println("b");
      FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("views/main.fxml"));
      Parent main = mainLoader.load();
      System.out.println("b");
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
