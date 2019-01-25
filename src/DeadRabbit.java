public class DeadRabbit extends Rabbit{
    DeadRabbit(int x, int y, int age, int energy) {
        super(x, y, age, energy);
    }

    public boolean isMovable() {
        return false;
    }

}
