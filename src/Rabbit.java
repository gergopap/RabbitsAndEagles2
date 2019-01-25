import javax.swing.*;

public class Rabbit extends Cell {

    protected int age;
    protected int energy;
    protected boolean inBush;

    Rabbit(int x, int y, int age, int energy) {
        super(x, y);
        this.age = age;
        this.energy = energy;
        this.inBush = false;
    }


    public boolean isItRabbit() {
        return true;
    }

    public boolean isMovable() {
        return true;
    }

    public boolean isInBush () { return inBush;}

    public void setInBush(boolean inBush) {
        this.inBush = inBush;
    }


    @Override
    public int maxStep() {
        return 2;
    }

    @Override
    public boolean canMoveOutOfAxis() {
        return false;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }


    public String rabbitInfo() { return "Rabbit:" + "\n\r" +" "
            + "Age:" + " " + getAge() + "\n\r"
            + "Energy:" + " " + getEnergy() + "\n\r";
    }

}