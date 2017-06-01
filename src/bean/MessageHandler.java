package bean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;

/**
 * Created by 区枫华 on 2017/5/15.
 */
public class MessageHandler {

    public static final String DETAIL = "detail";
    public static final String DATA = "data";

    /**
     * 发送简单消息
     * @param writer PrintWriter
     * @param flag 成功或失败
     * @param key 键名
     * @param detail 细节描述
     */
    public static void sendDetailMessage(PrintWriter writer, boolean flag,String key, Object detail){
        String status;
        if(flag){
            status = "success";
        }else{
            status = "fail";
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("status",status);
            jsonObject.put(key,detail);
            writer.write(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
