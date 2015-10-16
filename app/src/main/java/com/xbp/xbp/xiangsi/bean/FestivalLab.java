package com.xbp.xbp.xiangsi.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/14.
 */
public class FestivalLab {

    public static FestivalLab mInstance;
    private List<Festival> mFestivals=new ArrayList<Festival>();

    private List<Msg>mMsgs=new ArrayList<Msg>();

    /**
     * 可以放入数据库
     */
    private FestivalLab(){
        mFestivals.add(new Festival(1,"国庆节"));
        mFestivals.add(new Festival(2,"中秋节"));
        mFestivals.add(new Festival(3,"元旦"));
        mFestivals.add(new Festival(4,"春节"));
        mFestivals.add(new Festival(5,"端午节"));
        mFestivals.add(new Festival(6,"七夕节"));
        mFestivals.add(new Festival(7,"圣诞节"));
        mFestivals.add(new Festival(8,"儿童节"));

        mMsgs.add(new Msg(1,1,"国庆长假又来到，送上关怀请记牢，节前劳累抖落掉，远离烦心与苦恼，带上愉快的心情，投入幸福的怀抱，结伴同行风景俏，愉悦身心共欢笑，感受生活的美好，迎接快乐的风暴，喜笑颜开乐逍遥！国庆节快乐！"));
        mMsgs.add(new Msg(2,1," 国庆到来，惦记在怀。合理饮食，身体不赖。粗细搭配，少荤多菜。暴饮暴食，千万不该。酒喝三分，莫图豪迈。熬夜娱乐，自我伤害。关怀贴士，真心奉上，国庆假期，欢乐开怀！"));
        mMsgs.add(new Msg(3,1,"国庆节，祝你微笑像花儿一样，皮肤像雪儿一样，心情像水儿一样，身体像树儿一样，幸运像星儿一样，幸福像地下水儿一样，用之不尽，永远幸福。"));
        mMsgs.add(new Msg(4,1,"不管日子多么忙碌，千万不要忘了休息。不论岁月怎么奔波，请你一定注意身体。任由季节千变万化，别让烦恼纠缠着你。别问每天变换的天气，人生总有狂风暴雨。国庆到了，祝福送上，愿开心快乐过长假！"));
        mMsgs.add(new Msg(5,1,"国庆节长假，我种上芙蓉花，栽上摇钱树，捉只吉祥鸟，摘颗幸运星，栽根富贵竹，叠只平安符，全部送给你，祝你富贵荣华，吉祥平安，幸运快乐！"));



        mMsgs.add(new Msg(1,2,"国庆长假又来到，送上关怀请记牢，节前劳累抖落掉，远离烦心与苦恼，带上愉快的心情，投入幸福的怀抱，结伴同行风景俏，愉悦身心共欢笑，感受生活的美好，迎接快乐的风暴，喜笑颜开乐逍遥！国庆节快乐！"));
        mMsgs.add(new Msg(2,2," 国庆到来，惦记在怀。合理饮食，身体不赖。粗细搭配，少荤多菜。暴饮暴食，千万不该。酒喝三分，莫图豪迈。熬夜娱乐，自我伤害。关怀贴士，真心奉上，国庆假期，欢乐开怀！"));
        mMsgs.add(new Msg(3,2,"国庆节，祝你微笑像花儿一样，皮肤像雪儿一样，心情像水儿一样，身体像树儿一样，幸运像星儿一样，幸福像地下水儿一样，用之不尽，永远幸福。"));

        mMsgs.add(new Msg(1,3,"国庆长假又来到，送上关怀请记牢，节前劳累抖落掉，远离烦心与苦恼，带上愉快的心情，投入幸福的怀抱，结伴同行风景俏，愉悦身心共欢笑，感受生活的美好，迎接快乐的风暴，喜笑颜开乐逍遥！国庆节快乐！"));
        mMsgs.add(new Msg(2,3," 国庆到来，惦记在怀。合理饮食，身体不赖。粗细搭配，少荤多菜。暴饮暴食，千万不该。酒喝三分，莫图豪迈。熬夜娱乐，自我伤害。关怀贴士，真心奉上，国庆假期，欢乐开怀！"));
        mMsgs.add(new Msg(3,3,"国庆节，祝你微笑像花儿一样，皮肤像雪儿一样，心情像水儿一样，身体像树儿一样，幸运像星儿一样，幸福像地下水儿一样，用之不尽，永远幸福。"));

        mMsgs.add(new Msg(1,4,"国庆长假又来到，送上关怀请记牢，节前劳累抖落掉，远离烦心与苦恼，带上愉快的心情，投入幸福的怀抱，结伴同行风景俏，愉悦身心共欢笑，感受生活的美好，迎接快乐的风暴，喜笑颜开乐逍遥！国庆节快乐！"));
        mMsgs.add(new Msg(2,4," 国庆到来，惦记在怀。合理饮食，身体不赖。粗细搭配，少荤多菜。暴饮暴食，千万不该。酒喝三分，莫图豪迈。熬夜娱乐，自我伤害。关怀贴士，真心奉上，国庆假期，欢乐开怀！"));
        mMsgs.add(new Msg(3,4,"国庆节，祝你微笑像花儿一样，皮肤像雪儿一样，心情像水儿一样，身体像树儿一样，幸运像星儿一样，幸福像地下水儿一样，用之不尽，永远幸福。"));
    }

    /**
     * 通过节日短信分类查询短信息
     * @param fesId
     * @return
     */
        public List<Msg>getMsgsByFestivalId(int fesId){
            List<Msg>msgs=new ArrayList<>();
            for(Msg msg:mMsgs){
                if(msg.getFestivalId()==fesId){
                    msgs.add(msg);
                }

            }
            return msgs;
        }

    public Msg getMsgByMsgId(int id){
        for(Msg msg:mMsgs){
            if(msg.getId()==id){
                return  msg;
            }
        }
        return  null;
    }

    /**
     * 返回Festival
     * @return
     */
    public List<Festival>getmFestivals(){
        return new ArrayList<>(mFestivals);
    }

    /**
     * 通过Id返回Festival
     * @param fesId
     * @return
     */
    public Festival getFestivalById(int fesId){
        for(Festival festival:mFestivals){
            if(festival.getId()==fesId){
                return festival;
            }
        }
        return  null;
    }

    /**
     * 单例模式创建一个Festival
     * @return
     */
    public static FestivalLab getInstance(){
        if(mInstance==null)
        {
            synchronized (FestivalLab.class){
                if(mInstance==null){
                    mInstance=new FestivalLab();
                }
            }
        }
        return mInstance;
    }
}
