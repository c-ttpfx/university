import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;


public class HappyValentinsDay extends JFrame {

    public static boolean flag = false;

    private static final long serialVersionUID = 1L;

    private JLabel label;
    private JButton button1;
    private JButton button2;
    private JDialog dialog1;


    private int enterCount = 0;
    private boolean chooseFlag = false;


    public static final int screenWidth =
            (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final int screenHeight =
            (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();


    public HappyValentinsDay() {
        label = new JLabel("可以给我10块钱吗?", SwingConstants.CENTER);
        button1 = new JButton("不，不行!");
        button2 = new JButton("我给你100!");
        dialog1 = new JDialog(this);
        windowInitial();
        setWindowListener();
    }


    public HappyValentinsDay(String labelTxt, String bt1Txt, String bt2Txt) {
        label = new JLabel(labelTxt, SwingConstants.CENTER);
        button1 = new JButton(bt1Txt);
        button2 = new JButton(bt2Txt);
        dialog1 = new JDialog(this);
        windowInitial();
        chooseFlag = true;
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }


    private void windowInitial() {
        setDialog(dialog1, "太好了，就喜欢你这种!", "我的微信是abab，把钱打过来吧!"); // 自行修改


        label.setFont(new Font("", Font.BOLD, 17));
        label.setBounds(0, 30, 480, 20);

        button1.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                return;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                return;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                return;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                switch (enterCount) {
                    case 0:
                        button1.setBounds(75, 60, 110, 30);
                        HappyValentinsDay.this.repaint();
                        ++enterCount;
                        break;
                    case 1:
                        button1.setBounds(75, 110, 110, 30);
                        HappyValentinsDay.this.repaint();
                        ++enterCount;
                        break;
                    case 2:
                        button1.setBounds(155, 60, 110, 30);
                        HappyValentinsDay.this.repaint();
                        ++enterCount;
                        break;
                    case 3:
                        button1.setBounds(75, 110, 110, 30);
                        HappyValentinsDay.this.repaint();
                        ++enterCount;
                        break;
                    case 4:
                        button1.setBounds(310, 110, 110, 30);
                        button2.setBounds(75, 110, 110, 30);
                        HappyValentinsDay.this.repaint();
                        ++enterCount;
                        break;
                    case 5:
                        button1.setBounds(75, 110, 110, 30);
                        button2.setBounds(310, 110, 110, 30);
                        HappyValentinsDay.this.repaint();
                        enterCount = 0;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                dialog1.setVisible(true);
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });

        button1.setBounds(70, 110, 110, 30);
        button1.setFont(new Font("", Font.BOLD, 13));


        button2.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                return;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                return;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                return;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                return;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                dialog1.setVisible(true);
                chooseFlag = true;
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });
        button2.setBounds(310, 110, 110, 30);
        button2.setFont(new Font("", Font.BOLD, 13));


        Container c = getContentPane();
        c.setLayout(null);
        c.add(label);
        c.add(button1);
        c.add(button2);
        setTitle("好心人，帮帮我!");
        setBounds(screenWidth / 2 - 250, screenHeight / 2 - 100, 500, 200);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }


    private void setDialog(JDialog diag, String tittle, String txt) {
        if (chooseFlag) setDialog(new JDialog(), "谢谢", "感谢大佬!!!!");
        else {
            JLabel diagLabel = new JLabel(txt, SwingConstants.CENTER);
            diagLabel.setFont(new Font("", Font.BOLD, 17));
            diagLabel.setBounds(0, 40, 430, 20);
            JButton diagBut = new JButton("打钱！");
            diagBut.setFont(new Font("", Font.BOLD, 14));
            diagBut.setBounds(155, 100, 100, 30);
            diagBut.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    return;
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    return;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    return;
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    return;
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    diag.dispose();
                    button1.setVisible(false);
                    button2.setVisible(false);
                    setBounds(screenWidth / 2 - 150, screenHeight / 2 - 250, 500, 520);
                    label.setBounds(0, 0, 500, 480);
                    label.setFont(new Font("宋体", Font.PLAIN, 25));
                    label.setText("感谢！！！！！");
                    chooseFlag = true;
                    URL url = this.getClass().getResource("/images/img.jpg");
                    label.setIcon(new ImageIcon(url));
                    label.setHorizontalTextPosition(SwingConstants.CENTER);
                    label.setVerticalTextPosition(SwingConstants.BOTTOM);

                }
            });
            diag.setTitle(tittle);
            diag.setBounds(screenWidth / 2 - 225, screenHeight / 2 - 100, 450, 200);
            diag.setLayout(null);
            diag.add(diagBut);
            diag.add(diagLabel);
        }
    }


    private void setWindowListener() {
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                return;
            }

            @Override
            public void windowIconified(WindowEvent e) {
                return;
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                return;
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                return;
            }

            @Override
            public void windowClosed(WindowEvent e) {
                return;
            }

            @Override
            public void windowActivated(WindowEvent e) {
                return;
            }

            @Override
            public void windowClosing(WindowEvent e) {
                if (!chooseFlag) {
                    String labelTxt = "不会吧，不会吧，你居然想关闭！";
                    new HappyValentinsDay(labelTxt, "我就要关闭", "点错了");
                }
            }
        });
    }


    public static void main(String[] args) {
        HappyValentinsDay myApp = new HappyValentinsDay();
        myApp.setVisible(true);
    }


}