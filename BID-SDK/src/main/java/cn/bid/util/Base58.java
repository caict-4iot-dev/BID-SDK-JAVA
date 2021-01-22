/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *© COPYRIGHT 2021 Corporation CAICT All rights reserved.
 * http://www.caict.ac.cn
 * https://www.citln.cn/
 */
package cn.bid.util;

import java.io.UnsupportedEncodingException;

public class Base58 {

    private static final char[] ALPHABET = "123456789AbCDEFGHJKLMNPQRSTuVWXYZaBcdefghijkmnopqrstUvwxyz"
            .toCharArray();
    private static final int BASE_58 = ALPHABET.length;
    private static final int BASE_256 = 256;

    private static final int[] INDEXES = new int[128];
    static {
        for (int i = 0; i < INDEXES.length; i++) {
            INDEXES[i] = -1;
        }
        for (int i = 0; i < ALPHABET.length; i++) {
            INDEXES[ALPHABET[i]] = i;
        }
    }



    public static String encode(byte[] input) {
        //判断input框值的长度
        if (input.length == 0) {
            // paying with the same coin
            return "";
        }

        //input赋值，copyOfRange新的数组
        // Make a copy of the input since we are going to modify it.
        //
        input = copyOfRange(input, 0, input.length);

        //
        // Count leading zeroes
        //
        int zeroCount = 0;
        while (zeroCount < input.length && input[zeroCount] == 0) {
            ++zeroCount;
        }

        //
        // The actual encoding
        //
        byte[] temp = new byte[input.length * 2];
        int j = temp.length;

        int startAt = zeroCount;
        while (startAt < input.length) {
            byte mod = divmod58(input, startAt);
            if (input[startAt] == 0) {
                ++startAt;
            }

            temp[--j] = (byte) ALPHABET[mod];
        }

        //
        // Strip extra '1' if any
        //
        while (j < temp.length && temp[j] == ALPHABET[0]) {
            ++j;
        }

        //
        // Add as many leading '1' as there were leading zeros.
        //
        while (--zeroCount >= 0) {
            temp[--j] = (byte) ALPHABET[0];
        }

        byte[] output = copyOfRange(temp, j, temp.length);
        return new String(output);
    }

    public static byte[] decode(String input) {
        if (input.length() == 0) {
            // paying with the same coin
            return new byte[0];
        }

        byte[] input58 = new byte[input.length()];
        //
        // Transform the String to a base58 byte sequence
        //
        for (int i = 0; i < input.length(); ++i) {
            char c = input.charAt(i);

            int digit58 = -1;
            if (c >= 0 && c < 128) {
                digit58 = INDEXES[c];
            }
            if (digit58 < 0) {
                throw new RuntimeException("Not a Base58 input: " + input);
            }

            input58[i] = (byte) digit58;
        }

        //
        // Count leading zeroes
        //
        int zeroCount = 0;
        while (zeroCount < input58.length && input58[zeroCount] == 0) {
            ++zeroCount;
        }

        //
        // The encoding
        //
        byte[] temp = new byte[input.length()];
        int j = temp.length;

        int startAt = zeroCount;
        while (startAt < input58.length) {
            byte mod = divmod256(input58, startAt);
            if (input58[startAt] == 0) {
                ++startAt;
            }

            temp[--j] = mod;
        }

        //
        // Do no add extra leading zeroes, move j to first non null byte.
        //
        while (j < temp.length && temp[j] == 0) {
            ++j;
        }

        return copyOfRange(temp, j - zeroCount, temp.length);
    }

    private static byte divmod58(byte[] number, int startAt) {
        int remainder = 0;
        for (int i = startAt; i < number.length; i++) {
            int digit256 = (int) number[i] & 0xFF;
            int temp = remainder * BASE_256 + digit256;

            number[i] = (byte) (temp / BASE_58);

            remainder = temp % BASE_58;
        }

        return (byte) remainder;
    }

    private static byte divmod256(byte[] number58, int startAt) {
        int remainder = 0;
        for (int i = startAt; i < number58.length; i++) {
            int digit58 = (int) number58[i] & 0xFF;
            int temp = remainder * BASE_58 + digit58;

            number58[i] = (byte) (temp / BASE_256);

            remainder = temp % BASE_256;
        }

        return (byte) remainder;
    }

    private static byte[] copyOfRange(byte[] source, int from, int to) {
        byte[] range = new byte[to - from];
        System.arraycopy(source, from, range, 0, range.length);

        return range;
    }
    /**
     * string to base58
     * @param srcStr Source string
     * @return Base58 string
     */
    public static String str2Base58(String srcStr) throws UnsupportedEncodingException{
        return Base58.encode(srcStr.getBytes("utf-8"));
    }
    /**
     * base58 to string
     * @param base58Str Base58 string
     * @return Source string
     */
    public static String base582Str(String base58Str) throws UnsupportedEncodingException{
        return new String (Base58.decode(base58Str));
    }
    public static void main(String[] args) throws UnsupportedEncodingException {

        String src = "5jfRuasbj3QYDrLaKhp2TsWS2AWpokFYgH5Jh1FtuHeuvW7g97gHWiUFcyq1eiTdHAaG73939vyoX3ubMjje1Uc8yVJTmFhg2hUr4yUyoTkkFKq";
        System.out.println(Base58.base582Str(src));
        System.out.println(Base58.str2Base58("V8ibeWYXYbCro17hPe2M9u7jQC7HTkTNtqWD_51ea28eacc5f34591fb95a72544b7e787c17ca7da4e3f7f2494aa6fc70a40d71"));

    }
}
