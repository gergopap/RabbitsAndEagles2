import javax.swing.*;
import java.util.List;
import java.util.Random;

public class Eagle implements Attack {

    private int age;
    private int energy;

    public Eagle(int age, int energy) {
        this.age = age;
        this.energy = energy;
    }


    @Override
    public Rabbit attack() {

        if (Math.random() < 0.5) {
            if (Table.rabbitList.size() > 0) {

                int i = (int) (Math.random() * Table.rabbitList.size() - 1);
                Rabbit rabbit = Table.rabbitList.get(i);
                if (rabbit.getAge() < 150 && !rabbit.isInBush()) {
                    energy += 2;

                    Table.rabbitList.remove(i);

                    System.out.println(Table.rabbitList);
                    return rabbit;
                } else {
                    return null;

                }
            } else {
                return null;
            }
        } else {
            if (Table.superRabbitList.size() > 0) {

                int i = (int) (Math.random() * Table.superRabbitList.size() - 1);
                SuperRabbit superRabbit = Table.superRabbitList.get(i);
                if (superRabbit.getAge() < 150 && !superRabbit.isInBush()) {
                    energy += 2;

                    Table.superRabbitList.remove(i);

                    System.out.println(Table.superRabbitList);
                    return superRabbit;
                } else {
                    return null;

                }
            } else {
                return null;
            }
        }
    }

    public int getAge() {
        return age;
    }

    public int getEnergy() {
        return energy;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}

