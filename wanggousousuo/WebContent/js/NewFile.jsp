<%@ page language="java" pageEncoding="UTF-8"%>.

<script>
var C_productsPerPage = 40;//const :product max count per page
var currentItemIndex = 1;
var needShowedItemCount = 0;
var needShowedPageCount = 1;


var allProduct = [];
var viewedProducts = [];
var allshop = [];	//'äºé©¬éä¸­å½','æ·å®ç½','å¤©ç«åå','å½å½ç½','äº¬ä¸åå'
var shop_condition='å¨é¨';
var pageIndex ='1';
var price_between_switch = 'off';
var price_min = 0;	//price filter condition
var price_max = 0;	//price filter condition


function searchAll(keywordVar) {
	
	//show loading bar when begin search
	 $("#loading").show();
	
	//re initialize
	 setPageIndex(1);
	 allshop = [];	//'äºé©¬éä¸­å½','æ·å®ç½','å¤©ç«åå','å½å½ç½','äº¬ä¸åå'
	 allProduct = []; 
	
	$("#keyword-info span").text("ç¸å³æç´¢  " + keywordVar);
	$("div#search_result").empty();	//clear page
	//$('#search_result_summary span').text('0');
	
	//initialize end
	
	//var urlSouSou = '';//'http://bijia365.net:8008/wanggousousuo/';//'http://wgsousou.gotoip3.com/';
	var urlSouSou = 'http://bijia365.net:8008/wanggousousuo/';//'http://wgsousou.gotoip3.com/';
	
	searchOneShop('taobao',keywordVar ,1,'');
	searchOneShop('tmall',keywordVar,1,'');
	searchOneShop('amazon',keywordVar,1,'');
	searchOneShop('dangdang',keywordVar,1,'');
	searchOneShop('jingdong',keywordVar,1,'');
	
	searchOneShop('wl',keywordVar,1,urlSouSou);
	searchOneShop('yixun',keywordVar,1,urlSouSou);
	searchOneShop('fanke',keywordVar,1,urlSouSou);
	searchOneShop('okbuy',keywordVar,1,urlSouSou);
	searchOneShop('newegg',keywordVar,1,urlSouSou);
	searchOneShop('handuyishe',keywordVar,1,urlSouSou);
	searchOneShop('coo8',keywordVar,1,urlSouSou);
	searchOneShop('qinqinbaby',keywordVar,1,urlSouSou);
	searchOneShop('yihaodian',keywordVar,1,urlSouSou);
	searchOneShop('m18',keywordVar,1,urlSouSou);
	searchOneShop('jumei',keywordVar,1,urlSouSou);
	//searchOneShop('vjia',keywordVar,1,urlSouSou);
	//searchOneShop('lefeng',keywordVar,1,urlSouSou);
	//searchOneShop('gome',keywordVar,1,urlSouSou);
	searchOneShop('mbaobao',keywordVar,1,urlSouSou);
	searchOneShop('yintai',keywordVar,1,urlSouSou);
	searchOneShop('outlets',keywordVar,1,urlSouSou);
}//end search all

function searchOneShop(shopnameVar,keywordVar, pagenum,url) {
	if(url == undefined){
		url = '';
	}
	$.ajax({
		type:'get',
		 url:url+'searchBean',	
		 dataType:'jsonp',
		 jsonp:"callback",
		 data:{"shopname":shopnameVar, "keyword":keywordVar, "pagenum":pagenum },
		 async: true,
		 success:function(data){
			 
				showProducts(data);
				
				//init shop conditions
				for (var i in data) 
				{ 
					 if( !arrayContain(allshop, data[i].source )){
						 allshop.push( data[i].source );
						 $cloneShopCondition = $('.shop-condition:eq(0)').clone(true);
						 $cloneShopCondition.children('a').children('span').text(data[i].source );
						 
						 $("#condition-shops ul").append( $cloneShopCondition);//condition-shops
						// alert(allshop.join());
					 }
				}

				allProduct = $.merge(allProduct, data);
				if(shopnameVar=='tmall' || shopnameVar=='jingdong'){//å¤©ç«åäº¬ä¸å è½½å® æåºä¸æ¬¡ï¼æèèç½ç»ææ¿æ¢æ
					$("#sort-comment-321").trigger('click'); 
				}
				
				 $("#loading").hide();
		 }
	 }) ;

} //end search one shop
	
function showProducts(products) { 
 
	$template = $("div#search_item_template>div").clone(true);
	
	for ( var p in products) {
		
		//filter condition æäºitemæ éæ¾ç¤º
		if(shop_condition != 'å¨é¨'){	//shop condition filter
			if(shop_condition != products[p].source){//not match codition, not show
				continue;
			}
		}
		if(price_between_switch=='on'){	//price condition filter
			
			if(price_min != 0){
				if( products[p].price < price_min  ){
					continue;
				}
			}
			if(price_max != 0){
				if( products[p].price > price_max){
					continue;
				}
			}
		}
		
		
		currentItemIndex++;
		//ç¿»é¡µ æäºitemæä¸æ¾ç¤º
		var startItemIndex  = (pageIndex-1) *C_productsPerPage;
		var endItemIndex  = (pageIndex) *C_productsPerPage;
		
		if( currentItemIndex <= startItemIndex || currentItemIndex > endItemIndex){
			continue;	
		}
		
		$newItem = $template.clone(true);
		
		setProduct($newItem, products[p]);
		
		$newItem.find("input").val(JSON.stringify(products[p]));
		
		//show on page
		$("#search_result").append($newItem);
		//if(currentItemIndex%4 == 0){
		//	$("#search_result").append('<div class="clear-fix-hidden"></div>');
		//}

	}//end for
	
}

function setProduct(item, p){
	item.find("div[class='item_pic'] a img").attr('src',
			p.imgUrl);

	item.find("div[class='item_pic'] a").attr('href',
			p.url);
	 
	item.find("div[class='item_title'] a").text(p.name);
	item.find("div[class='item_title'] a").attr('href',
			p.url);

	if (p.price.length == 0) {
		item.find("div[class='item_price'] span ").text("ææ ");
	} else if (p.price.length > 15) {
		item.find("div[class='item_price'] span").hide();
		item.find("div[class='item_price'] img").attr('src',
				p.price);
	} else {
		item.find("div[class='item_price'] img").hide();
		item.find("div[class='item_price'] span").text(
				p.price);
	}
	
	if(p.commentCount == 0){
		item.find("div[class='item_comment'] a span ").text("ææ ");
	}
	else{
		item.find("div[class='item_comment'] a span ").text(p.commentCount);
	}
	
	if(p.commentUrl == '' || p.commentUrl == '#'){
		item.find("div[class='item_comment'] a").attr('href',
				p.url);
	}
	else{
		item.find("div[class='item_comment'] a").attr('href',
				p.commentUrl);
	}
	
	item.find("div[class='item_source'] a").attr('href',
			p.url);
	item.find("div[class='item_source'] a").text(  p.source );
}
	
function setPageIndex(index){
	pageIndex = index;
	currentItemIndex = 0;
	$(".pageindex").attr("style", "color:#055D90");
	$(".pageindex").eq(index-1).attr("style", "color:red");
	$("div#search_result").empty();
	
}

//--- tools --------------------------------------------------------------

function sortJSON(data, key, way) {
	return data.sort(function(a, b) {
		var x = parseFloat(a[key]);
		var y = parseFloat(b[key]);
		
		if (way === '123') {
			if(x=='x')return 0;//x unkown value
			if(y=='x')return 1;//x unkown value
			if(x>y) return 1;
			if(x==y) return 0;
			if(x<y) return -1;
		}
		if (way === '321') {
			if(x=='x')return 1;//x unkown value
			if(y=='x')return 0;//x unkown value
			if(x<y) return 1;
			if(x==y) return 0;
			if(x>y) return -1;
		}
	});
}

function arrayContain(array, element){
	for(var a in array){
		
		if(array[a] == element){
			
			//alert(array[a]);
			return true;
		}
	}
	return false;
}

function setCookie(c_name,value,expiredays)
{
	var exdate=new Date();
	exdate.setDate(exdate.getDate()+expiredays);
	//alert(exdate.toGMTString() );
	document.cookie=c_name+ "=" +escape(value) + ((expiredays==null) ? "" : ";expires="+exdate.toGMTString());
}

function getCookie(c_name){
	if (document.cookie.length>0){
		c_start=document.cookie.indexOf(c_name + "=");
		if (c_start!=-1){
			c_start=c_start + c_name.length+1;
			c_end=document.cookie.indexOf(";",c_start);
			if (c_end==-1) c_end=document.cookie.length;
			return unescape(document.cookie.substring(c_start,c_end));
		}
	}
	return "";
}

</script>