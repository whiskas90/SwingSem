package com.sem.frames;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PaintImage extends JPanel {

    private Image backImg;
    public PaintImage(String fileName){
        try {
            backImg = ImageIO.read(this.getClass().getResource(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backImg,0,0,this);
    }
}
