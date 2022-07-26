package jp.co.kawakyo.ramen_map.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.kawakyo.ramen_map.model.entity.StoreEntity;

public interface StoreRepository extends JpaRepository<StoreEntity,Integer> {
    
}
