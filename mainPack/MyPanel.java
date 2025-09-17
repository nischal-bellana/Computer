package mainPack;

import BasicLogic.IntegCirc;
import BasicLogic.LogicComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class MyPanel extends JPanel implements Runnable {

    Deque<Boolean> output = new ArrayDeque<>();

    public void getOutput() {
        IntegCirc clock = (IntegCirc)LogicComponent.handbag.get("clock");
        output.offerFirst(clock.getOutputGate(0).Output());

        while(output.size() > 30){
            output.pollLast();
        }

        repaint(); // trigger redraw
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GREEN);
        ((Graphics2D) g).setStroke(new BasicStroke(3));

        int i = 0;
        boolean last = Boolean.TRUE.equals(output.peekFirst());
        for(boolean x: output){
            if(last^x){
                g.drawLine(50+10*i, 200+(!x?50:0),50+10*i, 200+(x?50:0));
                last = x;
            }
            g.drawLine(50+10*i, 200+(x?50:0), 60+10*i, 200+(x?50:0));
            i++;
        }
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Circle Color Example");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setBackground(Color.WHITE);
        frame.add(this);
        frame.setVisible(true);

        // Example: change color every second
        new Timer(100, e -> {
            // Randomly pick a new color
            getOutput();
        }).start();
    }
}
