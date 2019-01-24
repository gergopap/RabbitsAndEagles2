import javax.swing.*;

public class Rabbit extends Cell implements Birth, Death {

    private int age;
    private int energy;
    public Icon icon;

    Rabbit(int age, int energy) {
        this.age = age;
        this.energy = energy;
        this.icon = new ImageIcon("nyuszi2.jpg");
    }


    public boolean isItRabbit() {
        return true;
    }

    public boolean isMovable() {
        return true;
    }


    @Override
    public int maxStep() {
        return 2;
    }

    @Override
    public boolean canMoveOutOfAxis() {
        return false;
    }

    @Override
    public void birth() {
        Table.rabbitList.add(new Rabbit(1, 5));
    }

    @Override
    public void death() {
        for (int i = 0; i < Table.rabbitList.size(); i++) {
            if (Table.rabbitList.get(i).getAge() == 10 || Table.rabbitList.get(i).getEnergy() == 0) {
                Table.rabbitList.remove(i);
                --i;
            }
        }
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


}