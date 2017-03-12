package com.dzy.wx.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用Jackson工具, xml和json与javaBean之间转化
 */
@Component
public class XmlAndJsonUtils {

    private static org.apache.log4j.Logger logger = Logger.getLogger(XmlAndJsonUtils.class);

    //  xml转化为HashMap
    public static Map<String, Object> XML2HashMap(String xml) {
        Map<String, Object> jsonInMap = new HashedMap();
        XMLSerializer xmlSerializer = new XMLSerializer();
        ObjectMapper mapper = new ObjectMapper();
        JSON json = xmlSerializer.read(xml);
        try {
            jsonInMap = mapper.readValue(json.toString(),
                    new TypeReference<Map<String, Object>>() {
                    });
        } catch (IOException e) {
            logger.error("XmlAndJsonUtils.XML2HashMap has error");
            e.printStackTrace();
        }
        return jsonInMap;
    }

    public static HashMap xml2HashMap(String str) {
        XmlMapper mapper = new XmlMapper();
        HashMap readValue = null;
        try {
            readValue = mapper.readValue(str, HashMap.class);
        } catch (IOException e) {
            logger.error("XmlAndJsonUtils.xml2HashMap has error");
            e.printStackTrace();
        }
        return readValue;

    }
}