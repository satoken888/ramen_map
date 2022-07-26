package jp.co.kawakyo.ramen_map.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.kawakyo.ramen_map.model.entity.StoreEntity;
import jp.co.kawakyo.ramen_map.model.service.StoreService;

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

}
