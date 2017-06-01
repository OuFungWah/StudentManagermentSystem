package dao;

import bean.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateSessionFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by 区枫华 on 2017/4/19.
 */
public class StudentDao implements StudentService {

    private static final String hqlDemo = "from Student where";

    /**
     *
     */
    public StudentDao() {

    }

    @Override
    public Student findById(int id) {
        Session session = HibernateSessionFactory.getSession();
        return session.get(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        Session session = HibernateSessionFactory.getSession();
        String hql = "from Student";
        Query<Student> query = session.createQuery(hql, Student.class);
        List<Student> list = query.getResultList();
        return list;
    }

    /**
     * 查找学生
     *
     * @param map name d_c d_num
     * @return
     */
    @Override
    public List<Student> findStudents(Map<String, Object> map) {
        System.out.println("收到的数据:" + map.toString());
        boolean nameFlag = false;
        boolean numFlag = false;
        boolean cFlag = false;
        boolean d_numFlag = false;

        //判定是否存在以下键
        if (map.containsKey("name")) {
            nameFlag = true;
        }
        if (map.containsKey("num")) {
            numFlag = true;
        }
        if (map.containsKey("c")) {
            cFlag = true;
        }
        if (map.containsKey("d_num")) {
            d_numFlag = true;
        }

        if (!nameFlag && !numFlag && !cFlag && !d_numFlag) {
            return findAll();
        } else {
            Session session = HibernateSessionFactory.getSession();
            String hql = hqlDemo;
            if (nameFlag) {
                hql += " s_name=:s_name";
            }
            if (numFlag) {
                if (hql.equals(hqlDemo)) {
                    hql += " s_num=:s_num";
                } else {
                    hql += " and s_num=:s_num";
                }
            }
            if (cFlag) {
                if (hql.equals(hqlDemo)) {
                    hql += " s_department=:s_department";
                } else {
                    hql += " and s_department=:s_department";
                }
            }
            if (d_numFlag) {
                if (hql.equals(hqlDemo)) {
                    hql += " s_dormitory_num=:s_dormitory_num";
                } else {
                    hql += " and s_dormitory_num=:s_dormitory_num";
                }
            }

            Query<Student> query = session.createQuery(hql, Student.class);
            System.out.println("收到的数据:" + hql);

            if (nameFlag) {
                query.setParameter("s_name", (String) map.get("name"));
            }
            if (numFlag) {
                query.setParameter("s_num", (String) map.get("num"));
            }
            if (cFlag) {
                query.setParameter("s_department", (int) map.get("c"));
            }
            if (d_numFlag) {
                query.setParameter("s_dormitory_num", (int) map.get("d_num"));
            }
            List<Student> list = query.getResultList();
            if (list == null || list.size() == 0) {
                return null;
            }
            return list;
        }

    }


    /**
     * 插入操作
     *
     * @param student
     */
    @Override
    public void add(Student student) {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        System.out.print("Session:" + (session != null) + " Transaction:" + (transaction != null));
        session.save(student);
        transaction.commit();
    }

    /**
     * 删除操作
     *
     * @param student
     */
    @Override
    public void delete(Student student) {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(student);
        transaction.commit();
    }
}
