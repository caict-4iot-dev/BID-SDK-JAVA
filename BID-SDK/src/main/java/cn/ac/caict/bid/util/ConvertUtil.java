package cn.ac.caict.bid.util;

import java.io.UnsupportedEncodingException;

public class ConvertUtil {
    /**
     * byte数组转Ascii
     * @param cbyte byte
     * @return String值
     */
    public static String  byteToAscii(byte cbyte) throws UnsupportedEncodingException {
        byte[] abyte=new byte[1];
        abyte[0] = cbyte;
        return new String(abyte, "ISO8859-1");
    }
}
