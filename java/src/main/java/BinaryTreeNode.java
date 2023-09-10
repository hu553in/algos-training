public class BinaryTreeNode<T> {
    private final T value;
    private BinaryTreeNode<T> parent;
    private BinaryTreeNode<T> leftChild;
    private BinaryTreeNode<T> rightChild;

    public BinaryTreeNode(T value) {
        this.value = value;
    }

    public BinaryTreeNode<T> getParent() {
        return parent;
    }

    private void setParent(BinaryTreeNode<T> parent) {
        this.parent = parent;
    }

    public void removeParent() {
        parent = null;
    }

    public BinaryTreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode<T> leftChild) {
        this.leftChild = leftChild;
        if (leftChild != null) leftChild.setParent(this);
    }

    public BinaryTreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTreeNode<T> rightChild) {
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
