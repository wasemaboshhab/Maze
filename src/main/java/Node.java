import java.awt.*;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Node   {
    private int x;
    private int y;
    private boolean nodeVisited;
//    Stack<Node> neighbors;
//    HashSet<Node> visited;


    public Node(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    //    public boolean isNodeVisited() {
//        return this.nodeVisited;
//    }
            //getters


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setNodeVisited(boolean nodeVisited) {
        this.nodeVisited = nodeVisited;
    }

    public boolean isNodeVisited() {
        return nodeVisited;
    }

    public Point findNextStep(int border) {

        int nextX = 0;
        int nextY = 0;
    // go deep
        if (this.y++ < border) {

        }
        return new Point(nextX, nextY);

    }

    public void checkIfAnExit(int x, int y) {

    }
    public boolean checkIfDirectionValid(int x, int y , int border) {
        return x < 0 || border < x && y < 0 || border < y;

    }



    public void move() {
        if (checkIfDirectionValid(this.x, this.y, 9)) {
            moveDown();
        }

    }

    public void moveDown() {
        this.y++;
    }

    public void goBack() {
        this.y--;
    }

    public void goRight() {
        this.x--;
    }

    public void goLeft() {
        this.x++;
    }



}
