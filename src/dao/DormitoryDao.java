package dao;

import bean.DormitoryBean;
import com.sun.istack.internal.Nullable;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateSessionFactory;

import java.util.List;

/**
 * Created by 区枫华 on 2017/5/9.
 */
public class DormitoryDao implements DormitoryService {

    private final static String hqlDemo = "from DormitoryBean where";

    /**
     * 添加操作
     *
     * @param dormitoryBean
     */
    @Override
    public void addDormitory(DormitoryBean dormitoryBean) {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(dormitoryBean);
        transaction.commit();
        session.close();
    }

    /**
     * 根据Id查找
     *
     * @param num
     * @return
     */
    @Override
    public DormitoryBean findById(int num) {
        Session session = HibernateSessionFactory.getSession();
        DormitoryBean bean = (DormitoryBean) session.get(DormitoryBean.class, num);
        return bean;
    }

    /**
     * 查找全部
     *
     * @return
     */
    @Override
    public List<DormitoryBean> findall() {
        Session session = HibernateSessionFactory.getSession();
        String hql = "from DormitoryBean";
        Query<DormitoryBean> query = session.createQuery(hql, DormitoryBean.class);
        List<DormitoryBean> list = query.getResultList();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    /**
     * 按条件查询符合的宿舍
     *
     * @param d_c
     * @param d_floor
     * @param d_num
     * @return
     */
    @Override
    public List<DormitoryBean> findDormitorys(int d_c, int d_floor, int d_num) {
        DormitoryBean user = null;
        boolean cFlag = false;
        boolean floorFlag = false;
        boolean numFlag = false;
        Session session = HibernateSessionFactory.getSession();

        if ((Integer) d_c != 0) {
            cFlag = true;
        }
        if ((Integer) d_floor != 0) {
            floorFlag = true;
        }
        if ((Integer) d_num != 0) {
            numFlag = true;
        }
        if(!cFlag&&!floorFlag&&!numFlag){
            return findall();
        }else{
            /* 带参数的查询*/
            String hql = hqlDemo;
            if (cFlag) {
                hql += " d_c=:d_c";
            }
            if (floorFlag) {
                if (hql.equals(hqlDemo)) {
                    hql += " d_floor=:d_floor";
                } else {
                    hql += " and d_floor=:d_floor";
                }
            }
            if (numFlag) {
                if (hql.equals(hqlDemo)) {
                    hql += " d_num=:d_num";
                } else {
                    hql += " and d_num=:d_num";
                }
            }
            Query<DormitoryBean> query = session.createQuery(hql, DormitoryBean.class);
            if (cFlag) {
                query.setParameter("d_c", d_c);
            }
            if (floorFlag) {
                query.setParameter("d_floor", d_floor);
            }
            if (numFlag) {
                query.setParameter("d_num", d_num);
            }

            List<DormitoryBean> list = query.getResultList();
            System.out.print("数据条数:" + list.size());
            if (list == null || list.size() == 0) {
                return null;
            }
            session.close();
            return list;
        }
    }

    /**
     * 删除操作
     *
     * @param bean
     */
    @Override
    public void delete(DormitoryBean bean) {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(bean);
        transaction.commit();
    }

    @Override
    public void update(DormitoryBean bean) {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(bean);
        transaction.commit();
    }
}
