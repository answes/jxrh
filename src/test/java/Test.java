import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 16:47 2018/05/23
 * @Modificd :
 */
public class Test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: rgb(78.0,163.0,248.0);");
        gridPane.setPrefHeight(32);
        gridPane.setAlignment(Pos.CENTER_LEFT);
        Label label = new Label("title");
        label.setFont(Font.font(14));
        label.setTextFill(Paint.valueOf("white"));
//        ImageView imageView = new ImageView(getClass().getClassLoader().getResource("iamge/no.png").toExternalForm());
//        imageView.setFitHeight(24);
//        imageView.setFitWidth(24);
//        label.setGraphic(imageView);
        Button minButton = new Button("—");
        Button amxButton = new Button("口");
        Button closeButton = new Button("X");
        minButton.setStyle("-fx-base: rgb(243,243,243); -fx-border-color: rgb(243,243,243); -fx-border-width: 0.1; "
                + "-fx-max-height: infinity;-fx-text-fill: white ; -fx-border-image-insets: 0;");
        amxButton.setStyle("-fx-base: rgb(243,243,243); -fx-border-color: rgb(243,243,243); -fx-border-width: 0.1; "
                + "-fx-max-height: infinity;-fx-text-fill: white ; -fx-border-image-insets: 0;");
        closeButton.setStyle("-fx-base: rgb(255,128,128); -fx-border-color: rgb(243,243,243); -fx-border-width: 0.1; "
                + "-fx-max-height: infinity;-fx-text-fill: white ; -fx-border-image-insets: 0;");
        minButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.setIconified(true);
            }
        });
        amxButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.setMaximized(!primaryStage.isMaximized());
            }
        });
        closeButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        gridPane.addColumn(0, label);
        GridPane.setHgrow(label, Priority.ALWAYS);
        gridPane.addColumn(1, minButton);
        gridPane.addColumn(2, amxButton);
        gridPane.addColumn(3, closeButton);
//        box.getChildren().add(gridPane);
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);

    }
    public static void main(String[] arg){
        launch(arg);
    }
}
