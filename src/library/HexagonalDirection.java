package library;

import library.Exceptions.LibraryException;

public class HexagonalDirection {
    private int index;
    public HexagonalDirection(int index) {
        if (index < 0 || index > 5) {
            throw new LibraryException("HEXAGONAL DIRECTION MIGHT BE FROM 0 TO 5!!!!!!!!!!!!!!");
        }
        this.index = index;
    }
    public int getIndex() {
        return this.index;
    }
}
