package com.express.mvc;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class HandlerMapping {

    private static Map<String,MVCMapping> data = new HashMap<>();
    public static MVCMapping get_uri(String uri){
        return data.get(uri);
    }
    public  static void load(InputStream inputStream){
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            Collection<Object> values = properties.values();
            for (Object o:values) {
                String classname = (String)o;
                Class aClass = Class.forName(classname);
                Object o1 = aClass.getConstructor().newInstance();
                Method[] methods = aClass.getMethods();
                for (Method method:methods){
                    Annotation[] annotations = method.getAnnotations();
                    if (annotations!=null){
                        for (Annotation annotation:annotations){
                            if (annotation instanceof ResponseBody){
                                MVCMapping mvcMapping = new MVCMapping(o1,method,ResponseType.Text);
                                Object put = data.put(((ResponseBody) annotation).value(), mvcMapping);
                                System.out.println(((ResponseBody) annotation).value());
                                System.out.println(put);
                                if (put!=null){
                                    throw new RuntimeException("请求地址重复：" + ((ResponseBody) annotation).value());
                                }
                            }
                            else if (annotation instanceof ResponseView){
                                MVCMapping mvcMapping = new MVCMapping(o1,method,ResponseType.View);
                                Object put = data.put(((ResponseView) annotation).value(), mvcMapping);
                                if (put!=null){
                                    throw new RuntimeException("请求地址重复：" + ((ResponseView) annotation).value());
                                }

                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static class MVCMapping {
        Object object;
        Method method;
        ResponseType type;

        public MVCMapping(Object object, Method method, ResponseType type) {
            this.object = object;
            this.method = method;
            this.type = type;
        }

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public ResponseType getType() {
            return type;
        }

        public void setType(ResponseType type) {
            this.type = type;
        }
    }
}
