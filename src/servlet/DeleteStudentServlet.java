package servlet;

import bean.Student;
import dao.StudentDao;
import org.hibernate.HibernateException;
import org.hibernate.internal.ExceptionMapperStandardImpl;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 区枫华 on 2017/5/2.
 */
@WebServlet("/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {

    /**
     * @see DeleteStudentServlet #HttpServlet()
     */
    public DeleteStudentServlet() {

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
        JSONObject reponseJsonObject = new JSONObject();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        try {
            JSONObject data = new JSONObject(req.getParameter("data"));
            int s_num = data.getInt("num");
            Student student = new Student();
            student.setS_num(s_num);
            StudentDao studentDao = new StudentDao();
            studentDao.delete(student);
            reponseJsonObject.put("status", "success");
            reponseJsonObject.put("detail", "操作成功");
            resp.getWriter().write(reponseJsonObject.toString());

        } catch (Exception e) {
            try {
                e.printStackTrace();
                reponseJsonObject.put("status", "fail");
                reponseJsonObject.put("detail", e.getMessage());
                resp.getWriter().write(reponseJsonObject.toString());
            } catch (JSONException jsonE) {
                jsonE.printStackTrace();
            }
        }
    }

    /**
     * @see #doPost(HttpServletRequest, HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}