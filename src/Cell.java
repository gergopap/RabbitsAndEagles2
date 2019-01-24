public class Cell {

    public boolean isItRabbit() {
        return false;
    }

    public boolean isItGrass() {
        return false;
    }

    public boolean isItBush() {
        return false;
    }

    public boolean isItSuperRabbit() {
        return false;
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

    @Override
    public String toString() {
        if (isItRabbit()) {
            return "Rabbit";
        } else if (isItSuperRabbit()) {
            return "Super R.";
        } else if (isItGrass()) {
            return "G";
        } else if (isItBush()) {
            return "B";
        } else if ((isItRabbit() || isItSuperRabbit()) && isItBush()) {
            return "R/B";
        } else {
            return " ";
        }
    }
}