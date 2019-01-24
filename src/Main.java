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

        layoutButtons2 = new JPanel();
        layoutButtons2.setBounds(0, 0, 410, 410);
        layoutButtons2.setBackground(Color.RED);
        layoutButtons2.setLayout(null);

        root.add(layoutButtons2);

        rabbitInfo = new JTextArea();
        rabbitInfo.setBounds(495, 225, 300, 465);
        rabbitInfo.setBackground(Color.gray);
        rabbitInfo.setFont(new Font("Arial", Font.BOLD, 15));
        rabbitInfo.setLayout(new GridLayout(10, 3));
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

        eagleAttackAnimation(root);


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
                btn.setBorderPainted(true);
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
                    btn.setText(cell.toString());
                    if (cell instanceof SuperRabbit) {
                        Icon a = new ImageIcon("szny.jpg");
                        btn.setIcon(a);
                    } else if (cell instanceof Rabbit) {
                        Icon b = new ImageIcon("nyuszi2.jpg");
                        btn.setIcon(b);
                    }

                    if (cell instanceof Grass) {
                        Icon c = new ImageIcon("kaja.jpg");
                        btn.setIcon(c);
                    }

                    if (cell instanceof Bush) {
                        Icon d = new ImageIcon("bokor.jpg");
                        btn.setIcon(d);
                    }

                    if (btn.getText().equals(" ")) {
                        Icon e = new ImageIcon("alap.jpg");
                        btn.setIcon(e);
                    }
                    //if (Cell instanceof Rabbit) {
                    //   btn.setIcon(new ImageIcon("nyuszi2.jpg"));
                    //}
                }
            }
        }
    }

    @Override
    public void rabbitInfo(Rabbit selectedCell) {
        //gameInfo.setText(selectedCell.rabbitInfo());

    }


    public void eagleAttackAnimation(JPanel root) {
        eaglePanel = new JLabel();
        eaglePanel.setBounds(15, 5, 465, 200);
        eaglePanel.setIcon(new ImageIcon("szny.jpg"));
        root.add(eaglePanel);
        eaglePanel.setVisible(true);
    }

    public void setIcons(JButton btn) {
        if (btn.getText().equals("Super R.")) {
            Icon a = new ImageIcon("szny.jpg");
            btn.setIcon(a);
        }

        if (btn.getText().equals("Rabbit")) {
            Icon b = new ImageIcon("nyuszi2.jpg");
            btn.setIcon(b);
        }

        if (btn.getText().equals("G")) {
            Icon c = new ImageIcon("kaja.jpg");
            btn.setIcon(c);
        }

        if (btn.getText().equals("B")) {
            Icon d = new ImageIcon("bokor.jpg");
            btn.setIcon(d);
        }

        if (btn.getText().equals(" ")) {
            Icon e = new ImageIcon("alap.jpg");
            btn.setIcon(e);
        }
    }

    @Override
    public void showRabbitInfo(Rabbit rabbit) {

        rabbitInfo.removeAll();

        String info = rabbit.toString() + " "  + " :     Energy: " + rabbit.getEnergy() + ",     Age: " + rabbit.getAge();

        rabbitInfo.add(new Label(info));

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

        btn.setText(CellItem != null ? CellItem.toString() : null);

        layoutButtons.repaint();
        rabbitInfo.revalidate();

        //setIcons(btn);
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

    /*@Override
    public void showPanel1() {
        layoutButtons.setVisible(true);
        layoutButtons2.setVisible(false);
    }

    @Override
    public void showPanel2(String attack) {
        layoutButtons.setVisible(false);
        layoutButtons2.setVisible(true);

        ((Label) layoutButtons2.getComponent(0)).setText(attack);
    }*/

    @Override
    public void setRabbitInfo(String info) {
        rabbitInfo.setText(info);
    }


    /*@Override
    public int getYearr() {
        return year;
    }*/

}