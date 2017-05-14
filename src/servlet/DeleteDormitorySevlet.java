package servlet;

import bean.DormitoryBean;
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
@WebServlet("/DeleteDormitorySevlet")
public class DeleteDormitorySevlet extends HttpServlet {

    /**
     * @see DeleteDormitorySevlet #HttpServlet()
     */
    public DeleteDormitorySevlet(){

    }

    /**
     * @see #doGet(HttpServletRequest, HttpServletResponse)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        DormitoryDao dao = new DormitoryDao();
        try {
            JSONObject data = new JSONObject(req.getParameter("data"));
            List<DormitoryBean> list = dao.findDormitorys(data.getInt("c"),data.getInt("num"));
            DormitoryBean bean = list.get(0);
            dao.delete(bean);
            JSONObject respJson = new JSONObject();
            respJson.put("status","success");
            respJson.put("detail","删除成功");
            resp.getWriter().write(respJson.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            JSONObject respJson = new JSONObject();
            try{
                respJson.put("status","fail");
                respJson.put("detail",e.getMessage());
                resp.getWriter().write(respJson.toString());
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    /**
     * @see #doPost(HttpServletRequest, HttpServletResponse)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
