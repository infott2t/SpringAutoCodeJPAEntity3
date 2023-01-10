package org.example.v4;

public class UtilStaticV4 {

    public static String domainStr; // entity name, small letter.
    public static String[] colStrs;  // entity column, String array.
    public static String[] colLongs; // entity column, Long array.
    public static String[] colDates; // entity column, LocalDateTime array.

    public UtilStaticV4(String domainStr, String[] colStrs, String[] colLongs, String[] colDates) {
        this.domainStr = domainStr;
        this.colStrs = colStrs;
        this.colLongs = colLongs;
        this.colDates = colDates;
    }
}
