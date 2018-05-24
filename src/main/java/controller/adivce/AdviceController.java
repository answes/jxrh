package controller.adivce;

import utils.ControlledStage;
import utils.StageController;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 14:57 2018/05/21
 * @Modificd :
 */
public class AdviceController implements ControlledStage {
    private StageController myController;
    @Override
    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }
}
