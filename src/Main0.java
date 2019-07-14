import controllers.CateringAppManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import views.MainController;


public class Main extends Application {

    private CateringAppManager app;

    @Override
    public void start(Stage primaryStage) throws Exception {

    	System.out.println("a");
      this.app = CateringAppManager.getInstance();
      FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("main.fxml"));
      Parent main = mainLoader.load();
      Scene mainScene = new Scene(main);
      primaryStage.setScene(mainScene);
      primaryStage.setWidth(800);
      primaryStage.setHeight(600);
      primaryStage.setMaximized(true);
      primaryStage.show();
      System.out.println("b");
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
