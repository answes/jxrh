import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.Constant;
import utils.StageController;

import java.net.URL;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 14:08 2018/05/21
 * @Modificd :
 */
public class Main extends Application {
    private StageController stageController;

    @Override
    public void start(Stage primaryStage) throws Exception {

        stageController = new StageController();
        //将主舞台交给控制器处理
        stageController.setPrimaryStage("primaryStage", primaryStage);
        //注册eventBus
        //加载两个舞台，每个界面一个舞台
        stageController.loadStage(Constant.MAIN_ID, Constant.MAIN_VIEW_RES,StageStyle.TRANSPARENT);
        stageController.loadStage(Constant.ADVICE_ID, Constant.ADVICE_VIEW_RES, StageStyle.UNDECORATED);
        stageController.loadStage(Constant.LOGIN_ID, Constant.LOGIN_VIEW_RES,StageStyle.UNDECORATED);
        stageController.loadStage(Constant.REGISTER_ID, Constant.REGISTER_VIEW_RES,StageStyle.UNDECORATED);
        //显示MainView舞台
//        stageController.setStage(Constant.LOGIN_ID);
        stageController.setStage(Constant.REGISTER_ID);
    }

    public static void main(String[] arg){
        launch(arg);
    }
}
