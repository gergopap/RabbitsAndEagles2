import javax.swing.*;
import java.util.UUID;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private Table table;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        table = new Table(1);

        view.showTable(table.getTable());
        //view.getYearr(table.getYear());
        //view.showRabbitsInfo(Table.rabbitList, Table.superRabbitList);


    }

    @Override
    public void onTableItemClicked(Position position) {
        Cell cellItem = table.getCell(position);

        Position selectedPosition = table.getSelectedPosition();
        if (cellItem != null) {
            view.removeHighlight();
            if (changeItemSelection(position, selectedPosition)) {
                highlightItemRange(position, cellItem);
                if (cellItem instanceof Rabbit) {
                    view.showRabbitInfo((Rabbit) table.getCell(position));

                    view.rabbitInfo((Rabbit) cellItem);
                }
            }


            if (selectedPosition != null) {
                Cell selectedItem = table.getCell(selectedPosition);
                Cell selectedItem2 = table.getCell(position);
                if (selectedItem.isMovable() && !(selectedItem2 instanceof Rabbit || selectedItem2 instanceof SuperRabbit) &&
                        table.isValidStep(selectedPosition, position)) {
                    if (selectedItem instanceof Rabbit) {
                        ((Rabbit) selectedItem).setInBush(selectedItem2 instanceof Bush);
                        ((Rabbit) selectedItem).setX(selectedItem2.x);
                        ((Rabbit) selectedItem).setY(selectedItem2.y);
                    }
                    moveItem(position, selectedPosition);
                    nextRound();
                }

            }
        }
    }


    private void nextRound() {
        Position selectedPosition = table.getSelectedPosition();
        if (selectedPosition != null) {
            table.deselectItem();
            view.setSelection(selectedPosition, false);
        }

        int i = (int) (Math.random() * Table.eagleList.size() - 1);

        Eagle attackingEagle = Table.eagleList.get(i);
        int energy = attackingEagle.getEnergy();
        attackingEagle.setEnergy(energy - 1);

        Rabbit rabbit = attackingEagle.attack();
        if (rabbit != null) {
            table.removeRabbit(rabbit);
        }


        //view.eagleAttackAnimation(root);

        System.out.println(attackingEagle); //Eagle.attack()

        for (int j = 0; j < Table.eagleList.size(); j++) {
            int age = Table.eagleList.get(j).getAge();
            Table.eagleList.get(j).setAge(age + 1);
        }

        for (int j = 0; j < Table.rabbitList.size(); j++) {
            int age = Table.rabbitList.get(j).getAge();
            Table.rabbitList.get(j).setAge(age + 1);
        }

        for (int j = 0; j < Table.superRabbitList.size(); j++) {
            int age = Table.superRabbitList.get(j).getAge();
            Table.superRabbitList.get(j).setAge(age + 1);
        }

        view.showTable(table.getTable());

        //int year = table.getYear();
        //table.setYear(year + 1);
        //rabbitsturn()
        //showAttackingEagle();


    }


    private void moveItem(Position position, Position selectedPosition) {
        table.moveCellItem(selectedPosition, position);
        table.selectItem(position);

        view.setSelection(selectedPosition, false);

        view.updateCellItem(selectedPosition, table.getCell(selectedPosition));
        view.updateCellItem(position, table.getCell(position));

        view.setSelection(position, true);

        view.removeHighlight();
    }


    private boolean changeItemSelection(Position position, Position selectedPosition) {
        table.selectItem(position);
        view.setSelection(position, true);

        if (selectedPosition != null) {
            view.setSelection(selectedPosition, false);

            if (selectedPosition.equals(position)) {
                table.deselectItem();
                return false;
            }
        }

        return true;
    }

    private void highlightItemRange(Position itemPosition, Cell item) {
        view.removeHighlight();

        Position p1 = new Position(
                Math.max(0, itemPosition.x - item.maxStep()),
                Math.max(0, itemPosition.y - item.maxStep()));
        Position p2 = new Position(
                Math.min(7, itemPosition.x + item.maxStep()),
                Math.min(7, itemPosition.y + item.maxStep()));

        Range range = new Range(p1, p2);
        view.highlightRange(range, item.canMoveOutOfAxis() ? null : itemPosition);
    }


    private int ui = 0;

    /*public void showAttackingEagle() {
        if (ui == 0) {
            view.showPanel2(UUID.randomUUID().toString());
        } else {
            view.showPanel1();
        }

        ui = (ui + 1) % 2;
    }*/


}

