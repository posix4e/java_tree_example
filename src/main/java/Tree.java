import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class Tree<T extends Comparable> {
  Node<T> head;


  public void insert(T data) {
    insert(data, head);
  }

  public boolean isSearchTree() {
    return checkMinMax(head, null, null);
  }

  boolean checkMinMax(Node<T> node, T min, T max) {
    if (node == null) {
      return true;
    }

    if ((min != null  && (node.data.compareTo(min) <= 0))
        || (max != null && (node.data.compareTo(max)) > 0)) {
      return false;
    }

     if ((!(checkMinMax(node.left, min, node.data ))) ||
        (! checkMinMax(node.right, node.data, max))){
        return false;
      }

    return true;
  }

  void insert(T data, Node<T> compareToMe) {
    if (compareToMe == null) { // Should only happen in the head
      head = new Node<T>(data, null);
    } else {
      if (data.compareTo(compareToMe.data) <= 0) {
        if (compareToMe.left != null) {
          insert(data, compareToMe.left);
        } else {
          compareToMe.left = new Node<T>(data, compareToMe);
        }
      } else {
        if (compareToMe.right != null) {
          insert(data, compareToMe.right);
        } else {
          compareToMe.right = new Node<T>(data, compareToMe);
        }
      }
    }
  }

  @Override
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    Queue<Node<T>> q = new ArrayDeque<Node<T>>();
    q.add(head);
    while (!q.isEmpty()) {
      Node<T> top = q.remove();
      ;

      stringBuffer.append("|" + top + "|");
      if (top.right != null) {
        q.add(top.right);
      }
      if (top.left != null) {
        q.add(top.left);
      }

    }
    return stringBuffer.toString();
  }


  class Node<T extends Comparable> {
    public Node(T data, Node<T> parent) {
      this.data = data;
      this.parent = parent;
    }


    T data;
    Node<T> left;
    Node<T> right;
    Node<T> parent;

    @Override
    public String toString() {
      return String.valueOf(data);
    }
  }

  public static void main(String args[]) {

    Random random = new Random();
    Tree<Integer> tree = new Tree<Integer>();
    for (int i = 0; i != 10; i++) {
      int data = random.nextInt();
      System.out.println("data:" + data);
      tree.insert(data);
    }
    System.out.println(tree);
    System.out.println(tree.isSearchTree());
  }
};



