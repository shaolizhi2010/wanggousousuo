
(function(win,doc){
    var s = doc.createElement("script"), h = doc.getElementsByTagName("head")[0];
    if (!win.alimamatk_show) {
        s.charset = "gbk";
        s.async = true;
        s.src = "http://a.alimama.cn/tkapi.js";
        h.insertBefore(s, h.firstChild);
    };
    var o = {
        pid: "mm_41911274_3510173_11513408",/*推广单元ID，用于区分不同的推广渠道*/
        appkey: "",/*通过TOP平台申请的appkey，设置后引导成交会关联appkey*/
        unid: ""/*自定义统计字段*/
    };
    win.alimamatk_onload = win.alimamatk_onload || [];
    win.alimamatk_onload.push(o);
})(window,document);
