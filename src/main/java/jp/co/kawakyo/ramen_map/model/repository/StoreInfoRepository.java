package jp.co.kawakyo.ramen_map.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.kawakyo.ramen_map.model.entity.StoreInfoEntity;

public interface StoreInfoRepository extends JpaRepository<StoreInfoEntity,Integer> {
    List<StoreInfoEntity> findByStoreidAndDate(int storeid,Date date);
    List<StoreInfoEntity> findByDateInOrderByStoreidAscDateAsc(List<Date> dateList);
}
