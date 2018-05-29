package bean;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 9:40 2018/05/29
 * @Modificd :
 */
public class News extends BaseEntity{
    private String date;
    private String tilte;
    private String content;

    public News(Long id,String date, String tilte) {
        this.id = id;
        this.date = date;
        this.tilte = tilte;
        this.content="<p style=\"margin-top:21px;text-align:center;line-height:62px\"><br/></p><p style=\"text-align:center\"><span style=\"font-size:39px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\"><img src=\"http://xzjx.oss-cn-hangzhou.aliyuncs.com/image/1519816358869.jpg\" title=\"1519816358869.jpg\" alt=\"1519816358869.jpg\"/></span></p><p style=\"margin-top:21px;margin-right:0;margin-bottom:21px;margin-left: 0;text-indent:32px;line-height:41px\"><span style=\"font-size:18px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">“一带一路”倡议提出5年来，已经进入全面务实合作新阶段，沿线国家经贸合作更加密切，产能合作不断升级，贸易投资水平日益提升。接受证券时报记者采访的专家表示，今年各部委对 “一带一路”倡议的部署更具有针对性，更加打通互联互通的环节，工作也将更加见效，无疑，“一带一路”倡议将进一步纵深发展，全面推进。</span></p><p style=\"margin-top:21px;margin-right:0;margin-bottom:21px;margin-left: 0;text-indent:32px;line-height:41px\"><span style=\"font-size:18px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">进入2018年，多个部委对“一带一路”倡议做出新的部署，还出台了“一带一路”相关行动规划。据商务部透露，今年为推进“一带一路”建设，将加强与有关国家的战略和规划的对接；通过双边、多边等协定进一步提高贸易投资便利化的水平；继续完善公共服务体系，更新发布具体国家的投资指南；指导企业有效防范和化解风险。保监会与国家外汇管理局联手规范保险机构内保外贷业务，引导保险资金切实服务“一带一路”。</span></p><p style=\"margin-top:21px;margin-right:0;margin-bottom:21px;margin-left: 0;text-indent:32px;line-height:41px\"><span style=\"font-size:18px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">“一带一路”相关行动规划，包括气象局出台的《气象“一带一路”发展规划(2017~2025年)》、国家邮政局印发的《关于推进邮政业服务“一带一路”建设的指导意见》、海关总署发布的《推进“一带一路”沿线大通关合作行动计划(2018~2020年)》等。</span></p><p style=\"margin-top:21px;margin-right:0;margin-bottom:21px;margin-left: 0;text-indent:32px;line-height:41px\"><span style=\"font-size:18px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">商务部研究院国际市场研究所副所长白明接受证券时报记者采访时表示，“一带一路”倡议要全方位推进不仅要签署合作协议，政策沟通、设施联通、贸易畅通、资金融通、民心相通是“一带一路”愿景与行动的总框架，这些部委的新部署是把互联互通的子系统进一步全部打通。以海关总署发布的《推进“一带一路”沿线大通关合作行动计划(2018~2020年)》为例，这是充分发挥大通关合作在促进“一带一路”设施联通和贸易畅通中的基础性和关键性作用，将进一步提升沿线贸易安全和便利水平。因此中国不仅要提高本国的通关便利化水平，也需要改善“一带一路”沿线国家的通关设施。这样才会进一步促进“一带一路”国家间的合作。</span></p><p style=\"margin-top:21px;margin-right:0;margin-bottom:21px;margin-left: 0;text-indent:32px;line-height:41px\"><span style=\"font-size:18px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">此外，“一带一路”沿线国家经贸合作取得明显成效。商务部数据显示，2017年中国对“一带一路”沿线国家进出口总额达7.37万亿元人民币，同比增长17.8%，高于同期中国外贸整体增速3.6个百分点。从投资看，2017年，我国企业对沿线国家直接投资144亿美元，在沿线国家新签承包工程合同额1443亿美元，同比增长14.5%。</span></p><p style=\"margin-top:21px;margin-right:0;margin-bottom:21px;margin-left: 0;text-indent:32px;line-height:41px\"><span style=\"font-size:18px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">那么，对于2018年中国该如何继续深入推进“一带一路”建设，中国国际经济交流中心经济研究部副研究员刘向东指出，加快推进“一带一路”建设首先要推动政策沟通，实现共商和战略对接。为此，要在经济外交上多做工作，让外方了解“一带一路”倡议的内涵和操作思路。“一带一路”建设的首要条件是推动基础设施的联通，但“一带一路”建设不是援建项目，而是通过共建和公私合营共同推动基础设施互联互通，发挥设施联通对贸易投资的支撑作用。</span></p><p style=\"margin-top:21px;margin-right:0;margin-bottom:21px;margin-left: 0;text-indent:32px;line-height:41px\"><span style=\"font-size:18px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">刘向东指出，应发挥金融机构的资金融通作用，引导社会资本更多地流入“一带一路”建设项目。</span></p><p><br/></p>";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTilte() {
        return tilte;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
