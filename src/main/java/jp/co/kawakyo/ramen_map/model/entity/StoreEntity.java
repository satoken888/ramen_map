package jp.co.kawakyo.ramen_map.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="store", schema = "public")
public class StoreEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="店名")
    private String storeName;
    @Column(name="住所")
    private String address;
    @Column(name="電話番号")
    private String tel;
    @Column(name="定休曜日")
    private String holiday;
    @Column(name="定休曜日数字")
    private int holidayNum;
    @Column(name="営業時間")
    private String businessHour;
    @Column(name="最終更新日")
    private Date updateDate;
}
