package test;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.html.DoubleTag;
import com.html.HtmlPart;
import com.html.HtmlUtil;
import com.html.Tag;
import com.html.gen.promotion.PromotionToHtml;
import com.seeker.promotion.vo.Promotion;
import com.utils.Regex;
import com.utils.U;

public class TestMain {

    /**
     * @param args
     */
    public static void main(String[] args) {

        try {
            
            PromotionToHtml pth = new PromotionToHtml();
            String html = pth.fromFile("C:\\workspace\\wanggousousuo\\WebContent\\promotion\\1381477405820.json");
            System.out.println(html);
//            // Tag div = new DoubleTag("div") ;
//            HtmlSegment s = new HtmlSegment("<div></div>");
//            s.addAttr("id", "test");
//            s.addAttr("class", "test");
//
//            for (int i = 0; i < 2; i++) {
//
//                HtmlSegment a = new HtmlSegment(
//                        "<a title='' href='http://www.baidu.com'></a>");
//
//                HtmlSegment img = new HtmlSegment(
//                        "<img class='' title='' src='' alt=''>");
//                
//                a.append(img);
//                s.append(a);
//            }
//            System.out.println(s.html());

            //
            // String s = "<div class=''>abc</div>";
            // //System.out.println(s.matches(HtmlUtil.singleTagFormat));
            // System.out.println(s.matches(HtmlUtil.doubleTagFormat));
            //
            // HashMap<String, String> map = new HashMap<String, String>();
            // map.put("abc#29%", "1");
            // map.put("abc#EUR30", "2");
            //
            // System.out.println(map.get("abc#29%"));

            // System.out.println(U.curDate());

            // String str = "打折信息，满就送100";
            //
            //
            //
            // String[] arr = str.split(Regex.dazheRegex);
            //
            // System.out.println(arr.length);

            //
            // Promotion p1 = new Promotion();
            // Promotion p2 = new Promotion();
            //
            // p1.setScore(3.1);
            // p2.setScore(2.1);
            //
            //
            // LinkedList<Promotion> list = new LinkedList<Promotion>();
            // list.add(p1);list.add(p2);
            //
            // Collections.sort(list);
            //
            // U.printList(list);

            // String s = "handuyishe.com/product/3967.htm";
            // System.out.println( DiggerUtil.containsDomain(s));

            // PriceElement p1 = new PriceElement();
            // p1.setPrice(1d);
            //
            // PriceElement p2 = new PriceElement();
            // p2.setPrice(6d);
            //
            // PriceElement p3 = new PriceElement();
            // p3.setPrice(3d);
            //
            // List<PriceElement> plist= new LinkedList<PriceElement>();
            // plist.add(p1);
            // plist.add(p2);
            // plist.add(p3);
            //
            // Collections.sort(plist,new PriceElementComparator());
            //
            // Collections.reverse(plist);
            //
            // U.printList(plist);
            //

            // String s = "";
            // System.out.println(new BigDecimal(s).intValue());

            // Env.basePath =
            // "C:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp5/wtpwebapps/wanggousousuo";
            // String cachePath = Env.basePath + File.separator + C.cachePath;
            // File cpfile = new File(cachePath);
            // System.out.println(cpfile.listFiles().length);
            // cpfile.renameTo(new File(Env.basePath + File.separator +
            // "cache-"+UUID.randomUUID()));
            // new File(cachePath).mkdir();
            // WebClient client = new WebClient(BrowserVersion.FIREFOX_17);
            // HtmlPage page = client.getPage("http://www.amazon.cn/ref=z_cn");
            // HtmlForm form = page.getFormByName("site-search");
            // HtmlTextInput keyword = form.getInputByName("field-keywords");
            // keyword.setValueAttribute("iphone5");
            // HtmlSubmitInput btn = (HtmlSubmitInput) form.getByXPath(
            // "//input[@class='nav-submit-input']").get(0);
            // HtmlPage resultPage = btn.click();
            // System.out.println(resultPage.asText());

            // Digger digger = new JingdongDigger("买菜车",1);
            //
            // Map productMap = (HashMap) digger.getCookedProductMap();
            // new UrlBuilder().buildUrlsForPage(C.jingdong, productMap);
            // System.out.println( new
            // Gson().toJson(productMap.get("productList") ));
            // HtmlCleaner hc = new HtmlCleaner();
            // TagNode node = hc.clean(new
            // URL("http://s.vancl.com/search?k=%E5%A5%BD%E8%A1%A3%E5%BA%9C&orig=3"),"UTF-8");//encoding
            // used for parse returned content
            // CleanerProperties props = hc.getProperties();
            // JDomSerializer jdomSerializer = new JDomSerializer(props, true);
            // Document doc = jdomSerializer.createJDom(node);
            //
            // new TestMain().printXml(doc);

            // System.out.println(URLDecoder.decode("http://www.amazon.cn/mn/search/ajax/ref=nb_sb_noss?field-keywords=akg+990&rh=i%3Aaps%2Ck%3Aakg+990&fromHash=%2Fref%3Dnb_sb_noss_1%3F__mk_zh_CN%3D%25E4%25BA%259A%25E9%25A9%25AC%25E9%2580%258A%25E7%25BD%2591%25E7%25AB%2599%26url%3Dsearch-alias%253Daps%26field-keywords%3Dnike%26rh%3Di%253Aaps%252Ck%253Anike&section=ATF,BTF&fromApp=gp%2Fsearch&fromPage=results&version=2"));

            // List list = XPath.selectNodes(doc, "//dd/a");
            // //
            // Map<String, String> kuaidiMap = new HashMap<String, String>();
            // for (Object o : list) {
            //
            // Element xml = (Element) o;
            //
            // System.out.println(xml.getValue());
            // System.out.println( xml.getAttributeValue("data-code") );
            // kuaidiMap.put( xml.getAttributeValue("data-code")
            // ,xml.getValue());
            // }
            //
            // System.out.println(new Gson().toJson(kuaidiMap));

            // String flag = P.getProperties().getProperty("LogLevel");
            // System.out.println(LogLevel.exception);

            // String sText = "1.0";
            // System.out.println(sText.matches("[0-9]{1,6}(.0)?"));
            // System.out.println( sText.substring(String.));

            // BigInteger b = new
            // BigInteger(MessageDigest.getInstance("md5").digest("http://abc.com/xcz".getBytes()));
            // System.out.println( b.toString());
            // List<String> list = new
            // KeywordService().getKeywordListFromCache("C:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp5/wtpwebapps/wanggousousuo/");
            // List<String> list = new KeywordService().getKeywordListFromDB();
            //
            // String keyword = "a b /c";
            // String[] keywords = KeywordService.strToKeywords(keyword);
            // keyword= KeywordService.clearKeyword(keyword);
            // U.printArray(keywords);
            // System.out.println(keyword);
            // List<FileSummarize> list = new
            // FileUtil().getFilesAndDirs("C:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp5/wtpwebapps/wanggousousuo/blog/",
            // "",FileUtil.All, true);
            // for(FileSummarize fs : list){
            // System.out.println(fs.getName());
            // System.out.println(fs.getForder());
            // }
            // System.out.println(list.size());

            // new BlogService().digBlog();
            // System.out.println(new
            // KeywordService().getKeywordListFromCache("C:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp5/wtpwebapps/wanggousousuo/").size());
            // System.out.println(new Cache().get("三件套", "coo8",
            // "C:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp5/wtpwebapps/wanggousousuo/",
            // true));
            // System.out.println(new
            // BlogService().getBlogList("C:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp5/wtpwebapps/wanggousousuo/"));

            // String dd = "1.0";
            // System.out.println((int)Double.parseDouble(dd.replaceAll(",","")));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("end...");

    }

}
