package dao;

import bean.DormitoryBean;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateSessionFactory;

import java.util.List;

/**
 * Created by 区枫华 on 2017/5/9.
 */
public class DormitoryDao implements DormitoryService{

    /**
     *  添加操作
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
     * @param num
     * @return
     */
    @Override
    public DormitoryBean findById(int num) {
        Session session = HibernateSessionFactory.getSession();
        DormitoryBean bean = (DormitoryBean)session.get(DormitoryBean.class,num);
        return bean;
    }

    /**
     * 查找全部
     * @return
     */
    @Override
    public List<DormitoryBean> findall() {
        Session session = HibernateSessionFactory.getSession();
        String hql = "from DormitoryBean";
        Query<DormitoryBean> query = session.createQuery(hql, DormitoryBean.class);
        List<DormitoryBean> list = query.getResultList();
        if(list == null || list.size() == 0){
            return null;
        }
        return list;
    }

    /**
     * 按条件查询符合的宿舍
     * @param d_c
     * @return
     */
    @Override
    public List<DormitoryBean> findDormitorys(int d_c,int d_num) {
        DormitoryBean user = null;
        Session session = HibernateSessionFactory.getSession();
		/* 带参数的查询*/
        String hql = "from DormitoryBean where d_c=:d_c and d_num=:d_num";
        Query<DormitoryBean> query = session.createQuery(hql, DormitoryBean.class);
        query.setParameter("d_c",d_c );
        query.setParameter("d_num",d_num );
        List<DormitoryBean> list = query.getResultList();
        System.out.print("数据条数:"+list.size());
        if(list == null || list.size() == 0){
            return null;
        }
        session.close();
        return list;
    }

    /**
     * 删除操作
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
