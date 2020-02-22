package com.timmist24.timsoresnstones.util;

import javax.swing.*;
import java.awt.*;

public class TestGui extends JFrame {
    private JLabel lable;
    private ImageIcon imageIcon;
    private JLabel iLable;
    public TestGui(){
        setLayout(new FlowLayout());
        lable = new JLabel("boop");
        add(lable);
        imageIcon = new ImageIcon();
        iLable = new JLabel(imageIcon);
        add(iLable);
    }
    public void close(){
        this.dispose();
    }
}
