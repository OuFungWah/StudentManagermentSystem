package dao;

import bean.DormitoryBean;

import java.util.List;

/**
 * Created by 区枫华 on 2017/5/9.
 */
public interface DormitoryService {

    void addDormitory(DormitoryBean dormitoryBean);

    DormitoryBean findById(int num);

    List<DormitoryBean> findall();

    List<DormitoryBean> findDormitorys(int d_c,int d_num);

    void delete(DormitoryBean bean);

    void update(DormitoryBean bean);

}
