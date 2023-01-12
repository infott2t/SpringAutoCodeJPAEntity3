package org.example.v4.result.context;

import org.example.v4.UtilStaticV4;

public class RootIndexContextStr {
    public String rootIndexHTMLStr;
    public String domainStr;
    public String[] colStrs;
    public String[] colLongs;
    public String[] colDates;

    public String[] colNames;

    public String thymleafInitUrl;
    public String thymleafInitUrlDomain; //thymleafInitUrl + "/" +domainStr


    public RootIndexContextStr(UtilStaticV4 usv) {
        this.domainStr = usv.domainStr;
        this.colStrs = usv.colStrs;
        this.colLongs = usv.colLongs;
        this.colDates = usv.colDates;
        this.colNames = usv.colNames;
        this.thymleafInitUrl = usv.thymleafInitUrl;
        this.thymleafInitUrlDomain = usv.thymleafInitUrl + "/" + domainStr;
        this.rootIndexHTMLStr = htmlTexting();
    }

    private String htmlTexting() {
        return "" +
                "<!doctype html>\n" +
                "<html lang=\"ko\" xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "\n" +
                "<head>\n" +
                "    <!-- Required meta tags -->\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "\n" +
                "    <!-- Bootstrap CSS -->\n" +
                "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\n" +
                "          integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"/css/nav.css\">\n" +
                "    <title>오늘, 일 하시는 것은 어떤가요. 좋은 하루되세요.</title>\n" +
                "    <style>\n" +
                "        .navbar-brand {\n" +
                "            font-size: 1rem;\n" +
                "        }\n" +
                "        .card {\n" +
                "            margin-bottom: 10px;\n" +
                "        }\n" +
                "\n" +
                "        .nav_bottom {\n" +
                "            margin-bottom: 40px;\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "    </style>\n" +
                "    <script th:inline=\"javascript\">\n" +
                "        /*<![CDATA[*/\n" +
                "        let result = [[${workPlanList}]]\n" +
                "        /*]]>*/\n" +
                "    </script>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<div class=\"fixed-bottom\">\n" +
                "    <nav class=\"navbar navbar-expand  nav1\">\n" +
                "        <div class=\"container-fluid\">\n" +
                "            <a class=\"navbar-brand\" href=\"#\">스마트 팩토리</a>&nbsp;&nbsp;&nbsp; <a class=\"navbar-brand\">테스트, CRUD 페이지.</a>\n" +
                "            <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarNav\"\n" +
                "                    aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
                "                <span class=\"navbar-toggler-icon\" style=\"color:black;margin-top:5px;\"><i class=\"bi bi-justify\"></i></span>\n" +
                "            </button>\n" +
                "            <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n" +
                "\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </nav>\n" +
                "</div>\n" +
                "<div class=\"container\">\n" +
                "\n" +
                "    <br/>\n" +
                "    <h2>엔티티 데이터 CRUD 체크 테스트</h2>\n" +
                "    <br />\n" +
                "    <br/>\n" +
                "    <p><a th:href=\"@{"+thymleafInitUrlDomain+"}\">테이블 "+domainStr+"로 이동</a></p>\n" +


                "</div>\n" +
                "</div>\n" +
                "<br />\n" +
                "<br />\n" +
                "<br />\n" +
                "<br />\n" +
                "<br />\n" +
                "<br />\n" +
                "<br />\n" +
                "<br />\n" +
                "\n" +
                "<nav class=\"navbar nav_bottom\">\n" +
                "    <div class=\"container-fluid\">\n" +
                "        <div class=\"navbar-text\" href=\"#\">\n" +
                "            <i class=\"bi bi-emoji-smile\"></i>\n" +
                "            스마트 팩토리, UI 만들어봤습니다. 2021년 6월 21일 ~ 2021 7월 9일., 2022년 12월 29일 ~ ... <br />한번 만들어보세요~. 일하기가 더 좋아졌으면 좋겠습니다. 좋은 개발되세요~.\n" +
                "            감사합니다.<br />작성자: 최현일\n" +
                "            |\n" +
                "            Github주소 <a href=\"https://github.com/infott2t/smartFactory-ex\">@infott2t</a>\n" +
                "            <br/>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</nav>\n" +
                "\n" +
                "\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<!-- Optional JavaScript; choose one of the two! -->\n" +
                "\n" +
                "<!-- Option 1: Bootstrap Bundle with Popper -->\n" +
                "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js\"\n" +
                "        integrity=\"sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM\"\n" +
                "        crossorigin=\"anonymous\"></script>\n" +
                "\n" +
                "<!-- Option 2: Separate Popper and Bootstrap JS -->\n" +
                "<!--\n" +
                "  <script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js\" integrity=\"sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p\" crossorigin=\"anonymous\"></script>\n" +
                "  <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js\" integrity=\"sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF\" crossorigin=\"anonymous\"></script>\n" +
                "  -->\n" +
                "<script src=\"/js/jquery-3.6.3.min.js\"></script>\n" +
                "<script>\n" +
                "    $(document).ready(function(){\n" +
                "        var email = $(\"#email\").text()\n" +
                "\n" +
                "        setTimeout(function () {\n" +
                "            window.ReactNativeWebView.postMessage('{\"email\" : \"'+email+'\"}')\n" +
                "        }, 2000)\n" +
                "\n" +
                "\n" +
                "    });\n" +
                "\n" +
                "</script>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }
}
