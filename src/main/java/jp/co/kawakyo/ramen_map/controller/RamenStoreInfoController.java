package jp.co.kawakyo.ramen_map.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.kawakyo.ramen_map.model.entity.SalesStateTableRecord;
import jp.co.kawakyo.ramen_map.model.entity.StoreEntity;
import jp.co.kawakyo.ramen_map.model.entity.StoreInfoEntity;
import jp.co.kawakyo.ramen_map.model.service.StoreService;
import lombok.Data;

@Controller
public class RamenStoreInfoController {
    
    @Autowired
    StoreService storeService;

    @GetMapping("/")
    public String index(Model model) {

        //店舗情報を取得
        List<StoreEntity> storeInfolist = storeService.findAll();

        //店舗情報を画面へ渡す
        model.addAttribute("storeInfo", storeInfolist);
        return "index";
    }

    @GetMapping("/store/salesStates")
    public String showSalesStates(Model model) {

        List<Date> searchDateList = new ArrayList<Date>();
        Calendar cal = Calendar.getInstance();
        for(int i = 0; i < 7; i++) {
            searchDateList.add(cal.getTime());
            cal.add(Calendar.DATE, 1);
        }

        //直近1週間の営業情報を取得する
        List<SalesStateTableRecord> tableRecord = storeService.findAllStoreSalesStateThisWeek(searchDateList);

        model.addAttribute("searchDateList", searchDateList);
        model.addAttribute("tableRecord", tableRecord);

        return "salesStates";
    }

    @RequestMapping("/store/update")
    @ResponseBody
    @CrossOrigin
    public StoreInfoEntity updateStoreinfo(@RequestParam("storeId") String storeId,@RequestParam("salesDate") String salesDate,@RequestParam("openFlg") String openFlg ) {
        StoreInfoEntity rtn = null;

        //バリデーションチェック
        if(!StringUtils.isEmpty(storeId) && !StringUtils.isEmpty(salesDate) && !StringUtils.isEmpty(openFlg)) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                //パラメータに合致するレコードを取得する
                StoreInfoEntity entity = storeService.findOneStoreInfo(Integer.valueOf(storeId), format.parse(salesDate));
                //リクエストの内容でエンティティの情報を書き換える
                entity.setOpenflg(Integer.valueOf(openFlg));
                entity.setStoreid(Integer.valueOf(storeId));
                entity.setDate(format.parse(salesDate));
                //更新処理を実施する
                rtn = storeService.saveStoreInfo(entity);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } 
        return rtn;
    }

    @RequestMapping("/store/salesState")
    @ResponseBody
    @CrossOrigin
    public showSalesStateResponse showSalesState(@RequestParam("storeId") String storeId,@RequestParam("salesDate") String salesDate ) {
        showSalesStateResponse rtn = new showSalesStateResponse();

        //バリデーションチェック
        if(!StringUtils.isEmpty(storeId) && !StringUtils.isEmpty(salesDate)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            try {
                StoreInfoEntity res = storeService.findOneStoreInfo(Integer.valueOf(storeId), format.parse(salesDate));
                rtn.setOpenFlg(String.valueOf(res.getOpenflg()));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                rtn.setOpenFlg("error");
            } catch (ParseException e) {
                e.printStackTrace();
                rtn.setOpenFlg("error");
            }
        }
        return rtn;
    }

    @Data
    class showSalesStateResponse {
        private String openFlg;
    }

}
