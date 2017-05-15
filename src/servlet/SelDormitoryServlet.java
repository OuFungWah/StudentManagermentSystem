package servlet;

import bean.DormitoryBean;
import bean.MessageHandler;
import dao.DormitoryDao;
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
            int num = jsonObject.getInt("num");
            int c = jsonObject.getInt("c");

            DormitoryDao dao = new DormitoryDao();
            List<DormitoryBean> list = dao.findDormitorys(c, num);

            if (list != null) {
                DormitoryBean bean = list.get(0);
                JSONObject respJson = new JSONObject();
                respJson.put("c", bean.getD_c());
                respJson.put("num", bean.getD_num());
                respJson.put("floor", bean.getD_floor());
                respJson.put("bed", bean.getD_bed());
                respJson.put("price", bean.getD_price());
                MessageHandler.sendDetailMessage(resp.getWriter(),true,MessageHandler.DATA,respJson);
            } else {
                MessageHandler.sendDetailMessage(resp.getWriter(),false,MessageHandler.DETAIL,"没有数据");
            }
        } catch (JSONException e) {
            e.printStackTrace();
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
        doGet(req, resp);
    }
}
