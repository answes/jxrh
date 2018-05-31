package controller.user;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import utils.Constant;
import utils.ControlledStage;
import utils.StageController;
import utils.VerifyUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 14:54 2018/05/21
 * @Modificd :
 */
public class RegisterController implements ControlledStage, Initializable {
    @FXML
    private Label tip1;
    @FXML
    private Label tip2;
    @FXML
    private Label tip3;
    @FXML
    private Label tip4;
    @FXML
    private Label tip5;
    @FXML
    private WebView web;
    @FXML
    private Button submit;
    @FXML
    private AnchorPane next2;
    @FXML
    private TextField mobile;
    @FXML
    private TextField code;
    @FXML
    private Button sendCode;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField rePassword;
    @FXML
    private ComboBox<String> proxy;
    @FXML
    private Label tips1;
    @FXML
    private AnchorPane next3;
    @FXML
    private TextField name;
    @FXML
    private ComboBox<String> type;
    @FXML
    private TextField cardNo;
    @FXML
    private Label tips2;
    @FXML
    private AnchorPane next4;
    @FXML
    private AnchorPane next5;
    @FXML
    private ImageView icon,ivID1,ivID2;

    /**
     * 步骤0，阅读了风险揭示，1，填写了手机号码，2，进行了实名认证，3，已完成
     */
    private int flag = 0;
    public static WebEngine webEngine;
    private StageController myController;

    private String S = "";
    private int tmp = 120;
    Timeline animation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hideTip();
        showTip(1);
        initWeb();
        initOther();

    }

    private void initOther() {
        proxy.getItems().addAll("2001","2002");
        type.getItems().addAll("身份证");
        type.setValue("身份证");
        proxy.setValue("2001");
        icon.setImage(new Image(getClass().getClassLoader().getResource("image/success.png").toExternalForm()));

        sendCode.setOnAction(v->{
            if(verifyMobile()){
                sendCode.setDisable(true);
                animation = new Timeline(new KeyFrame(Duration.millis(1000), e -> timelabel()));
                animation.setCycleCount(Timeline.INDEFINITE);
                animation.play();
            }
        });
    }

    private void timelabel() {
        tmp--;
        S = tmp + "";
        sendCode.setText(S);
        if(tmp==0){
            animation.stop();
            sendCode.setText("重新发送");
            sendCode.setDisable(false);
            tmp=120;
        }

    }

    private void initWeb() {
        web.setContextMenuEnabled(false);
        webEngine = web.getEngine();
        webEngine.load(getClass().getClassLoader().getResource("html/riskReveal.html").toExternalForm());
    }

    @FXML
    private void hideTip() {
        tip1.setBackground(new Background(new BackgroundFill(Color.grayRgb(217), null, null)));
        tip2.setBackground(new Background(new BackgroundFill(Color.grayRgb(217), null, null)));
        tip3.setBackground(new Background(new BackgroundFill(Color.grayRgb(217), null, null)));
        tip4.setBackground(new Background(new BackgroundFill(Color.grayRgb(217), null, null)));
        tip5.setBackground(new Background(new BackgroundFill(Color.grayRgb(217), null, null)));
    }

    @FXML
    private void showTip(int index) {
        hideTip();
        switch (index) {
            case 1:
                tip1.setBackground(new Background(new BackgroundFill(Color.grayRgb(161), null, null)));
                break;
            case 2:
                tip2.setBackground(new Background(new BackgroundFill(Color.grayRgb(161), null, null)));
                break;
            case 3:
                tip3.setBackground(new Background(new BackgroundFill(Color.grayRgb(161), null, null)));
                break;
            case 4:
                tip4.setBackground(new Background(new BackgroundFill(Color.grayRgb(161), null, null)));
                break;
            case 5:
                tip5.setBackground(new Background(new BackgroundFill(Color.grayRgb(161),null,null)));
                break;
        }
    }

    public void next(Event event) {
        switch (flag) {
            case 0:
                showTip(2);
                flag = 1;
                web.setVisible(false);
                next2.setVisible(true);
                break;
            case 1:
                tips1.setText("");
                if (verifyMobile() && verifyParam1()) {
                    flag = 2;
                    showTip(3);
                    next2.setVisible(false);
                    next3.setVisible(true);
                }
                break;
            case 2:
                tips2.setText("");
                if(verifyParam2()){
                    flag = 3;
                    submit.setText("下一步");
                    showTip(4);
                    next3.setVisible(false);
                    next4.setVisible(true);
                }
                break;
            case 3:
                flag=4;
                showTip(5);
                submit.setText("完成");
                next4.setVisible(false);
                next5.setVisible(true);
                break;
            case 4:
                flag = 0;
                next5.setVisible(false);
                web.setVisible(true);
                showTip(1);
                submit.setText("下一步");
                initWeb();
                myController.setStage(Constant.LOGIN_ID, Constant.REGISTER_ID);
                break;
        }
    }


    private boolean verifyParam2(){
        if(VerifyUtil.ifStringEmpty(name.getText())){
            tips2.setText("真实姓名不能为空");
            return false;
        }
        if(VerifyUtil.isUsername(name.getText())){
            tips2.setText("真实姓名格式错误");
            return false;
        }
        if(VerifyUtil.ifStringEmpty(cardNo.getText())){
            tips2.setText("证件号码不能为空");
            return false;
        }
        if(VerifyUtil.isIDCard(cardNo.getText())){
            tips2.setText("证件号格式错误");
            return false;
        }
        return true;
    }

    private  boolean verifyParam1(){
        if(VerifyUtil.ifStringEmpty(code.getText())){
            tips1.setText("验证码不能为空");
            return false;
        }
        if(VerifyUtil.ifStringEmpty(password.getText()) ||VerifyUtil.ifStringEmpty(rePassword.getText())){
            tips1.setText("密码不能为空");
            return false;
        }
        if(password.getText().length()<6 || password.getText().length()>12){
            tips1.setText("密码长度在6到12位");
            return false;
        }
        if(!password.getText().equals(rePassword.getText())){
            tips1.setText("两次密码输入不一致");
            return false;
        }
        if(VerifyUtil.ifStringEmpty(proxy.getValue().toString())){
            tips1.setText("请选择代理商");
            return false;
        }
        return true;
    }

    /**
     * 校验手机号码等
     *
     * @return
     */
    private boolean verifyMobile() {
        String mobileText = mobile.getText();
        if (VerifyUtil.ifStringEmpty(mobileText)) {
            mobile.setPromptText("手机号码不能为空");
            tips1.setText("手机号码不能为空");
            return false;
        }
        if (VerifyUtil.isMobile(mobileText)) {
            tips1.setText("错误的手机号码");
            return false;
        }
        return true;
    }

    @Override
    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }

    public void exit(Event event) {
        myController.setStage(Constant.LOGIN_ID, Constant.REGISTER_ID);
    }

    final FileChooser fileChooser = new FileChooser();
    public void btID1(ActionEvent event) {
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(myController.getStage(Constant.REGISTER_ID));
        if(file!=null){
            System.out.println(file.getAbsolutePath());
            try {
                ivID1.setImage(new Image(new FileInputStream(file)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void btID2(ActionEvent event) {
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(myController.getStage(Constant.REGISTER_ID));
        if(file!=null){
            try {
                ivID2.setImage(new Image(new FileInputStream(file)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("选择图片");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }
}
