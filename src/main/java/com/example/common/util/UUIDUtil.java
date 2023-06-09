/**
 * UUIDUtil is a utility class for generating UUIDs.
 *
 * This utility class can be used
 * to retrieve a randomly generated UUID
 * or a specified number of UUIDs.
 */
package com.example.common.util;
import java.util.UUID;
public class UUIDUtil {

    /**
     * Retrieves a single UUID.
     *
     * @return The generated UUID.
     */
    public static String getOneUUID() {
        String s = UUID.randomUUID().toString();
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    /**
     * Retrieves a specified number of UUIDs.
     *
     * @param number The number of UUIDs to generate.
     * @return An array containing the generated UUIDs.
     */
    public static String[] getUUID(int number) {
        if (number < 1)
            return null;
        String[] ss = new String[number];
        for (int i = 0; i < number; i++)
            ss[i] = getOneUUID();
        return ss;
    }
}
