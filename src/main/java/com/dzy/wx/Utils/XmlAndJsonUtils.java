package com.dzy.wx.Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.collections.map.HashedMap;

import java.util.Map;


public class XmlAndJsonUtils {

    //jackson 把xml转化为HashMap
    public static Map<String, Object> XML2HashMap(String xml) {
        Map<String, Object> jsonInMap = new HashedMap();
        XMLSerializer xmlSerializer = new XMLSerializer();
        ObjectMapper mapper = new ObjectMapper();
        try {
            JSON json = xmlSerializer.read(xml);
            jsonInMap = mapper.readValue(json.toString(),
                    new TypeReference<Map<String, Object>>() {
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonInMap;
    }

}