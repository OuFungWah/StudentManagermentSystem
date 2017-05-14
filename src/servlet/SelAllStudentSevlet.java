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
import java.util.List;

/**
 * Created by 区枫华 on 2017/5/9.
 */
@WebServlet("/SelAllStudentSevlet")
public class SelAllStudentSevlet extends HttpServlet {

    /**
     * @see SelAllStudentSevlet #HttpServlet()
     */
    public SelAllStudentSevlet(){

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
//        尝试返回数据
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        try{
            JSONObject respJson = new JSONObject();
            JSONArray array = new JSONArray();
            StudentDao dao = new StudentDao();
            List<Student> list = dao.findAll();
            for(int i = 0;i<list.size();i++){
                Student student = list.get(i);
                JSONObject temp = new JSONObject();
                temp.put("num",student.getS_num());
                temp.put("age",student.getS_age());
                temp.put("sex",student.getS_sex());
                temp.put("name",student.getS_name());
                temp.put("tel",student.getS_tel());
                temp.put("grade",student.getS_grade());
                temp.put("college",student.getS_college());
                temp.put("department",student.getS_department());
                temp.put("dormitory_num",student.getS_dormitory_num());
                array.put(temp);
            }
            respJson.put("list",array);
            respJson.put("status","success");
            resp.getWriter().write(respJson.toString());
        }catch (Exception e){
//            返回异常细节
            e.printStackTrace();
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("status","fail");
                jsonObject.put("detail",e.getMessage());
                resp.getWriter().write(jsonObject.toString());
            } catch (JSONException e1) {
                e1.printStackTrace();
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