import sun.awt.RepaintArea;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;
import java.util.Objects;


public class Main extends JFrame implements MainContract.View {

    private ActionListener actionListener;

    public static void main(String... args) {
        EventQueue.invokeLater(() -> {
            Main main = new Main();
            main.setVisible(true);
        });
    }

    private MainContract.Presenter presenter;

    private JPanel layoutButtons;
    private JPanel layoutButtons2;
    private JTextArea rabbitInfo;
    private JLabel gameInfo;
    private JLabel eaglePanel;


    public Main() {
        setTitle("Nyulak és Sasok");
        setSize(810, 750);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Color backColor = new Color(108812);

        JPanel root = new JPanel();

        root.setLayout(null);
        add(root);

        layoutButtons = new JPanel();
        layoutButtons.setBounds(15, 225, 465, 465);
        layoutButtons.setBackground(backColor);
        layoutButtons.setLayout(null);
        root.add(layoutButtons);

        /*layoutButtons2 = new JPanel();
        layoutButtons2.setBounds(0, 0, 410, 410);
        layoutButtons2.setBackground(Color.RED);
        layoutButtons2.setLayout(null);*/

        //root.add(layoutButtons2);

        rabbitInfo = new JTextArea();
        rabbitInfo.setBounds(495, 400, 300, 100);
        rabbitInfo.setBackground(Color.gray);
        rabbitInfo.setFont(new Font("Arial", Font.BOLD, 17));
        rabbitInfo.setLayout(new GridLayout(2, 3));
        root.add(rabbitInfo);
        rabbitInfo.setVisible(true);
        //rabbitInfo.setText();

        /*gameInfo = new JLabel();
        gameInfo.setBounds(490,5,300,100);
        gameInfo.setFont(new Font("Serif",Font.PLAIN, 20));
        gameInfo.setForeground(Color.yellow);
        gameInfo.setText(getYearr(),". év nyulak száma: " + Table.rabbitList.size() + "<br/>sasok száma: " + Eagle.eagleNumber + "</html>");
        gameInfo.setVisible(true);
        root.add(info2);*/

        eaglePanel = new JLabel();
        eaglePanel.setBounds(15, 5, 465, 200);
        eaglePanel.setIcon(new ImageIcon("sas.gif"));
        root.add(eaglePanel);
        eaglePanel.setVisible(true);

        //eagleAttackAnimation(root);


        actionListener = e -> {
            String[] s = e.getActionCommand().split(" ");

            int x = Integer.valueOf(s[0]);
            int y = Integer.valueOf(s[1]);

            presenter.onTableItemClicked(new Position(x, y));
        };

        presenter = new MainPresenter(this);
    }

    @Override
    public void showTable(Cell[][] matrix) {
        layoutButtons.removeAll();


        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {


                JButton btn = new JButton();

                btn.setActionCommand(i + " " + j);
                btn.addActionListener(actionListener);
                btn.setBackground(null);
                btn.setBorder(BorderFactory.createEmptyBorder());
                btn.setBorder(BorderFactory.createLineBorder(Color.white));
                btn.setBounds(15 + j * 55,
                        15 + i * 55,
                        50,
                        50);
                layoutButtons.add(btn);
                System.out.println(btn.getText());


                /*if (btns[i][j].getText().charAt(0) == 'B') {
                    Icon b =new ImageIcon("nyuszi2.jpg");
                    btns[i][j].setIcon(b);
                }*/


                btn.setOpaque(true);

                //System.out.println(matrix.toString());

                Cell cell = matrix[i][j];
                if (cell != null) {
                    //btn.setText(cell.toString());
                    if (cell instanceof DeadRabbit) {
                        Icon f = new ImageIcon("death.gif");
                        btn.setIcon(f);
                    } else if (cell instanceof SuperRabbit) {
                        Icon a = new ImageIcon("szny.jpg");
                        btn.setIcon(a);
                    } else if (cell instanceof Rabbit) {
                        Icon b = new ImageIcon("nyuszi2.jpg");
                        btn.setIcon(b);
                    } else if (cell instanceof Grass) {
                        Icon c = new ImageIcon("kaja.jpg");
                        btn.setIcon(c);
                    } else if (cell instanceof Bush) {
                        Icon d = new ImageIcon("bokor.jpg");
                        btn.setIcon(d);
                    } else {
                        Icon e = new ImageIcon("alap.jpg");
                        btn.setIcon(e);
                    }
                }
            }
        }
    }

    @Override
    public void rabbitInfo(Rabbit selectedCell) {
        //gameInfo.setText(selectedCell.rabbitInfo());

    }

    @Override
    public void setAttackIcon(Position position) {
        JButton btn = (JButton) layoutButtons.getComponent(position.x * 8 + position.y);
        Icon i = new ImageIcon("sas.gif");
        btn.setIcon(i);
        repaint();
    }

    public static void gameOverBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "" + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void gameOverMessege() {
        gameOverBox("You LOSE!!!", "Game Over");
    }


    /*public void eagleAttackAnimation(JPanel root) {
        eaglePanel = new JLabel();
        eaglePanel.setBounds(15, 5, 465, 200);
        eaglePanel.setIcon(new ImageIcon("szny.jpg"));
        root.add(eaglePanel);
        eaglePanel.setVisible(true);
    }*/

    @Override
    public void showRabbitInfo(Rabbit rabbit) {

        rabbitInfo.removeAll();


        String info = rabbit.toString() + " " + ":   Energy: " + rabbit.getEnergy() + ",   Age: " + rabbit.getAge();
        String info2 = "Hiding :    " + rabbit.isInBush();
        rabbitInfo.add(new Label(info));
        rabbitInfo.add(new Label(info2));

        rabbitInfo.revalidate();
    }
/*
    public void showRabbitsInfo(List<Rabbit> rabbitList, List<SuperRabbit> superRabbitList) {

        rabbitInfo.removeAll();

        for (int j = 0; j < rabbitList.size(); ++j) {
            String info = Table.rabbitList.get(j).toString() + " " + (j + 1) + " :     Energy: " + Table.rabbitList.get(j).getEnergy() + ",     Age: " + Table.rabbitList.get(j).getAge();

            rabbitInfo.add(new Label(info));
        }
        for (int j = 0; j < superRabbitList.size(); ++j) {
            String info = Table.superRabbitList.get(j).toString() + " " + (j + 1) + " :  Energy: " + Table.superRabbitList.get(j).getEnergy() + ",     Age: " + Table.superRabbitList.get(j).getAge();

            rabbitInfo.add(new Label(info));
        }
    }*/

    @Override
    public void setSelection(Position position, boolean selection) {
        Component component = layoutButtons.getComponent(position.x * 8 + position.y);

        component.setBackground(selection ? Color.YELLOW : null);
    }

    @Override
    public void updateCellItem(Position position, Cell CellItem) {
        JButton btn = (JButton) layoutButtons.getComponent(position.x * 8 + position.y);

        layoutButtons.repaint();
        rabbitInfo.revalidate();
    }


    @Override
    public void highlightRange(Range range, Position center) {
        for (int i = range.topLeft.x; i <= range.bottomRight.x; i++) {
            for (int j = range.topLeft.y; j <= range.bottomRight.y; j++) {
                if (center == null ||
                        center.x == i || center.y == j) {
                    int index = i * 8 + j;
                    ((JButton) layoutButtons.getComponent(index))
                            .setBorder(BorderFactory.createLineBorder(Color.yellow));
                }
            }
        }
    }

    @Override
    public void removeHighlight() {
        for (int i = 0; i < layoutButtons.getComponentCount(); i++) {
            ((JButton) layoutButtons.getComponent(i))
                    .setBorder(BorderFactory.createLineBorder(Color.darkGray));


        }
    }

    @Override
    public void setRabbitInfo(String info) {
        rabbitInfo.setText(info);
    }


    /*@Override
    public int getYearr() {
        return year;
    }*/

}