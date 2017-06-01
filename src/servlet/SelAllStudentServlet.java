package servlet;

import bean.MessageHandler;
import bean.Student;
import dao.StudentDao;
import org.json.JSONArray;
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
@WebServlet("/SelAllStudentServlet")
public class SelAllStudentServlet extends HttpServlet {

    /**
     * @see SelAllStudentServlet #HttpServlet()
     */
    public SelAllStudentServlet(){

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
            MessageHandler.sendDetailMessage(resp.getWriter(), true, MessageHandler.DATA, array);
        }catch (Exception e){
//            返回异常细节
            MessageHandler.sendDetailMessage(resp.getWriter(), true, MessageHandler.DETAIL, e.getMessage());
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
