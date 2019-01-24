public class SuperRabbit extends Cell implements Birth, Death{

    private int age;
    private int energy;

    SuperRabbit(int age, int energy) {
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
        return true;
    }

    @Override
    public void birth() {
        Table.superRabbitList.add(new SuperRabbit(1,3));

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
}