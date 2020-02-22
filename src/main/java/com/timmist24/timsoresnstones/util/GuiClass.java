package com.timmist24.timsoresnstones.util;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;

import java.awt.*;

public class GuiClass extends Thread{
    TestGui testGui;
    @Override
    public void run() {

        testGui = new TestGui();
        testGui.setSize(200,100);
        testGui.setVisible(true);
        testGui.setResizable(false);

        try
        {
            // Displaying the thread that is running
            TimsOresNStonesMain.logger.error("Thread " +
                    Thread.currentThread().getId() +
                    " is running");

        }
        catch (Exception e)
        {
            // Throwing an exception
            TimsOresNStonesMain.logger.error("Exception is caught");
        }
    }

    public void update(String lable, Image image){

    }
}
