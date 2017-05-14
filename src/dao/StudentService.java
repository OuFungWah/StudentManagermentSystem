package dao;

import bean.Student;

import java.util.List;
import java.util.Map;

/**
 * Created by 区枫华 on 2017/4/19.
 */
public interface StudentService {

    /**
     * 通过学号查找学生
     * @param id
     * @return
     */
    Student findById(int id);

    /**
     * 查找所有学生
     * @return
     */
    List<Student> findAll();

    /**
     * 查找符合条件的学生
     * @param map
     * @return
     */
    List<Student> findStudents(Map<String,Object> map);

    /**
     * 增添学生
     * @param student
     */
    void add(Student student);

    /**
     * 删除学生
     * @param student
     */
    void delete(Student student);

}
