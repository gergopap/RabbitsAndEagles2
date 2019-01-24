import java.util.List;

public interface MainContract {
    interface View {
        void showTable(Cell[][] matrix);
        void showRabbitsInfo(List<Rabbit> rabbitList, List<SuperRabbit> superRabbitList);
        void setSelection(Position position, boolean selection);
        void updateCellItem(Position position, Cell Cell);
        //void rePaint();

        void highlightRange(Range range, Position center);
        void removeHighlight();
        //void attackAnimation();
        void setRabbitInfo(String info);
        //int getYearr(table.getYear);


    }

    interface Presenter {
        void onTableItemClicked(Position position);
    }
}
