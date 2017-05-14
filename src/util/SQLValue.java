package util;

/**
 * Created by 区枫华 on 2017/4/14.
 */
public class SQLValue {

    /**
     * 宿舍创建表
     */
    public static String CREATE_DORMITORY_TABLE = "CREATE TABLE 'dormitory'('d_c' int,'d_num' int,'d_floor' int,'d_bed' int DEFAULT 4,'d_price' int NOT NULL,PRIMARY KEY('d_num'));";

    /**
     *学生创建表
     */
    public static String CREATE_STUDENT_TABLE = "CREATE TABLE 'student'('s_num' int,'s_name' varchar(64) NOT NULL,'s_sex' varchar(16),'s_age' int,'s_department' varchar(64),'s_grade' int,'s_tel' varchar(32),'s_dormitory_num' int,PRIMARY KEY('s_num'));";

}
