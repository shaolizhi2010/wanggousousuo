package com.seeker.promotion;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;

import com.connect.Connecter;
import com.file.FileUtil;
import com.google.gson.Gson;
import com.seeker.promotion.vo.Image;
import com.seeker.promotion.vo.Promotion;
import com.utils.L;
import com.utils.Regex;
import com.utils.U;
import com.utils.X;

public class PromotionSeeker {

    /**
     * @param args
     */
    public static void main(String[] args) {

    }

    public static List<Promotion> getPromotions(String mainUrl) {
        List<Element> hrefList = getAllHref(mainUrl);
        List<Promotion> promotionList = new LinkedList<Promotion>();
        // for(Element e :list){
        // System.out.println(e.getAttributeValue("href"));
        // }

        for (Element urlElement : hrefList) {
            String url = urlElement.getAttributeValue("href");
            // if( !StringUtils.isBlank(url) && !url.startsWith("http://")){
            // url = tartUrl +"/" + url;
            // url.replaceAll("///", "/");
            // }

            Promotion p = getPromotion(url, 20000);
            if (p != null && p.getScore() > 0) {
                promotionList.add(p);
                System.out.println("adding promotion:" + p);
            }
            if (promotionList.size() >= 5) {// TODO 暂时5个就结束，节省时间
                break;
            }
        }
        Collections.sort(promotionList);
        U.printList(promotionList);
        return promotionList;

//        new FileUtil()
//                .saveFile(new Gson().toJson(promotionList),"C:/workspace/wanggousousuo/WebContent/promotion/"
//                        + System.currentTimeMillis() + ".json");
    }
    
    
    public static Promotion getPromotion(String url, int minSize) {

        /* 无效或不需要抓取的url */
        if (url.matches(Regex.inValidUrl1) || url.matches(Regex.inValidUrl2)) {
            return null;
        }

        LinkedList<Image> imgList = new LinkedList<Image>();

        Promotion p = new Promotion();

        String responseString = Connecter.connect(url, "");

        if (responseString.length() < 500) {
            return null;
        }

//      String[] matchArray = responseString.split(Regex.dazheRegex);
//      int matchScore = matchArray.length;
//      if (matchScore < 5) { /* 匹配的敏感词个数，如果没有则不是促销，不在继续计算 */
//          L.trace("TestAd", "敏感词个数太少，不是促销页面， url is " + url);
//          return null;
//      }

        try {
            Document doc = new org.jdom2.input.SAXBuilder()
                    .build(new StringReader(responseString));
            List<Element> linkElementlist = X.getSubElementList(doc,
                    "//a[count( ./img ) = 1]");

            boolean imgFinded = false;
            for (Element link : linkElementlist) {
                String href = link.getAttributeValue("href");
                String imgsrc = link.getChild("img").getAttributeValue("src");

                if (StringUtils.isBlank(imgsrc) || !imgsrc.startsWith("http")) {
                    continue;
                }

                int imgSize = 0;

                DataInputStream dis = null;
                BufferedImage buff = null;
                try {
                    URL imgurl = new URL(imgsrc);
                    dis = new DataInputStream(imgurl.openStream());
                    buff = ImageIO.read(dis);
                    if (buff == null) {
                        continue;// io 异常 跳过
                    }

                } catch (Exception e) {
                    continue;// url不合法 跳过
                }

                // System.out.println(buff.getWidth() + " - " +
                imgSize = buff.getWidth() * buff.getHeight();
                if (dis != null) {// 关闭流
                    try {
                        dis.close();
                    } catch (IOException e) {
                        dis = null;
                    }
                }
                
                if(imgSize>minSize){
                    /* 
                     * 再次通过width 和 height 属性取 img size，如果有width 和 height，则以这个为准 
                     * 这样做是为了防止某些图片很大，但在网页上显示的很小，
                     * 这种情况应该按小图片处理
                     * */
                    String imgWidth = link.getChild("img").getAttributeValue(
                            "width");
                    String imgHeight = link.getChild("img").getAttributeValue(
                            "height");

                    try {
                        if (StringUtils.isNotBlank(imgWidth)
                                && StringUtils.isNotBlank(imgHeight)) {
                            imgSize = Integer.parseInt(imgWidth)
                                    * Integer.parseInt(imgHeight);
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }

                if (imgSize > minSize) {

                    // map.put(imgsrc,href );
                    // Promotion p = new Promotion();
                    // p.setHref(href);
                    // p.addImg(imgsrc);
                    Image img = new Image();

                    Integer imgScore = imgSize / 10000;
                    if (imgScore > 20) { /* 图片再大，最多也只能得十分 */
                        imgScore = 20;
                    }
                    img.setScore(imgScore);
                    img.setSrc(imgsrc);
                    img.setSize(imgSize);
                    imgList.add(img);
                    imgFinded = true;
                    
                    if (imgList.size() >= 1)
                        
                        break;// TODO 暂时1个就退 节省时间

                }

            }
            if(imgFinded){  /*找到合适的图片*/
                // Collections.sort(imgList);
                Image maxScoreImage = Collections.max(imgList);
                p.addImg(maxScoreImage.getSrc());
                //p.addScore(matchScore);
                p.addScore(maxScoreImage.score);
                p.setHref(url);
                return p;
            }
            else{
                L.trace("TestAd", "img not finded : url is " + url);
            }
            
        } catch (Exception e) {
            L.exception("", e.getMessage());
        }

        return null;
    }
    
    public static List<Element> getAllHref(String url) {
        List<Element> list = new LinkedList<Element>();

        String responseString = Connecter.connect(url, "");

        Document doc;
        try {
            doc = new org.jdom2.input.SAXBuilder().build(new StringReader(
                    responseString));
            list = X.getSubElementList(doc,
                    "//*[string-length(normalize-space(@href))>10]");

        } catch (Exception e) {
            L.exception("", e.getMessage());
        }

        return list;
    }
    
}
