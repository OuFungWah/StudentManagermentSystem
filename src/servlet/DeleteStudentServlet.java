package servlet;

import bean.DormitoryBean;
import bean.MessageHandler;
import bean.Student;
import dao.DormitoryDao;
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
import java.util.HashMap;
import java.util.Map;

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
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        try {
            JSONObject data = new JSONObject(req.getParameter("data"));
            String s_num = data.getString("num");
            int d_c;
            int d_num;
            Student student = new Student();
            student.setS_num(s_num);
            StudentDao studentDao = new StudentDao();
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("num", s_num);
            Student temp = studentDao.findStudents(map).get(0);

            d_c = temp.getS_department();
            d_num = temp.getS_dormitory_num();
            DormitoryBean bean = new DormitoryBean();
            bean.setD_c(temp.getS_department());
            bean.setD_num(temp.getS_dormitory_num());
            bean = new DormitoryDao().findDormitorys(d_c, d_num / 100, d_num).get(0);
            bean.setD_bed(bean.getD_bed() + 1);
            new DormitoryDao().update(bean);
            studentDao.delete(student);
            MessageHandler.sendDetailMessage(resp.getWriter(), true, MessageHandler.DETAIL, "操作成功");
        } catch (Exception e) {
            MessageHandler.sendDetailMessage(resp.getWriter(), false, MessageHandler.DETAIL, e.getMessage());
        }
    }

    /**
     * @see #doPost(HttpServletRequest, HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
