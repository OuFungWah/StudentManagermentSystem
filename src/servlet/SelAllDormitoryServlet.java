package servlet;

import bean.DormitoryBean;
import bean.MessageHandler;
import dao.DormitoryDao;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 区枫华 on 2017/5/9.
 */
@WebServlet("/SelAllDormitoryServlet")
public class SelAllDormitoryServlet extends HttpServlet {

    /**
     * @see SelAllDormitoryServlet #HttpServlet()
     */
    public SelAllDormitoryServlet() {

    }

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @see #doGet(HttpServletRequest, HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        JSONObject respJson = new JSONObject();
        JSONArray array = new JSONArray();
        try {
//            尝试返回结果
            DormitoryDao dao = new DormitoryDao();
            List<DormitoryBean> list = dao.findall();
            for (int i = 0; i < list.size(); i++) {
                DormitoryBean bean = list.get(i);
                JSONObject temp = new JSONObject();
                temp.put("c", bean.getD_c());
                temp.put("num", bean.getD_num());
                temp.put("price", bean.getD_price());
                temp.put("bed", bean.getD_bed());
                temp.put("floor", bean.getD_floor());
                array.put(temp);
            }
            //成功
            MessageHandler.sendDetailMessage(resp.getWriter(),true,MessageHandler.DATA,array);
        } catch (Exception e) {
//            异常抛出
            MessageHandler.sendDetailMessage(resp.getWriter(),false,MessageHandler.DETAIL,e.getMessage());
        }

    }

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @see #doPost(HttpServletRequest, HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
