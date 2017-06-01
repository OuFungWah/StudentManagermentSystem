package servlet;

import bean.MessageHandler;
import bean.Student;
import dao.StudentDao;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 区枫华 on 2017/5/7.
 */
@WebServlet("/SelStudentServlet")
public class SelStudentServlet extends HttpServlet {

    /**
     * @see SelStudentServlet #HttpServlet()
     */
    public SelStudentServlet() {

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
        resp.setContentType("utf-8");
        resp.setContentType("application/json;charset=UTF-8");
        try {
            JSONObject data = new JSONObject(req.getParameter("data"));
            HashMap<String, Object> map = new HashMap<>();
            if (data.has("name")) {
                map.put("name", data.getString("name"));
            }
            if (data.has("num")) {
                map.put("num", data.getString("num"));
            }
            if (data.has("c")) {
                map.put("c", data.getInt("c"));
            }
            if (data.has("d_num")) {
                map.put("d_num", data.getInt("d_num"));
            }
            StudentDao dao = new StudentDao();
            List<Student> list = dao.findStudents(map);
            JSONArray array = new JSONArray();
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    Student student = list.get(i);
                    JSONObject temp = new JSONObject();
                    temp.put("num", student.getS_num());
                    temp.put("name", student.getS_name());
                    temp.put("sex", student.getS_sex());
                    temp.put("age", student.getS_age());
                    temp.put("college", student.getS_college());
                    temp.put("department", student.getS_department());
                    temp.put("grade", student.getS_grade());
                    temp.put("tel", student.getS_tel());
                    temp.put("dormitory_num", student.getS_dormitory_num());
                    array.put(temp);
                }
            }

            MessageHandler.sendDetailMessage(resp.getWriter(), true, MessageHandler.DATA, array);
        } catch (Exception e) {
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
