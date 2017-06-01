package tools;

import bean.DormitoryBean;
import dao.DormitoryDao;

/**
 * Created by 区枫华 on 2017/5/29.
 */
public class InitDormitory {

    public static void main(String args[]) {

        DormitoryDao dao = new DormitoryDao();

        for (int d_c = 1; d_c <= 6; d_c++) {
            for (int d_floor = 1; d_floor <= 6; d_floor++) {
                for (int d_num = 1; d_num <= 32; d_num++) {
                    DormitoryBean bean = new DormitoryBean(d_c, d_floor * 100 + d_num, d_floor, 4, 1500);
                    dao.addDormitory(bean);
                }

            }
        }

    }

}
