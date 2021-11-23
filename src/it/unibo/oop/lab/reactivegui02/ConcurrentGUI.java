package it.unibo.oop.lab.reactivegui02;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A simple GUI app.
 *
 */
public class ConcurrentGUI {

    private static final double WIDTH_PERC = 0.2;
    private static final double HEIGHT_PERC = 0.1;
    private final JLabel display;

    /**
     * Constructor.
     */
    public ConcurrentGUI() {
        super();
        final JFrame frame = new JFrame();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((int) (screenSize.getWidth() * WIDTH_PERC), (int) (screenSize.getHeight() * HEIGHT_PERC));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JButton up = new JButton("up");
        final JButton down = new JButton("down");
        final JButton stop = new JButton("stop");
        display = new JLabel();
        display.setText("0");
        final JPanel panel = new JPanel();
        final JPanel canvas = new JPanel();

        final Agent agent = new Agent();
        new Thread(agent).start();
        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                agent.countUp();
            }
        });

        down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                agent.countDown();
            }
        });

        stop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                agent.stopCounting();
                up.setEnabled(false);
                down.setEnabled(false);
                stop.setEnabled(false);
            }
        });

        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(display);
        panel.add(up);
        panel.add(down);
        panel.add(stop);
        canvas.add(panel, BorderLayout.CENTER);
        frame.setContentPane(canvas);
        frame.setVisible(true);
    }

    private class Agent implements Runnable {
        private volatile boolean up = true;
        private volatile boolean stop;
        private int counter;

        public void stopCounting() {
            this.stop = true;
        }

        public void countUp() {
            this.up = true;
        }

        public void countDown() {
            this.up = false;
        }

        @Override
        public void run() {
            while (!stop) {
                final int countmoment = this.counter;
                display.setText(Integer.toString(countmoment));
                if (up) {
                    counter++;
                } else {
                    if (countmoment > 0) {
                        counter--;
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
