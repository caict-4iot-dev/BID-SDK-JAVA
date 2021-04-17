package cn.ac.caict.bid.util;

import java.io.UnsupportedEncodingException;

public class ConvertUtil {
    /**
     * byte array to Ascii
     * @param cbyte byte
     * @return String value
     */
    public static String  byteToAscii(byte cbyte) throws UnsupportedEncodingException {
        byte[] abyte=new byte[1];
        abyte[0] = cbyte;
        return new String(abyte, "ISO8859-1");
    }
}
