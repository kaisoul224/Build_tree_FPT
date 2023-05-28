package BSTDemo;

/**
 *
 * @author NguyenQuocAnh CE170483
 */
public class BSTRange {
    private int leftIndex;
    private int rightIndex;

    public BSTRange(int leftIndex, int rightIndex) {
        this.leftIndex = leftIndex;
        this.rightIndex = rightIndex;
    }

    public int getLeftIndex() {
        return leftIndex;
    }

    public void setLeftIndex(int leftIndex) {
        this.leftIndex = leftIndex;
    }

    public int getRightIndex() {
        return rightIndex;
    }

    public void setRightIndex(int rightIndex) {
        this.rightIndex = rightIndex;
    }
    
    
}
