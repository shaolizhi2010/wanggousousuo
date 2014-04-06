<%@ page language="java" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
<jsp:include page="part/head-meta-blog.jsp" />

<link rel="stylesheet" href="css/common.css" type="text/css"/>
<style type="text/css">

.about-main{
	width: 50%; 
	height:3000px;
	margin-top:100px;
	margin-left:auto; margin-right:auto;
	border: 3px dotted #E6E6E6;    
	line-height: 20px; 
	
}

.message {
	font-size:12px;
	font-family:宋体;
	padding: 20px;
	float: left;
}
.message span{
	display:block;
	font-size:12px;
	font-family:宋体;
	color:#878787;
	clear:both;
}
 
 

</style>
<script src="js/jquery-1.9.1.js"></script>
<script>

$( document ).ready(function() {
	
	
	$("#submit-search-btn").on("click", function() {
		if($("#keyword").val().length>0){
			window.location.href='search.jsp?keyword='+$("#keyword").val(); // 跳转
		}
		else{
			window.location.href='search.jsp?keyword=新款'; // 跳转
		}
		$("#keyword").focus();

	}); //end  on

	$("#keyword").keydown(
			function(event) {
				if (event.keyCode == 13) {
					window.location.href = 'search.jsp?keyword='
							+ $("#keyword").val(); // 跳转
				}

	});
	
}); //end document ready


</script>
</head>

<body>
<jsp:include page="part/hat.jsp" />
<jsp:include page="part/header.jsp" />

<div class="about-main">

	<div class="message">
 


 


<pre>

我的网购10年


我的网购10年（一） -  易趣 VS 淘宝
作者：游   （源自：<a target="_blank" href="http://www.wanggousousuo.com/blog.jsp">网购搜索网</a>）
 
 
    大概是在2003年的时候，我还在读大二，有一天，隔壁的一个同学，推门进我们寝室，
手里拿着一瓶洗发香波，跟我说， 我在一个网站上注册了个帐号，就送了我一瓶洗发香波。
当时觉得很震撼，于是我也跑到上边去注册了个帐号，那个网站叫易趣。
 
    易趣，估计好多人都没有听说过，或早就淡忘了。易趣是美国最早也是最大的C2C网站，
也是最早进入中国的，他的美国名字，叫ebay。
 
    只可惜，当时送洗发香波的活动已经结束，但这勾起了我的兴趣，我又在网上一顿狂搜，
找到了一个同类的网站，淘宝，这是我第一次知道淘宝。
 
   然后我就毫不犹豫的选择了淘宝。易趣的界面，多多少少让人感觉不爽，用官方一点的话说，
就是不符合中国用户的习惯，而淘宝则不同，淘宝让人感觉亲切，操作起来也顺手。当时的网购群体，
大部分是图新鲜的，抱着好奇和好玩的心态，而淘宝的风格，无疑更能迎合用户。
 
    再然后，我就开始了我的网购之路，逛淘宝，开网银，试着买东西，又试着开店，
卖过电子书，光盘，MP3,盗版书，还卖过点卡，每天忙的不亦乐乎。
 
    而易趣，却被我渐渐淡忘了。






我的网购十年（二） -  卓越、当当、china-pub
作者：游   （源自：<a target="_blank" href="http://www.wanggousousuo.com/blog.jsp">网购搜索网</a>）


    不记得从什么时候开始，卓越和当当已经成为了图书网购市场的双星。
然而好景不长，一星就陨落了（其实人家是高升，只是升的不明显）。感觉卓越离我越来越远，
而当当，越来越近。

  2006年底，是我在网上买书最疯狂的时候，可能是因为开始工作了，手里有了点工资，
书是一摞一摞的买，一百一百的买。

    当时在网上买书的过程大概是这样的，先上china-pub，找书、看书评，然后上当当网下单买，
过个两三天，快递就上门了，交钱，收书。

    有时候我都觉的有点对不住china-pub，不知道别人是怎么买的。

    后来才知道，卓越被人7500万美金收购了，然后改名叫卓越亚马逊。收购他的公司，叫亚马逊，
 美国的在线图书零售巨头，和ebay是同一重量级的。

    据我观察，这种巨型跨国公司，到中国后都会面临一个问题，确切点说是一道沟，
中西文化之间的巨大鸿沟。

    无数公司都曾摔进这道沟里，包括ebay（就是我们前边说的易趣），雅虎，还有google，有的到现在都还没爬上来，
卓越亚马逊似乎也摔了一下，不过还好现在爬上来了。






我的网购十年（三）- 京东商城 VS 淘宝
作者：游   （源自：<a target="_blank" href="http://www.wanggousousuo.com/blog.jsp">网购搜索网</a>）


    2006年，北京，我和同事一起租房住。一天快递送来了一台液晶显示器，
同事告诉我，网上买的，叫京东商城，货到付款。

    这在现在看来再平常不过，不过在当时，这再一次刷新了我对网购的认识。显示器也能上网买？
而且不开包装，1000多直接给钱，, 没有担保。

    就此记住了京东。

    后来在淘宝买东西的时候，偶尔也会看一眼京东，当然还是淘宝为主，直到有一天。

    我在淘宝买了一款手机，行货，两千多， 结果发现是台翻新机。。。

    2000多的翻新机，民工的我怎堪承受。

    和店家交涉，未果。然后走官方程序，提申请，买卖双方辩论，出示照片证据，小二介入，全额退款。

    拿到钱的时候，感觉刚打完了一场官司，累。  想给个差评出出气，也好提醒下后来人，结果发现不能给差评。。。
肇事店铺毫发无损。

    我幼小的心灵受到了震撼。 淘宝已经不纯真了，之后暂离淘宝，转投京东。

    其实后来我也在淘宝买东西，当听说淘宝出新规对店铺加强监管，加大惩罚力度的时候，
我由衷支持。






我的网购十年（四） - 一手交钱，一手交货
作者：游   （源自：<a target="_blank" href="http://www.wanggousousuo.com/blog.jsp">网购搜索网</a>）

    2003年，我在淘宝也买了几次东西了，一日取完快递，给了他10元快递费，正欲转身离开，
那哥们叫住我，说，再给五毛。

    我说为啥，他说，刚才给你打电话了，电话费五角钱，得你出。

   我靠。。。（此处省略3500字），  我就不提那是哪家快递了。
   
   好了，正文。

    网购离不开快递，不管你是买，还是卖。

    一手交钱，一手交货，打酱油的小孩都懂。然而这个东西在网上做起来，却是相当的纠结，
就像先有鸡还是先有蛋一样纠结。

    你不给钱，快递就不让你拆包装，而你看不到里边的东西，又怎么能放心给钱。

    这个问题也让我纠结， 直到有一次在京东，买了一台空气加湿器，
打开了包装才发现，下错单了，买的不是我要的那款，

    然后给客服打电话，没想到很爽快的就答应退货，第二天快递就上门来取，不收快递费。

    我对京东的信任，油然而生，之后我在京东买了很多东西，先钱还是先货的问题，终于
可以不用再纠结了。





<!-- 
我的网购十年（五） - 网购搜索
作者：游   （源自：<a target="_blank" href="http://www.wanggousousuo.com/blog.jsp">网购搜索网</a>）

    提到搜索，主要就是两家，百度和google。

    Goole的技术实力不用多说，Google凭借其技术打遍全球，然而在中国，却一路坎坷，
因为这个国度，政治压倒技术，压倒一切。
    
    2002年， 几个室友交流看片的网站，一个室友听了之后说，不用费这劲，上网一搜，有的是。
然后我们去试了一下，还真是。

    我当时就想，就没人来查封他？ 后来真有人查了，然后google被封了。  可大家当时用的是google么？好像不是。
但google还是掉坑里了。之后数年，google的中国之行就一路是坑，直到最后退出中国。

    google 2009提供过购物搜索，但我一直没用过，到我开始关注网购搜索到时候，google已经关停了
在中国的购物搜索服务。 不过google的这个购物搜索 和我做的这个网购搜索应该不是一回事。


 -->










待续。。。


</pre>
		 
	</div>
		 
		
</div>
	
	<!-- end div main -->
	
<jsp:include page="/part/footer3.jsp" />
</body>
</html>