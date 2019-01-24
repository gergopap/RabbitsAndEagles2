import javax.swing.*;
import java.util.List;

public class Eagle implements Death, Attack {

    private int age;
    private int energy;

    public Eagle(int age, int energy) {
        this.age = age;
        this.energy = energy;
    }


    @Override
    public void death() {
        for (int i = 0; i < Table.eagleList.size(); ++i) {
            if (Table.eagleList.get(i).getAge() == 10 || Table.eagleList.get(i).getEnergy() == 0) {
                Table.eagleList.remove(i);
                --i;
            }
        }
    }

    @Override
    public Rabbit attack() {

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

