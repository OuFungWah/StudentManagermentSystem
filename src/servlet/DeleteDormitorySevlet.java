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
 * Created by 区枫华 on 2017/5/9.
 */
@WebServlet("/DeleteDormitoryServlet")
public class DeleteDormitorySevlet extends HttpServlet {

    /**
     * @see DeleteDormitorySevlet #HttpServlet()
     */
    public DeleteDormitorySevlet() {

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
        DormitoryDao dao = new DormitoryDao();
        try {
            JSONObject data = new JSONObject(req.getParameter("data"));
            List<DormitoryBean> list = dao.findDormitorys(data.getInt("c"), 0,data.getInt("num"));
            DormitoryBean bean = list.get(0);
            dao.delete(bean);
            MessageHandler.sendDetailMessage(resp.getWriter(), true, MessageHandler.DETAIL, "删除成功");
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

    }
}
