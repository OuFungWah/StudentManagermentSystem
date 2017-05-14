package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by 区枫华 on 2017/4/17.
 */
public class HibernateSessionFactory {

    private static final String configfile = "/hibernate.cfg.xml";
    /*本地线程*/
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
    private static Configuration configuration = new Configuration();
    private static org.hibernate.SessionFactory sessionFactory;

    /**
     * 读取配置文件，创建一个会话，静态代码在编译后已经运行
     */
    static{
        System.out.println("我是SessionFactory");
        try{
            configuration.configure(configfile);
            sessionFactory = configuration.buildSessionFactory();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取Session
     * @return 返回一个Session
     * @throws HibernateException
     */
    public static Session getSession() throws HibernateException{
        Session session = (Session)threadLocal.get();
        if(session == null||!session.isOpen()){
            if(sessionFactory == null){
                rebuildSessionFactory();
            }
            //如果sessionFactory不为空的时候，用Factory打开一个Session
            session = (sessionFactory != null) ? sessionFactory.openSession():null;
            threadLocal.set(session);

        }
        return session;
    }

    /**
     * 重建SessionFactory
     */
    private static void rebuildSessionFactory() {
        try{
            configuration.configure(configfile);
            sessionFactory = configuration.buildSessionFactory();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 关闭SessionFactory
     * @throws HibernateException
     */
    public static void closeSession() throws HibernateException{
        Session session = (Session)threadLocal.get();
        threadLocal.set(null);
        if(session != null){
            session.close();
        }
    }

}
