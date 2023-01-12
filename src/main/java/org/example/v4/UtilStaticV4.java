package org.example.v4;

public class UtilStaticV4 {

    public static String domainStr; // entity name, small letter.
    public static String[] colStrs;  // entity column, String array.
    public static String[] colLongs; // entity column, Long array.
    public static String[] colDates; // entity column, LocalDateTime array.

    public static String[] colNames; // entity column, In orders.

    public static String thymleafInitUrl;  // /administer/instanceurl



    public UtilStaticV4(String domainStr, String[] colStrs, String[] colLongs, String[] colDates, String[] colNames, String thymleafInitUrl) {
        this.domainStr = domainStr;
        this.colStrs = colStrs;
        this.colLongs = colLongs;
        this.colDates = colDates;
        this.colNames = colNames;
        this.thymleafInitUrl = thymleafInitUrl;

    }

    public static String upperCaseFirstLetter(String str){
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }
}
