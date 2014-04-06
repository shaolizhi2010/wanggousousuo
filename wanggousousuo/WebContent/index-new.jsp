<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
<link rel="stylesheet" href="css/index.css" type="text/css" />
</head>

<body>
    <div id="head">
        <a class="logo_new" href="/welcome"></a>
        <ul class="menu_leo">
            <li><a href="/connect/auth/weibo"><em
                    class="i_sina">&nbsp;</em>微博登录</a></li>
            <li><a href="/connect/auth/qzone"><em class="i_QQ">&nbsp;</em>
                    QQ登录</a></li>
            <li><a href="/user/login" class="red_f tunder_f">登录</a></li>
            <li style="border-right: none;"><a
                href="/user/register" class="red_f tunder_f">注册</a></li>
        </ul>
    </div>
    <div id="menu">
            <ul class="nav_new">
                <li class="home"><a href="/welcome" class="r_r ">首页<span
                        class="shining none_f"></span></a></li>
                <li class="group"><a href="/magazine"
                    class="r_l r_r ">翻杂志</a></li>
                <li class="guang"><a href="/guang/hot"
                    class="r_l r_d">今日最热<span class="item_right"></span></a>
                    <span class="hoverNav"> <a
                        href="/guang/catalog/dress?nid=11&amp;cata_id=2000000000000"
                        class="">衣服</a> <a
                        href="/guang/catalog/shoes?nid=13&amp;cata_id=6000000000000"
                        class="">鞋子</a> <a
                        href="/guang/catalog/bag?nid=15&amp;cata_id=5000000000000"
                        class="">包包</a> <a
                        href="/guang/catalog/access?nid=1097&amp;cata_id=7000000000000"
                        class="">配饰</a> <a
                        href="/guang/catalog/jiaju?nid=1093&amp;cata_id=9000000000000"
                        class="r_r ">家居</a>
                </span></li>
                <li class="beaut"><a href="/beauty/" class="r_l ">口碑美妆</a>
                    <a
                    href="/guang/catalog/beauty?nid=1095&amp;cata_id=8000000000000"
                    class="r_r ">逛美妆</a></li>
                <li class="report"><a href="/goods/report"
                    class="r_l ">搭配</a> <a href="/daren" class="r_r ">达人</a>
                </li>
                <li class="welfar"><a href="/brand" class="r_l ">品牌</a>
                    <a href="/welfare" class="">福利社</a> <a href="/shop/"
                    class="">好店</a></li>
            </ul>
           
           <div id="search"></div>
           
    </div>
    <div id="container"></div>
</body>
</html>