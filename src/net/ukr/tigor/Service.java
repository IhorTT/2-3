package net.ukr.tigor;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class Service {
    private static  Object getData(Field fld, Object obj) throws IllegalAccessException {
        Object result = null;
        if (fld.getType() == String.class){
            result = (String)fld.get(obj);
        }else if (fld.getType() == int.class){
            result = fld.getInt(obj);
        }else if (fld.getType() == long.class){
            result = fld.getLong(obj);
        }else if(fld.getType() == short.class){
            result = fld.getShort(obj);
        }else if(fld.getType() == boolean.class){
            result = fld.getBoolean(obj);
        }

        return result;
    }

    public static void serialize(Object obj,String path){

        StringBuilder sb = new StringBuilder();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field fld: fields ) {
            if (fld.isAnnotationPresent(Save.class)){
                if (Modifier.isPrivate(fld.getModifiers())) {
                    fld.setAccessible(true);
                }
                sb.append(fld.getName()+":");
                try {
                    sb.append(getData(fld,obj));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                sb.append(System.lineSeparator());
            }
        }

        try (FileWriter pw = new FileWriter(path)) {
            pw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserialization(Object obj, String path) {

        try (BufferedReader bf = new BufferedReader(new FileReader(new File(path)))) {
            String str = "";
            for (; (str = bf.readLine()) != null;) {
                String[] pair = str.split(":");
                Field fld = obj.getClass().getDeclaredField(pair[0]);
                if (Modifier.isPrivate(fld.getModifiers())){
                    fld.setAccessible(true);
                }
                if (fld.isAnnotationPresent(Save.class)){
                    fillField(obj,fld,pair[1]);
                }
            }
        } catch (IOException | IllegalAccessException| NoSuchFieldException  e) {
            e.printStackTrace();
        }

    }

    private static void fillField(Object obj, Field fld, String value) throws IllegalAccessException {

        if (fld.getType() == String.class){
           fld.set(obj,value);
        }else if (fld.getType() == int.class){
            fld.set(obj,Integer.parseInt(value));
        }else if (fld.getType() == long.class){
            fld.set(obj,Long.parseLong(value));
        }else if(fld.getType() == short.class){
            fld.set(obj,Short.parseShort(value));
        }else if(fld.getType() == boolean.class){
            fld.set(obj,Boolean.parseBoolean(value));
        }
    }

}


