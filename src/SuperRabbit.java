public class SuperRabbit extends Rabbit {

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

    public String superRabbitInfo() { return "Super Rabbit:" + " "
            + "Age:" + " " + getAge() + "\n\r"
            + "Energy:" + " " + getEnergy() + "\n\r";
    }

}