package bean;

import java.io.Serializable;

/**
 * Created by 区枫华 on 2017/4/14.
 */
public class DormitoryBean implements Serializable {

    /**
     *第几栋宿舍楼
     */
    private int d_c;
    /**
     * 宿舍号
     */
    private int d_num;
    /**
     * 楼层
     */
    private int d_floor;
    /**
     * 空余床位
     */
    private int d_bed;
    /**
     * 单价
     */
    private int d_price;

    /**
     * 构造函数
     * @param d_c 宿舍楼号
     * @param d_num 宿舍编号
     * @param d_floor 宿舍楼层
     * @param d_bed 空余床位
     * @param d_price 单价
     */
    public DormitoryBean(int d_c,int d_num,int d_floor,int d_bed,int d_price){
        setD_c(d_c);
        setD_num(d_num);
        setD_floor(d_floor);
        setD_bed(d_bed);
        setD_price(d_price);
    }

    public DormitoryBean() {
    }

    public int getD_num() {
        return d_num;
    }

    public void setD_num(int d_num) {
        this.d_num = d_num;
    }

    public int getD_floor() {
        return d_floor;
    }

    public void setD_floor(int d_floor) {
        this.d_floor = d_floor;
    }

    public int getD_bed() {
        return d_bed;
    }

    public void setD_bed(int d_bed) {
        this.d_bed = d_bed;
    }

    public int getD_price() {
        return d_price;
    }

    public void setD_price(int d_price) {
        this.d_price = d_price;
    }

    public int getD_c() {
        return d_c;
    }

    public void setD_c(int d_c) {
        this.d_c = d_c;
    }

    @Override
    public boolean equals(Object obj) {
        DormitoryBean temp = (DormitoryBean)obj;
        if(temp==null){
            return false;
        }
        if(obj.getClass()!=DormitoryBean.class){
            return false;
        }
        if(temp.d_c!=this.d_c){
            return false;
        }
        if(temp.d_num!=this.d_num){
            return false;
        }
        if(temp.d_bed!=this.d_bed){
            return false;
        }
        if(temp.d_floor!=this.d_floor){
            return false;
        }
        if(temp.d_price!=this.d_price){
            return false;
        }
        return true;
    }

    /**
     * 哈希存储时的计算方式
     * @return
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + d_c;
        result = prime * result + d_num;
        return result;
    }

    public static double Show(int a){
        return a*3.14;
    }
}
