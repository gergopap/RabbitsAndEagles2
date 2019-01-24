public class Bush extends Cell {
    public Bush(int x, int y) {
        super(x, y);
    }

    public boolean isItBush() {
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

    public void ramLeptek() {

    }
}

