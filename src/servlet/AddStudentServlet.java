package servlet;

import bean.DormitoryBean;
import bean.MessageHandler;
import bean.Student;
import dao.DormitoryDao;
import dao.StudentDao;
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
 * Created by 区枫华 on 2017/4/27.
 */
@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {

    private int s_num;
    private String s_name;
    private String s_sex;
    private int s_age;
    private int s_department;
    private int s_grade;
    private String s_tel;
    private String s_college;
    private int s_dormitory_num;
    private JSONObject jsonObject;

    /**
     * @see AddStudentServlet #HttpServlet()
     */
    public AddStudentServlet() {

    }

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @see AddStudentServlet#doGet(HttpServletRequest, HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject reponseJsonObject = new JSONObject();
        resp.setContentType("utf-8");
        resp.setContentType("application/json;charset=UTF-8");
        try {
            String requestBody = "";
            requestBody = req.getParameter("data");
            System.out.print(requestBody);
            //参数封装
            if (!requestBody.equals("")) {
                jsonObject = new JSONObject(requestBody);
                s_num = jsonObject.getInt("num");
                s_name = jsonObject.getString("name");
                s_sex = jsonObject.getString("sex");
                s_age = jsonObject.getInt("age");
                s_department = jsonObject.getInt("department");
                s_college = jsonObject.getString("college");
                s_grade = jsonObject.getInt("grade");
                s_tel = jsonObject.getString("tel");
                s_dormitory_num = jsonObject.getInt("dormitory_num");
                Student student = new Student(s_num, s_name, s_sex, s_age, s_department, s_college, s_grade, s_tel, s_dormitory_num);
                StudentDao studentDao = new StudentDao();

                //获取当前即将入住的宿舍
                DormitoryBean bean = findThisDormitory();
                if (bean.getD_bed() >= 4) {
                    //宿舍满人了
                    MessageHandler.sendDetailMessage(resp.getWriter(),false,MessageHandler.DETAIL,"宿舍满人了");
                } else {
                    //宿舍有空位
                    studentDao.add(student);
                    bean.setD_bed(bean.getD_bed() + 1);
                    new DormitoryDao().update(bean);
                    MessageHandler.sendDetailMessage(resp.getWriter(),true,MessageHandler.DETAIL,"操作成功");
                }

            } else {
                //获取数据为空
                MessageHandler.sendDetailMessage(resp.getWriter(),false,MessageHandler.DETAIL,"上传上来的数据为空");
            }

        } catch (Exception e) {
            //抓获异常
            MessageHandler.sendDetailMessage(resp.getWriter(),false,MessageHandler.DETAIL,e.getMessage());
        }
    }

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @see AddStudentServlet#doPost(HttpServletRequest, HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    /**
     * 获取宿舍人数
     *
     * @return
     */
    public DormitoryBean findThisDormitory() {
        DormitoryDao dao = new DormitoryDao();
        List<DormitoryBean> list = dao.findDormitorys(s_department, s_dormitory_num);
        if (list != null) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
