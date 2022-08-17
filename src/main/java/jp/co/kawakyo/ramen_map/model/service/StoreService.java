package jp.co.kawakyo.ramen_map.model.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.kawakyo.ramen_map.model.entity.SalesStateTableRecord;
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

    public List<SalesStateTableRecord> findAllStoreSalesStateThisWeek(List<Date> dateList){
        List<SalesStateTableRecord> rtn = new ArrayList<SalesStateTableRecord>();
        List<StoreEntity> storeList = storeRepository.findAll();
        List<StoreInfoEntity> storeInfoList = storeInfoRepository.findByDateInOrderByStoreidAscDateAsc(dateList);
        //TODO:storeInfoテーブルに店舗名が無いため、どこから持ってくるか。
        //resからTable用の表記にするためにどのようにアルゴリズムを作るか要考察
        for(StoreInfoEntity storeInfoEntity : storeInfoList) {
            boolean exists = false;
            for(SalesStateTableRecord salesStateTableRecord : rtn) {
                //テーブルレコードリストの中に店舗IDが含まれるか調査
                //含まれてたら、営業状況フラグを格納
                //含まれてなかったら、Recordを作成
                if(salesStateTableRecord.getStoreId() == storeInfoEntity.getStoreid()) {
                    //IDが同じものがある場合
                    exists = true;

                    if(StringUtils.isEmpty(salesStateTableRecord.getSalesDiv01())) {
                        salesStateTableRecord.setSalesDiv01(String.valueOf(storeInfoEntity.getOpenflg()));
                    } else if (StringUtils.isEmpty(salesStateTableRecord.getSalesDiv02())) {
                        salesStateTableRecord.setSalesDiv02(String.valueOf(storeInfoEntity.getOpenflg()));
                    } else if (StringUtils.isEmpty(salesStateTableRecord.getSalesDiv03())) {
                        salesStateTableRecord.setSalesDiv03(String.valueOf(storeInfoEntity.getOpenflg()));
                    } else if (StringUtils.isEmpty(salesStateTableRecord.getSalesDiv04())) {
                        salesStateTableRecord.setSalesDiv04(String.valueOf(storeInfoEntity.getOpenflg()));
                    } else if (StringUtils.isEmpty(salesStateTableRecord.getSalesDiv05())) {
                        salesStateTableRecord.setSalesDiv05(String.valueOf(storeInfoEntity.getOpenflg()));
                    } else if (StringUtils.isEmpty(salesStateTableRecord.getSalesDiv06())) {
                        salesStateTableRecord.setSalesDiv06(String.valueOf(storeInfoEntity.getOpenflg()));
                    } else if (StringUtils.isEmpty(salesStateTableRecord.getSalesDiv07())) {
                        salesStateTableRecord.setSalesDiv07(String.valueOf(storeInfoEntity.getOpenflg()));
                    } 

                    break;

                }
            }
            if(!exists) {
                    String storeName = storeList.stream().filter(e -> e.getId() == storeInfoEntity.getStoreid()).collect(Collectors.toList()).get(0).getStoreName();
                    rtn.add(new SalesStateTableRecord(storeInfoEntity.getStoreid(),storeName,String.valueOf(storeInfoEntity.getOpenflg()),"","","","","",""));
            }
        }
        return rtn;
    }
}
