public class Grass extends Cell {

    public Grass(int x, int y) {
        super(x, y);
    }

    public boolean isItGrass() {
        return true;
    }

    public boolean isMovable() {
        return false;
    }

    public int maxStep() {
        return 0;
    }

    public boolean canMoveOutOfAxis() {
        return false;
    }
}