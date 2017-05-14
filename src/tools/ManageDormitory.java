package tools;

import bean.DormitoryBean;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by 区枫华 on 2017/4/15.
 */
public class ManageDormitory {

    /** SessionFactory 引用*/
    private static SessionFactory factory;

    /**
     * 新建 SessionFactory 的对象
     */
    public ManageDormitory(){
        factory = new Configuration().configure().buildSessionFactory();
    }

    /**
     * 插入宿舍数据库
     * @param d_c 宿舍楼号
     * @param d_num 宿舍号
     * @param d_floor 宿舍楼层
     * @param d_bed 空余床位
     * @param d_price 宿舍单价
     * @return 返回操作是否成功
     */
    public boolean addOrUpdateDormitory(int d_c,int d_num,int d_floor,int d_bed,int d_price){

        boolean flag = false;
        //创建会话
        Session session = factory.openSession();
        //交易（一会话对多交易）
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            DormitoryBean bean = new DormitoryBean(d_c,d_num,d_floor,d_bed,d_price);
            session.saveOrUpdate(bean);
            tx.commit();
            flag = true;
        }catch (HibernateException e){
            if(tx!=null){
                //回滚事件
                tx.rollback();
            }
            flag = false;
            e.printStackTrace();
        }finally {
            session.close();
        }
        return flag;
    }

}
