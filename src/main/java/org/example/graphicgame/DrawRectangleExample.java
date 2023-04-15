package org.example.graphicgame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.OptionalDouble;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawRectangleExample extends JFrame {
    private JPanel canvas;
    private Point startPoint;
    private Point endPoint;

    //private Rectangle[] objectArr;

    //private int a;

    public DrawRectangleExample() {
        setTitle("Draw Rectangle Example");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //objectArr = new Rectangle[1000];
        //a=0;

        canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                this.setBackground(Color.BLACK);

                if (startPoint != null && endPoint != null) {
                    int x = Math.min(startPoint.x, endPoint.x);
                    int y = Math.min(startPoint.y, endPoint.y);
                    int width = Math.abs(startPoint.x - endPoint.x);
                    int height = Math.abs(startPoint.y - endPoint.y);
                    g.setColor(Color.WHITE);
                    g.drawRect(x, y, width, height);




                }


            }
        };
        canvas.setBackground(Color.BLACK);
        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endPoint = e.getPoint();
                canvas.repaint();

            }
        });
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();



            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endPoint = e.getPoint();

                canvas.repaint();

            }
        });

        add(canvas);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DrawRectangleExample();
    }
}

