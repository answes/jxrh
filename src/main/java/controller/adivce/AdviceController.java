package controller.adivce;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import utils.Constant;
import utils.ControlledStage;
import utils.StageController;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 14:57 2018/05/21
 * @Modificd :
 */
public class AdviceController implements ControlledStage,Initializable {
    @FXML
    private WebView web;
    @FXML
    private WebEngine webEngine;

    private StageController myController;
    @Override
    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    private void init() {
        web.setContextMenuEnabled(false);
        webEngine = web.getEngine();
        webEngine.loadContent("<p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; clear: both; font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif; font-size: medium; white-space: normal; text-align: center;\"><strong style=\"margin: 0px; padding: 0px;\"><span style=\"margin: 0px; padding: 0px; font-size: 20px;\">【彼得天珠】临时停牌公告</span></strong></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; clear: both; font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif; font-size: medium; white-space: normal; text-align: center;\"><span style=\"margin: 0px; padding: 0px; font-size: 16px;\"><strong style=\"margin: 0px; padding: 0px;\">锦绣融和2018（控）第 008号&nbsp;</strong></span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; clear: both; font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif; font-size: medium; white-space: normal; text-align: center;\"><span style=\"margin: 0px; padding: 0px; font-size: 16px;\"><strong style=\"margin: 0px; padding: 0px;\"><br/></strong></span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; clear: both; font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif; font-size: medium; white-space: normal;\">尊敬的交易商及各合作单位：</p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; clear: both; font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif; font-size: medium; white-space: normal;\"><br/></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; clear: both; font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif; font-size: medium; white-space: normal; text-indent: 2em;\">《彼得天珠》（产品代码：600001）已通过本中心增发预审，各项工作正在开展。为保证公平信息披露，维护投资者利益，避免造成产品价格异常波动，根据《锦绣融和交易规则（试行）》、《锦绣融和风险控制管理办法（试行）》规定，《彼得天珠》自 2018 年5月28日开市起停牌5天，2018 年6月4日9：30恢复正常交易。</p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; clear: both; font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif; font-size: medium; white-space: normal;\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;特此公告！&nbsp;</p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; clear: both; font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif; font-size: medium; white-space: normal;\">&nbsp;</p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; clear: both; font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif; font-size: medium; white-space: normal; text-align: right;\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 锦绣融和</p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif; font-size: medium; white-space: normal; text-align: right;\">&nbsp; &nbsp; &nbsp; &nbsp; 2018年 5 月 28 日</p>");
//        webEngine.load("https://baidu.com");
    }

    public void close(ActionEvent event) {
        myController.setStage(Constant.MAIN_ID,Constant.ADVICE_ID);
    }
}
