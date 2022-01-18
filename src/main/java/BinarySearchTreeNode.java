public class BinarySearchTreeNode<T> {
    private BinarySearchTreeNode<T> parent;
    private BinarySearchTreeNode<T> leftChild;
    private BinarySearchTreeNode<T> rightChild;
    private final T value;

    public BinarySearchTreeNode(T value) {
        this.value = value;
    }

    public BinarySearchTreeNode<T> getParent() {
        return parent;
    }

    private void setParent(BinarySearchTreeNode<T> parent) {
        this.parent = parent;
    }

    public void removeParent() {
        parent = null;
    }

    public BinarySearchTreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinarySearchTreeNode<T> leftChild) {
        this.leftChild = leftChild;
        if (leftChild != null) leftChild.setParent(this);
    }

    public BinarySearchTreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinarySearchTreeNode<T> rightChild) {
        this.rightChild = rightChild;
        if (rightChild != null) rightChild.setParent(this);
    }

    public T getValue() {
        return value;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public boolean hasLeftChild() {
        return leftChild != null;
    }

    public boolean hasRightChild() {
        return rightChild != null;
    }
}
