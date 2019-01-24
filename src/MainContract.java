import javax.swing.*;
import java.util.List;

public interface MainContract {
    interface View {
        void showTable(Cell[][] matrix);
        //void showRabbitsInfo(List<Rabbit> rabbitList, List<SuperRabbit> superRabbitList);
        void showRabbitInfo(Rabbit rabbit);
        void setSelection(Position position, boolean selection);
        void updateCellItem(Position position, Cell Cell);
        //void rePaint();

        void highlightRange(Range range, Position center);
        void removeHighlight();
        //void showPanel1();
        //void showPanel2(String attack);
        //void attackAnimation();
        void setRabbitInfo(String info);
        //int getYearr(table.getYear);
        void eagleAttackAnimation(JPanel root);
        void rabbitInfo(Rabbit selectedCell);


    }

    interface Presenter {
        void onTableItemClicked(Position position);
        //void showAttackingEagle();
    }
}
