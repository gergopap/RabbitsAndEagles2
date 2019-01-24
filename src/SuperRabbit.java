public class SuperRabbit extends Cell implements Death{

    private int age;
    private int energy;

    SuperRabbit(int x, int y, int age, int energy) {
        super(x, y);
        this.age = age;
        this.energy = energy;
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
    public void death() {
        for (int i = 0; i < Table.superRabbitList.size(); ++i) {
            if (Table.superRabbitList.get(i).getAge() == 10 || Table.superRabbitList.get(i).getEnergy() == 0) {
                Table.superRabbitList.remove(i);
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

    public String superRabbitInfo() { return "Rabbit:" + " "
            + "Age:" + " " + getAge() + "\n\r"
            + "Energy:" + " " + getEnergy() + "\n\r";
    }

}