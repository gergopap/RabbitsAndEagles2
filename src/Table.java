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


    public Table() {
        //rabbitListFiller();
        eagleListFiller();
        //superRabbitListFiller();
        generateGrass(20);
        generateBush(20);
        generateRabbits();
        generateSuperRabbits();
        fillMap();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                prevMatrix[i][j] = new Cell(i, j);
            }

        }
    }

/*

    private void rabbitListFiller() {

        for (int i = 0; i < 5; i++) {
            Rabbit rabbit = new Rabbit(1, 5);
            rabbitList.add(rabbit);
        }
    }*/


    private void eagleListFiller() {
        for (int i = 0; i < 5; i++) {
            Eagle eagle = new Eagle(1, 5);
            eagleList.add(eagle);
        }
    }
/*
    private void superRabbitListFiller() {
        for (int i = 0; i < 5; i++) {
            SuperRabbit superRabbit = new SuperRabbit(1, 3);
            superRabbitList.add(superRabbit);
        }
    }
*/


    private void generateGrass(int grassPiece) {
        int index1;
        int index2;

        for (int i = 0; i < grassPiece; i++) {
            do {
                index1 = (int) (Math.random() * 8);
                index2 = (int) (Math.random() * 8);

            } while (matrix[index1][index2] != null);
            Grass grass = new Grass(index1, index2);
            matrix[index1][index2] = grass;
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
            Bush bush = new Bush(index1, index2);
            matrix[index1][index2] = bush;
        }
    }

    private void generateRabbits() {
        int index1;
        int index2;

        for (int i = 0; i < 5; i++) {
            do {
                index1 = (int) (Math.random() * 8);
                index2 = (int) (Math.random() * 8);

            } while (matrix[index1][index2] != null);
            Rabbit rabbit = new Rabbit(index1, index2, 1, 5);
            rabbitList.add(rabbit);
            matrix[index1][index2] = rabbit;
        }
    }

    private void generateSuperRabbits() {
        int index1;
        int index2;

        for (int i = 0; i < 5; i++) {
            do {
                index1 = (int) (Math.random() * 8);
                index2 = (int) (Math.random() * 8);

            } while (matrix[index1][index2] != null);
            SuperRabbit supeRabbit = new SuperRabbit(index1, index2, 1, 5);
            superRabbitList.add(supeRabbit);
            matrix[index1][index2] = supeRabbit;

            //matrix[index1][index2] = new SuperRabbit(1,3);
        }
    }

    private void fillMap() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == null) {
                    matrix[i][j] = new Cell(i, j);
                }
            }
        }
    }

    public void updateTable() {

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

    public void increaseEnergy (Cell CellFrom) {
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

    public void hide (Cell CellTo) {
        //String inBush = ((Rabbit) CellFrom).isInBush();
        String rb = "R/B";

        //System.out.println(prevMatrix[to.x][to.y].toString());
    }

    public void moveCellItem(Position from, Position to) {
        Cell CellFrom = matrix[from.x][from.y];
        Cell CellTo = matrix[to.x][to.y];

        if (CellFrom == null) return;
        if (CellTo instanceof Grass) {
            increaseEnergy(CellFrom);
        }
        if (CellTo instanceof Bush) {
            hide(CellTo);
        }

        matrix[from.x][from.y] = prevMatrix[from.x][from.y];
        prevMatrix[to.x][to.y] = CellTo;
        matrix[to.x][to.y] = CellFrom;

    }

    public void removeRabbit(Rabbit rabbit) {
        if(rabbit != null) {
            matrix[rabbit.x][rabbit.y] = new DeadRabbit(rabbit.x, rabbit.y,0,0);
        }
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
