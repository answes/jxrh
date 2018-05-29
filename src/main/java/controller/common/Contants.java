package controller.common;

import chart.domain.TradeMaketEntity;
import chart.util.HttpUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.javafx.scene.control.skin.TableViewSkin;
import javafx.collections.ListChangeListener;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by 京美电子 on 2018/5/29.
 */
public class Contants {

    /**
     * 获取所有市场
     * @return
     */
    public static List<TradeMaketEntity> getMarketList(){

        HttpUtils utils = new HttpUtils();
        String jsonStr = utils.get("/platform/trade/getMarketList.m");

        JSONObject jsonObject = JSON.parseObject(jsonStr);
        List<TradeMaketEntity> markets = JSON.parseArray(JSON.toJSONString(jsonObject.get("result")),TradeMaketEntity.class);

        return markets;
    }

    private static Method columnToFitMethod;

    static {
        try {
            columnToFitMethod = TableViewSkin.class.getDeclaredMethod("resizeColumnToFitContent", TableColumn.class, int.class);
            columnToFitMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void autoFitTable(TableView tableView) {
        tableView.getItems().addListener(new ListChangeListener<Object>() {
            @Override
            public void onChanged(Change<?> c) {
                for (Object column : tableView.getColumns()) {
                    try {
                        columnToFitMethod.invoke(tableView.getSkin(), column, -1);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
