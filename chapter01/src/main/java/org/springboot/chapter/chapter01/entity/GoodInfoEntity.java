package org.springboot.chapter.chapter01.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 商品信息表
 */
@Entity
@Table(name = "basic_good_info")
@Data
public class GoodInfoEntity {
    /**
     * 商品编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bgi_id")
    private Long id;
    /**
     * 商品名称
     */
    @Column(name = "bgi_name")
    private String name;
    /**
     * 商品单位
     */
    @Column(name = "bgi_unit")
    private String unit;
    /**
     * 商品单价
     */
    @Column(name = "bgi_price")
    private BigDecimal price;
}
