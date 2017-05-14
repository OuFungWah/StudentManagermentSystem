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
public class StudentDao implements StudentService{

    /**
     *
     */
    public StudentDao(){

    }

    @Override
    public Student findById(int id) {
        Session session = HibernateSessionFactory.getSession();
        return session.get(Student.class,id);
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
     * @param map name d_c d_num
     * @return
     */
    @Override
    public List<Student> findStudents(Map<String,Object> map) {
        boolean flag1 = false;
        boolean flag2 = false;

        //判定是否存在以下键
        if(map.containsKey("name")){
            flag1 = true;
        }
        if(map.containsKey("d_num")&&map.containsKey("d_c")){
            flag2 = true;
        }
        Session session = HibernateSessionFactory.getSession();
        String hql = "from Student";
        if(flag1){
             hql+=" where s_name=:s_name";
            if(flag2){
                hql+=" and s_department=:s_department and s_dormitory_num=:s_dormitory_num";
            }
        }else{
            if(flag2){
                hql+=" where s_department=:s_department and s_dormitory_num=:s_dormitory_num";
            }
        }

        Query<Student> query = session.createQuery(hql,Student.class);

        if(flag1){
            query.setParameter("s_name",(String)map.get("name"));
        }
        if(flag2){
            query.setParameter("s_department",(int)map.get("d_c"));
            query.setParameter("s_dormitory_num",(int)map.get("d_num"));
        }
        List<Student> list = query.getResultList();
        if(list==null){
            return null;
        }
        return list;
    }



    /**
     * 插入操作
     * @param student
     */
    @Override
    public void add(Student student) {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        System.out.print("Session:"+(session!=null)+" Transaction:"+(transaction!=null));
        session.save(student);
        transaction.commit();
    }

    /**
     * 删除操作
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
