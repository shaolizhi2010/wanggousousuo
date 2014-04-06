package com.html.gen.promotion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.html.HtmlPart;
import com.seeker.promotion.vo.Promotion;
import com.utils.C;
import com.utils.U;

public class PromotionToHtml {
    
    public String fromFile(String realpath) {
        String path = realpath;//+C.promotionDir;
       
        try {
            String promotionJson = IOUtils.toString(new FileInputStream(
                    path), "UTF-8");
            
            return fromJson(promotionJson);
            
        } catch (Exception e) {
            //TODO
        }  
        return "";
        
    }
    
    
    public String fromJson(String json) {
        List<Promotion> promotionList = new Gson().fromJson(json, new TypeToken<List<Promotion>>() {}.getType());
       // U.printList(promotionList);
        
        HtmlPart container = new HtmlPart("<div></div>");
        container.addAttr("id", "promotion-container");
        
        for(Promotion p : promotionList) {
            String imgurl = p.getImgList().get(0);
            String href = p.getHref();
            
            HtmlPart div =new HtmlPart("<div  ></div>");//style='width:500px;'
            div.addAttr("class", "promotion-item");
            HtmlPart a = new HtmlPart("<a target='_blank' href='"+href+"'></a>");
            HtmlPart img = new HtmlPart("<img width='400' src='"+imgurl+"'>");
           
            a.append(img);
            div.append(a);
            container.append(div);
            
        }
        return container.html();
    }   
}
