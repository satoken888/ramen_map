package jp.co.kawakyo.ramen_map.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.kawakyo.ramen_map.model.entity.StoreEntity;
import jp.co.kawakyo.ramen_map.model.repository.StoreRepository;

@Service
@Transactional
public class StoreService {
    
    @Autowired
    StoreRepository storeRepository;

    public List<StoreEntity> findAll() {
        return storeRepository.findAll();
    }
}
