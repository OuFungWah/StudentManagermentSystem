package bean;

/**
 * Created by 区枫华 on 2017/4/14.
 */
public class Student {

    /**
     * 学号
     */
    private String s_num;
    /**
     * 姓名
     */
    private String s_name;
    /**
     * 性别
     */
    private String s_sex;
    /**
     * 年龄
     */
    private int s_age;
    /**
     * 宿舍栋数
     */
    private int s_department;
    /**
     * 院系
     */
    private String s_college;
    /**
     * 年级
     */
    private int s_grade;
    /**
     * 电话
     */
    private String s_tel;
    /**
     * 宿舍号
     */
    private int s_dormitory_num;

    /**
     *
     */
    public Student(){

    }

    /**
     * 构造函数
     * @param s_num 学号
     * @param s_name 姓名
     * @param s_sex 性别
     * @param s_age 年龄
     * @param s_department 宿舍楼数
     * @param s_college 院系
     * @param s_grade 年级
     * @param s_tel 电话
     * @param s_dormitory_num 宿舍号
     */
    public Student(String s_num, String s_name, String s_sex, int s_age, int s_department,String s_college, int s_grade, String s_tel, int s_dormitory_num){
        setS_num(s_num);
        setS_name(s_name);
        setS_sex(s_sex);
        setS_age(s_age);
        setS_department(s_department);
        setS_grade(s_grade);
        setS_tel(s_tel);
        setS_dormitory_num(s_dormitory_num);
        setS_college(s_college);
    }

    public String getS_num() {
        return s_num;
    }

    public void setS_num(String s_num) {
        this.s_num = s_num;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_sex() {
        return s_sex;
    }

    public void setS_sex(String s_sex) {
        this.s_sex = s_sex;
    }

    public int getS_age() {
        return s_age;
    }

    public void setS_age(int s_age) {
        this.s_age = s_age;
    }

    public int getS_department() {
        return s_department;
    }

    public void setS_department(int s_department) {
        this.s_department = s_department;
    }

    public int getS_grade() {
        return s_grade;
    }

    public void setS_grade(int s_grade) {
        this.s_grade = s_grade;
    }

    public String getS_tel() {
        return s_tel;
    }

    public void setS_tel(String s_tel) {
        this.s_tel = s_tel;
    }

    public int getS_dormitory_num() {
        return s_dormitory_num;
    }

    public void setS_dormitory_num(int s_dormitory_num) {
        this.s_dormitory_num = s_dormitory_num;
    }

    public String getS_college() {
        return s_college;
    }

    public void setS_college(String s_college) {
        this.s_college = s_college;
    }
}
