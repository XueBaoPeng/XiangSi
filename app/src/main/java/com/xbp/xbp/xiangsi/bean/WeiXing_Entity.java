package com.xbp.xbp.xiangsi.bean;

/**
 * Created by Administrator on 2015/10/15.
 */
public class WeiXing_Entity  {

    /**
     * id : wechat_20151015058634
     * title : 女性大姨妈要多吃葡萄干的结果，我被答案吓傻了！
     * source : 每天扎头发
     * firstImg : http://zxpic.gtimg.com/infonew/0/wechat_pics_-2013570.jpg/640
     * mark :
     * url : http://v.juhe.cn/weixin/redirect?wid=wechat_20151015058634
     */

    private String id;
    private String title;
    private String source;
    private String firstImg;
    private String mark;
    private String url;

    public static WeiXing_Entity objectFromData(String str) {

        return new com.google.gson.Gson().fromJson(str, WeiXing_Entity.class);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setFirstImg(String firstImg) {
        this.firstImg = firstImg;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getFirstImg() {
        return firstImg;
    }

    public String getMark() {
        return mark;
    }

    public String getUrl() {
        return url;
    }
}
