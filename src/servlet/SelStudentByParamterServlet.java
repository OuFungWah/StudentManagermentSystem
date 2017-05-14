package servlet;

import bean.Student;
import dao.StudentDao;
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
 * Created by 区枫华 on 2017/5/14.
 */
@WebServlet("/SelStudentByParamterServlet")
public class SelStudentByParamterServlet extends HttpServlet {

    /**
     * @see SelStudentByParamterServlet #HttpServlet()
     */
    public SelStudentByParamterServlet() {

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
        try {
            JSONObject data = new JSONObject(req.getParameter("data"));
            StudentDao dao = new StudentDao();
            Map<String, Object> param = new HashMap();
            if (param.containsKey("name"))
                param.put("name", data.getString("name"));
            if (param.containsKey("d_c"))
                param.put("d_c", data.getInt("d_c"));
            if (param.containsKey("d_num"))
                param.put("d_num", data.getInt("d_num"));
            List<Student> list = dao.findStudents(param);
            if(list!=null){
                JSONObject respJson = new JSONObject();
                JSONArray array = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    Student student = list.get(i);
                    JSONObject temp = new JSONObject();
                    temp.put("num", student.getS_num());
                    temp.put("name", student.getS_name());
                    temp.put("sex", student.getS_sex());
                    temp.put("age", student.getS_age());
                    temp.put("college", student.getS_college());
                    temp.put("department", "C" + student.getS_department());
                    temp.put("grade", student.getS_grade());
                    temp.put("tel", student.getS_tel());
                    temp.put("dormitory_num", student.getS_dormitory_num());
                    array.put(temp);
                }
                respJson.put("list", array);
                respJson.put("status", "success");
                resp.getWriter().write(respJson.toString());
            }else{
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("status", "fail");
                jsonObject.put("detail", "你都没有给我参数");
            }

        } catch (JSONException e) {
            e.printStackTrace();
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("status", "fail");
                jsonObject.put("detail", e.getMessage());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

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
