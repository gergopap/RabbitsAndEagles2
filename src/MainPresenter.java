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
                        int energy = ((Rabbit) selectedItem).getEnergy();
                        ((Rabbit) selectedItem).setInBush(selectedItem2 instanceof Bush);
                        ((Rabbit) selectedItem).setEnergy(energy - 1);
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
        //int posX = rabbit.x;
        //int posY = rabbit.y;
        //Position position = new Position(posX, posY);
        if (rabbit != null) {

            //view.setAttackIcon(position);
            table.removeRabbit(rabbit);
        }

        Rabbit rabbit2 = death();
        table.removeRabbit(rabbit2);
        SuperRabbit superRabbit = deathSr();
        table.removeRabbit(superRabbit);

        //view.eagleAttackAnimation(root);

        System.out.println(attackingEagle);

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

        gameOver();

    }

    public Rabbit death() {
        for (int i = 0; i < Table.rabbitList.size(); i++) {
            if (Table.rabbitList.get(i).getAge() == 10 || Table.rabbitList.get(i).getEnergy() == 0) {
                Rabbit rabbit = Table.rabbitList.get(i);
                Table.rabbitList.remove(rabbit);
                --i;
                System.out.println(Table.rabbitList);
                return rabbit;
            }
        }
        return null;
    }

    public SuperRabbit deathSr() {
        for (int i = 0; i < Table.superRabbitList.size(); i++) {
            if (Table.superRabbitList.get(i).getAge() == 10 || Table.superRabbitList.get(i).getEnergy() == 0) {
                SuperRabbit superR = Table.superRabbitList.get(i);
                Table.superRabbitList.remove(superR);
                --i;
                System.out.println(Table.superRabbitList);
                return superR;
            }
        }
        return null;
    }

    private void gameOver() {
        if (Table.rabbitList.size() == 0 && Table.superRabbitList.size() == 0) {
            view.gameOverMessege();
        }
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


}

