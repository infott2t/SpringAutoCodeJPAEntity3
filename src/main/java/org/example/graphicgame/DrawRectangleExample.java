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


    private int clickCount; //3. 세번 클릭하면, 사각형 생성. 마우스 클릭 횟수.
    private Point clickPoint;       // clickPointFirst, clickPointTwice, clickPointThird가 같으면, clickPoint에 x,y값 저장.
    private Point clickPointFirst;
    private Point clickPointTwice;
    private Point clickPointThird;

    private Color[] colorArr;

    private Rectangle[] objectArr;

    //private int a;

    public DrawRectangleExample() {
        setTitle("Draw Rectangle Example. Thrid Click, same point. Make Rectangle.");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //사각형 배열 초기화
        objectArr = new Rectangle[1000];
        for (int i = 0; i < objectArr.length; i++) {
            objectArr[i] = new Rectangle(0,0,0,0);
        }

        //색상 배열 초기화
        /*
        colorArr = new Color[1000];
        for (int i = 0; i < colorArr.length; i++) {
            colorArr[i] = Color.blue;
        }
        */


        //더블클릭 카운트 초기화
        clickCount = 0;

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
                if(clickPoint!=null){
                    g.setColor(Color.BLUE);
                    g.fillRect(clickPoint.x-25, clickPoint.y-25, 50,50);

                    //클릭하면, 사각형 1개 저장하기.
                    for (int i = 0; i < objectArr.length; i++) {
                        if(objectArr[i].getX()==0 && objectArr[i].getY()==0){
                            objectArr[i]= new Rectangle(clickPoint.x-25, clickPoint.y-25, 50,50);
                            break;
                        }
                    }

                    //저장된 사각형 불러오기.
                    for (int i = 0; i < objectArr.length; i++) {
                        if(objectArr[i].getX()!=0 && objectArr[i].getY()!=0){
                            g.setColor(Color.BLUE);
                            //new Rectangle((int) objectArr[i].getX(),(int)objectArr[i].getY(),objectArr[i].width, objectArr[i].height);
                            g.fillRect((int) objectArr[i].getX(),(int)objectArr[i].getY(),objectArr[i].width, objectArr[i].height);
                        }
                    }
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
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                clickPointFirst = e.getPoint();
                clickCount++;

                if(clickCount==2){
                    clickPointTwice = e.getPoint();
                }

                if(clickCount==3){
                    clickPointThird = e.getPoint();

                    if(clickPointFirst.equals(clickPointTwice)){
                        if(clickPointTwice.equals(clickPointThird)){
                            clickPoint = clickPointFirst;
                        }else{
                            clickPointFirst = null;
                            clickPointTwice = null;
                            clickPointThird = null;
                        }
                    }
                    clickCount=0;
                }

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

