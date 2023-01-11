package org.example.v4.result.screen;

import org.example.v4.UtilStaticV4;

public class UpdateHTMLResultScreen {
    public String domainStr;
    public String[] colStrs;
    public String[] colLongs;
    public String[] colDates;

    public String thymleafInitUrl;

    public UpdateHTMLResultScreen(UtilStaticV4 usv) {
        this.domainStr = usv.domainStr;
        this.colStrs = usv.colStrs;
        this.colLongs = usv.colLongs;
        this.colDates = usv.colDates;
        this.thymleafInitUrl = usv.thymleafInitUrl;
    }
}
