package org.example.v4;


public class UtilStrConvV4 {



    public String tableName;
    public String[] valBefore;



    public UtilStrConvV4(String tableName, String[] valBefore, String method) {
        if(method.equals("Camel")) {
            //All lowerCase.
            for (int i = 0; i < valBefore.length; i++) {
                valBefore[i] = valBefore[i].toLowerCase();
                //_다음 문자 대문자. after_, UpperCase.

                int a = valBefore[i].indexOf("_");
                if (a == -1) {
                    continue;
                }
                System.out.println("index, _ : " + a);
                String frontval = valBefore[i].substring(0, a);
                String backval = valBefore[i].substring(a + 1);

                String backvalUpp = backval.substring(0, 1);
                backvalUpp = backvalUpp.toUpperCase();
                String backvalLow = backval.substring(1);
                System.out.println(frontval + backvalUpp + backvalLow);
                String valBefore0 = frontval + backvalUpp + backvalLow;
                valBefore[i] = valBefore0;

                a = valBefore[i].indexOf("_");
                if (a == -1) {
                    continue;
                }
                System.out.println("index, _ : " + a);
                frontval = valBefore[i].substring(0, a);
                backval = valBefore[i].substring(a + 1);

                backvalUpp = backval.substring(0, 1);
                backvalUpp = backvalUpp.toUpperCase();
                backvalLow = backval.substring(1);
                System.out.println(frontval + backvalUpp + backvalLow);
                valBefore0 = frontval + backvalUpp + backvalLow;
                valBefore[i] = valBefore0;

                a = valBefore[i].indexOf("_");
                if (a == -1) {
                    continue;
                }
                System.out.println("index, _ : " + a);
                frontval = valBefore[i].substring(0, a);
                backval = valBefore[i].substring(a + 1);

                backvalUpp = backval.substring(0, 1);
                backvalUpp = backvalUpp.toUpperCase();
                backvalLow = backval.substring(1);
                System.out.println(frontval + backvalUpp + backvalLow);
                valBefore0 = frontval + backvalUpp + backvalLow;
                valBefore[i] = valBefore0;


            }
            this.valBefore = valBefore;
            this.tableName = tableName;
        }else if(method.equals("sqlUpper")){
            //All UpperrCase.
            for (int i = 0; i < valBefore.length; i++) {
                valBefore[i] = valBefore[i].toUpperCase();
            }
            this.valBefore = valBefore;
            this.tableName = tableName;
        }
    }



    public String[] getValBefore() {
        return valBefore;
    }

    public void setValBefore(String[] valBefore) {
        this.valBefore = valBefore;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
