public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private Table table;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        table = new Table(1);

        view.showTable(table.getTable());
        //view.getYearr(table.getYear());
        view.showRabbitsInfo(Table.rabbitList, Table.superRabbitList);


    }

    @Override
    public void onTableItemClicked(Position position) {
        Cell cellItem = table.getCell(position);

        Position selectedPosition = table.getSelectedPosition();
        if (cellItem != null) {
            view.removeHighlight();
            if (changeItemSelection(position, selectedPosition)) {
                highlightItemRange(position, cellItem);
            }

            if (selectedPosition != null) {
                Cell selectedItem = table.getCell(selectedPosition);
                if (selectedItem.isMovable() &&
                        table.isValidStep(selectedPosition, position)) {
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

        int i = (int) (Math.random() * Table.eagleList.size()-1);

        Eagle attackingEagle = Table.eagleList.get(i);
        int energy = attackingEagle.getEnergy();
        attackingEagle.setEnergy(energy - 1);

        attackingEagle.attack();

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
        //int year = table.getYear();
        //table.setYear(year + 1);
        //rabbitsturn()


    }
        

    private void moveItem(Position position, Position selectedPosition) {
        table.moveCellItem(selectedPosition, position);
        table.selectItem(position);

        view.setSelection(selectedPosition, false);

        view.updateCellItem(selectedPosition, table.getCell(selectedPosition) );
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

