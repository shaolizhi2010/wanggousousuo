package com.html;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * tag like <div></div>
 * 
 * @author shaoliz
 * 
 */
public class DoubleTag extends BaseTag {
    
    /*
    
    List<Node> children = new ArrayList<Node>();

    public DoubleTag(String html) {
        
        if( !html.startsWith("<")) {//tag name, like div
            this.name = html;
            this.html = "<"+html+"></"+html+">";//to <div></div>
        }
        else {
            this.name = StringUtils.substringBetween(html, "</",">").trim();
            this.html = html;
        }
        
        
    }

    public void append(String html) {

        if (HtmlUtil.isSingleTag(html)) {
            this.append(new SingleTag(html));
        }

        if (HtmlUtil.isDoubleTag(html)) {
            this.append(new DoubleTag(html));
        }
        // is text
        this.append(new Text(html));
    }

    public void append(Node node) {
        this.children.add(node);
    }

    @Override
    public String html() {
        StringBuffer buffer = new StringBuffer();
        
        
        
        for (Node child : children) {
            buffer.append(child.toString());
        }
        return buffer.toString();
    }
*/
}
