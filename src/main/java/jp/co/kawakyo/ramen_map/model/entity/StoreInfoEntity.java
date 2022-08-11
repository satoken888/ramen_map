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
@Table(name="storeinfo", schema = "public")
public class StoreInfoEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="storeid")
    private int storeid;
    @Column(name="日付")
    private Date date;
    @Column(name="状況FLG")
    private int openflg;
    @Column(name="備考")
    private String remarks;
    @Column(name="曜日数字")
    private int dayofweek;
}
