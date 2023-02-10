package org.example.v6;

public class UtilStaticV6 {


    public static String domainStr; // entity name, small letter.
    public static String[] colStrs;  // entity column, String array.
    public static String[] colLongs; // entity column, Long array.
    public static String[] colDates; // entity column, LocalDateTime array.

    public static String[] colNames; // entity column, In orders.

    public static String[] foreignCols; // entity column, In orders.


    public static String thymleafInitUrl;  // /administer/instanceurl

    public static String rootPackageStr;

    public static String projectPath;

    public static String nowEntityTextFieldStr;

    public UtilStaticV6(String domainStr, String[] colStrs, String[] colLongs, String[] colDates, String[] colNames, String[] foreignColStrs, String thymleafInitUrl, String rootPackageStr, String projectPath) {
        this.domainStr = domainStr;
        this.colStrs = colStrs;
        this.colLongs = colLongs;
        this.colDates = colDates;
        this.colNames = colNames;
        this.foreignCols = foreignColStrs;
        this.thymleafInitUrl = thymleafInitUrl;
        this.rootPackageStr = rootPackageStr;
        this.projectPath = projectPath;

    }

    public UtilStaticV6(String domainStr, String[] colStrs, String[] colLongs, String[] colDates, String[] colNames, String[] foreignColStrs, String thymleafInitUrl, String rootPackageStr, String projectPath, String nowEntityTextFieldStr) {
        this.domainStr = domainStr;
        this.colStrs = colStrs;
        this.colLongs = colLongs;
        this.colDates = colDates;
        this.colNames = colNames;
        this.foreignCols = foreignColStrs;
        this.thymleafInitUrl = thymleafInitUrl;
        this.rootPackageStr = rootPackageStr;
        this.projectPath = projectPath;
        this.nowEntityTextFieldStr = nowEntityTextFieldStr;

    }
}
