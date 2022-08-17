package jp.co.kawakyo.ramen_map.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalesStateTableRecord {
    
    private int storeId;
    private String storeName;
    private String SalesDiv01;
    private String SalesDiv02;
    private String SalesDiv03;
    private String SalesDiv04;
    private String SalesDiv05;
    private String SalesDiv06;
    private String SalesDiv07;
}
