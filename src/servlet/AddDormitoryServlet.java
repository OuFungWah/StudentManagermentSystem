package servlet;

import bean.DormitoryBean;
import dao.DormitoryDao;
import org.json.JSONException;
import org.json.JSONObject;
import tools.ManageDormitory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * 添加宿舍的接口
 * Created by 区枫华 on 2017/4/16.
 */
@WebServlet("/AddDormitoryServlet")
public class AddDormitoryServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDormitoryServlet() {

    }

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @see HttpServlet#doPost(HttpServletRequest, HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        try{
            JSONObject data = new JSONObject(req.getParameter("data"));
            DormitoryBean bean = new DormitoryBean();
            bean.setD_c(data.getInt("c"));
            bean.setD_floor(data.getInt("floor"));
            bean.setD_num(data.getInt("num"));
            bean.setD_bed(data.getInt("bed"));
            bean.setD_price(data.getInt("price"));
            DormitoryDao dao = new DormitoryDao();
            dao.addDormitory(bean);
            JSONObject respJson = new JSONObject();
            respJson.put("status","success");
            respJson.put("detail","操作成功");
            resp.getWriter().write(respJson.toString());
        }catch (Exception e){
            JSONObject respJson = new JSONObject();
            try {
                respJson.put("status","fail");
                respJson.put("detail",e.getMessage());
                resp.getWriter().write(respJson.toString());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }

    }

}
