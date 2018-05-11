package com.xucg.util.xml;

import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class XmlUtil {
    /**
     * xml 转换成map输出
     *
     * @param xml
     * @return
     */
    public static Map<String, String> getXmlValue(String xml) {
        SAXReader saxReader = new SAXReader();
        Document document = null;
        Map<String, String> nodemap = new HashMap<String, String>();
        Reader reader = null;
        try {
            reader = new StringReader(xml);
            document = saxReader.read(reader);
            Element root = document.getRootElement();
            for (Iterator it = root.elementIterator(); it.hasNext(); ) {
                Element e = (Element) it.next();
                nodemap.put(e.getName(), e.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return nodemap;
    }

    public static Object convertXmlStrToObject(String xmlStr, Class clazz) {
        Object xmlObject = null;
        try {
            XStream xStream = new XStream();
            xStream.alias("xml", clazz);
            // 进行将Xml转成对象的核心接口
            xmlObject = xStream.fromXML(xmlStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlObject;
    }
}
