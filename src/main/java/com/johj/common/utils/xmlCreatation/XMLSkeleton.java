package com.johj.common.utils.xmlCreatation;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * author:zhou_wenwei
 * mail:
 * date:2017/11/8
 * description:the basic struction of xml
 */
public class XMLSkeleton<T> {
    //xml中的元素名
    private String elementName;
    //元素属性值的map键值对
    private Map<String, String> attribureValMap = new LinkedHashMap<String, String>();
    //元素文本内容
    private String textContent;
    //元素子元素List集合
    private List<T> childrenElement = new ArrayList<T>();

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public Map<String, String> getAttribureValMap() {
        return attribureValMap;
    }

    public void setAttribureValMap(Map<String, String> attribureValMap) {
        this.attribureValMap = attribureValMap;
    }

    public List<T> getChildrenElement() {
        return childrenElement;
    }

    public void setChildrenElement(List<T> childrenElement) {
        this.childrenElement = childrenElement;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public XMLSkeleton(String elementName, Map<String, String> attribureValMap, List<T> childrenElement) {
        this.elementName = elementName;
        this.attribureValMap = attribureValMap;
        this.childrenElement = childrenElement;
    }

    public XMLSkeleton(String elementName) {
        this.elementName = elementName;
    }

    public XMLSkeleton() {
    }
}
