package com.dzy.wx.pay.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by 16440 on 2017/3/9.
 */
public class SignUtils {
    private static final String ABC = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String createRandom() {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int j = 0; j < 32; j++) {
            int i = random.nextInt(32);
            stringBuffer.append(ABC.charAt(i));
        }
        return stringBuffer.toString();
    }

    public static Map Object2Map(Object object) {
        Map<String, String> data = new TreeMap<String, String>();
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            String k = null, v = null;
            Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
            for (Annotation annotation : declaredAnnotations) {
                JsonProperty value = (JsonProperty) annotation;
                k = value.value();
                if (org.springframework.util.StringUtils.isEmpty(k))
                    continue;
            }
            Method[] declaredMethods = object.getClass().getDeclaredMethods();
            for (Method method : declaredMethods) {
                String methodName = "get" + castStr(field.getName());
                if (method.getName().equals(methodName)) {
                    try {
                        Object o = method.invoke(object, null);
                        if (o == null) {
                            continue;
                        }
                        v = o.toString();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (k==null||v==null) {
                continue;
            }
            data.put(k, v);
        }
        System.out.println(data.toString());

//        try {
//            BeanInfo info = Introspector.getBeanInfo(object.getClass(), Introspector.IGNORE_ALL_BEANINFO);
//            PropertyDescriptor[] descriptors = info.getPropertyDescriptors();
//            for (PropertyDescriptor pd : descriptors) {
//                String name = pd.getName();
//                Object value = pd.getReadMethod().invoke(object);
//                if ("class".equals(name) || value == null || StringUtils.isBlank(value.toString()))
//                    continue;
//                if (value instanceof Date) {
//                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    data.put(name, format.format((Date) value));
//                } else if (value instanceof Map) {
//                    data.put(name, JSON.toJSONString(value, SerializerFeature.WriteDateUseDateFormat));
//                } else if (value instanceof List || value instanceof Set) {
//                    // 如果调用list.toString方法，会在元素之间加一个空格，但是解析的时候解析成json又去掉了空格，导致解析失败，所以使用json来转string。
//                    data.put(name, JSON.toJSONString(value, SerializerFeature.WriteDateUseDateFormat));
//                } else {
//                    data.put(name, value.toString());
//                }
//            }
//        } catch (Exception e) {
//            System.err.println(e.getMessage().toString());
//        }
        return data;
    }

    private static String castStr(String name) {
        String first = name.substring(0, 1);
        StringBuffer buffer = new StringBuffer(name);
        return buffer.replace(0, 1, first.toUpperCase()).toString();
    }

    public static String md5(Map<String, String> data, String key) {
        String content = getSignContent(data, true);
        System.out.println("content:" + content);
        if (StringUtils.isBlank(content)) {
            return null;
        }
        StringBuilder buf = new StringBuilder(content);
        buf.append("&").append("key=" + key);

        String sign = MD5Util.md5(buf.toString()).toUpperCase();
        return sign;
    }

    /**
     * @param data
     * @param withSignType sign_type字段，  支付宝支付回调会有此字段。 true,计算签名(微信)；  false 不计算签名(支付宝)
     * @return
     */
    public static String getSignContent(Map<String, String> data, boolean withSignType) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        StringBuilder buf = new StringBuilder();
        TreeMap<String, String> map = new TreeMap<String, String>(data);

        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            String k = entry.getKey();
            if (StringUtils.isBlank(k)) {
                continue;
            }
            if ("class".equalsIgnoreCase(k) || "key".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
                continue;
            }
            if (!withSignType && "sign_type".equalsIgnoreCase(k)) {
                continue;
            }
            String v = entry.getValue();
            // 字段为空，不参与签名
            if (StringUtils.isBlank(v)) {
                continue;
            }
            buf.append(k);
            buf.append("=");
            buf.append(v);
            buf.append("&");
        }

        buf = buf.deleteCharAt(buf.length() - 1);
        return buf.toString();
    }
}
