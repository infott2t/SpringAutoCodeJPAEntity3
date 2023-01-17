package org.example.themes;


import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class MyFlatLaf extends FlatDarkLaf {
    public static boolean setup(){
        return setup(new MyFlatLaf());
    }

    @Override
    public String getName() {
        return "My FlatLaf";
    }


}
