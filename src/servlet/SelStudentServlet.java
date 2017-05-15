package servlet;

import bean.MessageHandler;
import bean.Student;
import dao.StudentDao;
import org.hibernate.query.Query;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 区枫华 on 2017/5/7.
 */
@WebServlet("/SelStudentServlet")
public class SelStudentServlet extends HttpServlet {

    /**
     * @see SelStudentServlet #HttpServlet()
     */
    public SelStudentServlet(){

    }

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @see #doGet(HttpServletRequest, HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            JSONObject data = new JSONObject(req.getParameter("data"));
            int s_num = data.getInt("num");
            StudentDao dao = new StudentDao();
            Student student = dao.findById(s_num);
            JSONObject jsonObject = new JSONObject();
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json;charset=UTF-8");
            jsonObject.put("num",student.getS_num());
            jsonObject.put("name",student.getS_name());
            jsonObject.put("sex",student.getS_sex());
            jsonObject.put("age",student.getS_age());
            jsonObject.put("college",student.getS_college());
            jsonObject.put("department","C"+student.getS_department());
            jsonObject.put("grade",student.getS_grade());
            jsonObject.put("tel",student.getS_tel());
            jsonObject.put("dormitory_num",student.getS_dormitory_num());
            MessageHandler.sendDetailMessage(resp.getWriter(),true,MessageHandler.DATA,jsonObject);
        }catch (Exception e){
            MessageHandler.sendDetailMessage(resp.getWriter(),false,MessageHandler.DATA,e.getMessage());
        }
    }

    /**
     *
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
