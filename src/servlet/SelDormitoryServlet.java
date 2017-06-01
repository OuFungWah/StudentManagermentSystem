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
 * 查询宿舍接口
 * Created by 区枫华 on 2017/5/9.
 */
@WebServlet("/SelDormitoryServlet")
public class SelDormitoryServlet extends HttpServlet {

    private int c = 0;
    private int floor = 0;
    private int num = 0;

    /**
     * @see SelDormitoryServlet #HttpServlet()
     */
    public SelDormitoryServlet() {

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
        try {
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json;charset=UTF-8");
            JSONObject jsonObject = new JSONObject(req.getParameter("data"));
            if (jsonObject.has("c")) {
                c = jsonObject.getInt("c");
            }
            if (jsonObject.has("floor")) {
                floor = jsonObject.getInt("floor");
            }
            if (jsonObject.has("num")) {
                num = jsonObject.getInt("num");
            }

            DormitoryDao dao = new DormitoryDao();
            List<DormitoryBean> list = dao.findDormitorys(c, floor, num);
            JSONObject respJson = new JSONObject();
            JSONArray array = new JSONArray();
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    DormitoryBean bean = list.get(i);
                    JSONObject temp = new JSONObject();
                    temp.put("c", bean.getD_c());
                    temp.put("num", bean.getD_num());
                    temp.put("floor", bean.getD_floor());
                    temp.put("bed", bean.getD_bed());
                    temp.put("price", bean.getD_price());
                    array.put(temp);
                }
                MessageHandler.sendDetailMessage(resp.getWriter(), true, MessageHandler.DATA, array);
            } else {
                MessageHandler.sendDetailMessage(resp.getWriter(), false, MessageHandler.DETAIL, "没有数据");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            MessageHandler.sendDetailMessage(resp.getWriter(), false, MessageHandler.DETAIL, e.getMessage());
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
        doGet(req, resp);
    }
}
