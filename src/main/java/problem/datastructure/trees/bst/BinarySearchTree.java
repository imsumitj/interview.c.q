package problem.datastructure.trees.bst;

import lombok.NonNull;
import lombok.ToString;

import java.util.Optional;

/**
 * Binary search tree implementation.
 *
 * @param <T>
 *     Type of object that will be stored in the tree.
 */
@ToString
public class BinarySearchTree<T extends Comparable<T>> {

    @NonNull
    private final BinaryTreeNodes<T> root;


    BinarySearchTree(final T data) {
        root = new BinaryTreeNodes<T>(data);
    }

    /**
     * Add new node with the data passed as input.
     *
     * @param data
     *     of Type <T>
     *
     * @return BinarySearchTree
     */
    public BinarySearchTree<T> add(@NonNull final T data) {
        add(data, root);
        return this;
    }

    /**
     * Checks if the tree contains the data
     *
     * @param data
     *     of Type <T>
     *
     * @return False if data is null or if it is not present in the tree
     */
    public boolean contains(final T data) {
        return data != null && findNode(data, root).isPresent();
    }

    /**
     * Returns GraphViz representation of the tree.
     *
     * @return GraphViz
     */
    public String toGraphViz() {
        return "digraph g {\n" +
            "node [shape = Mrecord,height=.1];\n" +
            root.toGraphviz() +
            "}";
    }

    // recursively searches through the tree
    private Optional<BinaryTreeNodes<T>> findNode(@NonNull final T data, final BinaryTreeNodes<T> currentNode) {
        if (currentNode == null) {
            return Optional.empty();
        }

        if (currentNode.getData().equals(data)) {
            return Optional.of(currentNode);
        } else if (shouldGoLeft(data, currentNode)) {
            return findNode(data, currentNode.getLeftNode());
        } else {
            return findNode(data, currentNode.getRightNode());
        }
    }

    // Recursive call
    private void add(final T data, final BinaryTreeNodes<T> currentNode) {
        if (shouldGoRight(data, currentNode)) { // root node is greater than the new node.
            if (currentNode.getRightNode() != null) {
                add(data, currentNode.getRightNode());
            } else {
                final BinaryTreeNodes<T> newNode = new BinaryTreeNodes<>(data);
                currentNode.setRightNode(newNode);
            }
        } else if (shouldGoLeft(data, currentNode)) {
            if (currentNode.getLeftNode() != null) {
                add(data, currentNode.getLeftNode());
            } else {
                final BinaryTreeNodes<T> newNode = new BinaryTreeNodes<>(data);
                currentNode.setLeftNode(newNode);
            }
        } else {
            System.out.println("ignoring as node already exists with data " + data);
        }
    }

    private boolean shouldGoRight(final T newNode, final BinaryTreeNodes<T> currentNode) {
        return currentNode.getData().compareTo(newNode) < 0;
    }

    private boolean shouldGoLeft(final T newNode, final BinaryTreeNodes<T> currentNode) {
        return currentNode.getData().compareTo(newNode) > 0;
    }
}
