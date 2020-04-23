package cn.swufe.Util;

import java.io.UnsupportedEncodingException;

/**
 * 常用工具类
 */
public class Utility {

    /**
     * 将页面中传入的数据进行字符集转换
     * @param str 待传入的字符串
     * @return  返回GBK格式的字符串。
     */
    public static String getGBK(String str){
        String result=null;
        if (str!=null){
            try {
                return new String(str.getBytes("8859_1"), "GBK");
            }catch(UnsupportedEncodingException ex){
                ex.printStackTrace();
                return null;
            }
        }
        return result;
    }

}
