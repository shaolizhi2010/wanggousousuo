package com.html;

public class HtmlPart {

    String html = "";

    public HtmlPart(String html) {
        this.html = html;
    }

    public void addAttr(String name, String value) {
        int pos = html.indexOf(">");
        this.html = new StringBuilder(html).insert(pos,
                " " + name + "='" + value + "'").toString();
    }

    public void append(String target) {
        int pos = html.lastIndexOf("</");
        this.html = new StringBuilder(html).insert(
                pos,
                System.getProperty("line.separator") + target
                        + System.getProperty("line.separator")).toString();
    }

    public void append(HtmlPart target) {
        this.append(target.html());

    }

    public String html() {
        return this.html;
    }

    public void html(String html) {
        this.html = html;

    }

}
