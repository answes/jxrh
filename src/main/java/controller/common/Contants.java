package controller.common;

import chart.domain.TradeMaketEntity;
import chart.util.HttpUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

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
}
