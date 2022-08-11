package jp.co.kawakyo.ramen_map.model.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.kawakyo.ramen_map.model.entity.StoreEntity;
import jp.co.kawakyo.ramen_map.model.entity.StoreInfoEntity;
import jp.co.kawakyo.ramen_map.model.repository.StoreRepository;
import jp.co.kawakyo.ramen_map.model.repository.StoreInfoRepository;

@Service
@Transactional
public class StoreService {
    
    @Autowired
    StoreRepository storeRepository;

    @Autowired
    StoreInfoRepository storeInfoRepository;

    public List<StoreEntity> findAll() {
        return storeRepository.findAll();
    }

    public StoreInfoEntity saveStoreInfo(StoreInfoEntity entity){
        return storeInfoRepository.save(entity);
    }

    public StoreInfoEntity findOneStoreInfo(int storeId,Date date) {
        List<StoreInfoEntity> res = storeInfoRepository.findByStoreidAndDate(storeId,date);
        return res.get(0);
    }
}
