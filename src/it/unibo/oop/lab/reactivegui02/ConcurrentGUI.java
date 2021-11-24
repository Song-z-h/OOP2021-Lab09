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
import javax.swing.SwingUtilities;

/**
 * A simple GUI app.
 *
 */
public class ConcurrentGUI {

    private static final double WIDTH_PERC = 0.2;
    private static final double HEIGHT_PERC = 0.1;
    private final JLabel display;
    private final Agent agent;
    private final JButton up = new JButton("up");
    private final JButton down = new JButton("down");
    private final JButton stop = new JButton("stop");

    /**
     * stop the agent.
     */
    protected void stopAgent() {
        agent.stopCounting();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                up.setEnabled(false);
                down.setEnabled(false);
                stop.setEnabled(false);
            }
        });
    }

    /**
     * Constructor.
     */
    public ConcurrentGUI() {
        super();
        final JFrame frame = new JFrame();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((int) (screenSize.getWidth() * WIDTH_PERC), (int) (screenSize.getHeight() * HEIGHT_PERC));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        display = new JLabel();
        display.setText("0");
        final JPanel panel = new JPanel();
        final JPanel canvas = new JPanel();

        agent = new Agent();
        up.addActionListener(e -> agent.countUp());

        down.addActionListener(e -> agent.countDown());

        stop.addActionListener(e -> stopAgent());

        new Thread(agent).start();

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
                counter += up ? +1 : -1;
                final int countmoment = this.counter;
                try {
                    SwingUtilities.invokeLater(() -> display.setText(Integer.toString(countmoment)));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
