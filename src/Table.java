import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Table {

    private Cell[][] matrix = new Cell[8][8];

    private Cell[][] prevMatrix = new Cell[8][8];

    private Position selectedPosition;

    //private int year;

    static List<Rabbit> rabbitList = new ArrayList<>();
    static List<Eagle> eagleList = new ArrayList<>();
    static List<SuperRabbit> superRabbitList = new ArrayList<>();


    public Table(int year) {
        //this.year = year;
        rabbitListFiller();
        eagleListFiller();
        superRabbitListFiller();
        generateGrass(20);
        generateBush(20);
        generateRabbits();
        generateSuperRabbits();
        fillMap();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0;j < matrix[i].length; j++) {
                prevMatrix[i][j] = new Cell();
            }

        }
    }


    private void rabbitListFiller() {

        for (int i = 0; i < 5; i++) {
            Rabbit rabbit = new Rabbit(1, 5);
            rabbitList.add(rabbit);
        }
    }


    private void eagleListFiller() {
        for (int i = 0; i < 5; i++) {
            Eagle eagle = new Eagle(1, 5);
            eagleList.add(eagle);
        }
    }

    private void superRabbitListFiller() {
        for (int i = 0; i < 5; i++) {
            SuperRabbit superRabbit = new SuperRabbit(1, 3);
            superRabbitList.add(superRabbit);
        }
    }


    private void generateGrass(int grassPiece) {
        int index1;
        int index2;

        for (int i = 0; i < grassPiece; i++) {
            do {
                index1 = (int) (Math.random() * 8);
                index2 = (int) (Math.random() * 8);

            } while (matrix[index1][index2] != null);
            matrix[index1][index2] = new Grass();
        }
    }

    private void generateBush(int bushPiece) {
        int index1;
        int index2;

        for (int i = 0; i < bushPiece; i++) {
            do {
                index1 = (int) (Math.random() * 8);
                index2 = (int) (Math.random() * 8);

            } while (matrix[index1][index2] != null);
            matrix[index1][index2] = new Bush();
        }
    }

    private void generateRabbits() {
        int index1;
        int index2;

        for (int i = 0; i < rabbitList.size(); i++) {
            do {
                index1 = (int) (Math.random() * 8);
                index2 = (int) (Math.random() * 8);

            } while (matrix[index1][index2] != null);
            matrix[index1][index2] = rabbitList.get(i);
        }
    }

    private void generateSuperRabbits() {
        int index1;
        int index2;

        for (int i = 0; i < superRabbitList.size(); i++) {
            do {
                index1 = (int) (Math.random() * 8);
                index2 = (int) (Math.random() * 8);

            } while (matrix[index1][index2] != null);
            matrix[index1][index2] = superRabbitList.get(i);

            //matrix[index1][index2] = new SuperRabbit(1,3);
        }
    }

    private void fillMap() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == null) {
                    matrix[i][j] = new Cell();
                }
            }
        }
    }

    public void selectItem(Position position) {
        selectedPosition = position;
    }

    public void deselectItem() {
        selectedPosition = null;
    }

    public Position getSelectedPosition() {
        return selectedPosition;
    }


    public Cell[][] getTable() {
        return matrix;
    }

    public Cell getCell(Position position) {
        return matrix[position.x][position.y];
    }


    public boolean isValidStep(Position from, Position to) {
        Cell c = getCell(from);

        System.out.println("ds");
        return c != null &&
                (from.x == to.x || from.y == to.y) &&
                Math.abs(from.x - to.x) <= c.maxStep() &&
                Math.abs(from.y - to.y) <= c.maxStep();// &&
        //matrix[to.x][to.y] instanceof Rabbit;
    }

    public void moveCellItem(Position from, Position to) {
        Cell CellFrom = matrix[from.x][from.y];
        Cell CellTo = matrix[to.x][to.y];

        if (CellFrom != null) {
            if (CellTo instanceof Grass) {
                if (CellFrom instanceof Rabbit) {
                    int energy = ((Rabbit) CellFrom).getEnergy();
                    ((Rabbit) CellFrom).setEnergy(energy + 1);
                    System.out.println(((Rabbit) CellFrom).getEnergy());
                } else {
                    int energy = ((SuperRabbit) CellFrom).getEnergy();
                    ((SuperRabbit) CellFrom).setEnergy(energy + 1);
                    System.out.println(((SuperRabbit) CellFrom).getEnergy());
                }
            }
            if (CellTo instanceof Bush) {
                //String inBush = ((Rabbit) CellFrom).isInBush();
                String rb = "R/B";

                System.out.println(prevMatrix[to.x][to.y].toString());
            }

            matrix[from.x][from.y] = prevMatrix[from.x][from.y];
            prevMatrix[to.x][to.y] = CellTo;
            matrix[to.x][to.y] = CellFrom;
        }
    }

    public String rabbitStepToBush () {
        String rb = "R/B";
        return rb;
    }

    @Override
    public String toString() {
        String cellValue = "";
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                cellValue += ' ' + matrix[i][j].toString();
            }
            cellValue += "\n";
        }
        return cellValue;
    }

    /*public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }*/
}
