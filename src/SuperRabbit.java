public class SuperRabbit extends Rabbit implements Death{

    SuperRabbit(int x, int y, int age, int energy) {
        super(x, y, age, energy);
    }


    public boolean isItSuperRabbit() {
        return true;
    }

    public boolean isMovable() {
        return true;
    }

    @Override
    public int maxStep() {
        return 5;
    }

    @Override
    public boolean canMoveOutOfAxis() {
        return false;
    }



    @Override
    public Rabbit death() {
        for (int i = 0; i < Table.superRabbitList.size(); ++i) {
            if (Table.superRabbitList.get(i).getAge() == 10 || Table.superRabbitList.get(i).getEnergy() == 0) {
                Rabbit rabbit = Table.superRabbitList.get(i);
                Table.superRabbitList.remove(rabbit);
                --i;
                return rabbit;
            }
        }
        return null;
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

    public String superRabbitInfo() { return "Rabbit:" + " "
            + "Age:" + " " + getAge() + "\n\r"
            + "Energy:" + " " + getEnergy() + "\n\r";
    }

}