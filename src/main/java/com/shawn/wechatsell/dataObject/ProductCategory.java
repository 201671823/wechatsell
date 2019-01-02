package com.shawn.wechatsell.dataObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author: zxx
 * @Date: 2018/12/7 16:05
 * @Version 1.0
 */

/**
 * 将数据映射成对象
 */
@Entity //声明实体
@DynamicUpdate //声明动态更新，对默认值进行动态刷新，比如updateTime
@Data //自动添加get set 和 to'String方法
public class ProductCategory {

    /** 类目id name type
     * 注意mysql中自增的量要添加 @GeneratedValue(strategy = GenerationType.IDENTITY)
     * createTime
     * updateTime
     */
    @Id //声明主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //声明自动增长键
    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
    // 声明无参构造方法，如果没有自定义构造方法则不需要
    public ProductCategory(){

    }

    public ProductCategory(String categoryName, int categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
