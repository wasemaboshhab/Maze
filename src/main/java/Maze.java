
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Stack;

public class Maze extends JFrame {

    private int[][] values;
    private boolean[][] visited;
    private int startRow;
    private int startColumn;
    private ArrayList<JButton> buttonList;
    private int rows;
    private int columns;
    private boolean backtracking;
    private int algorithm;

    public Maze(int algorithm, int size, int startRow, int startColumn) {
        this.algorithm = algorithm;
        Random random = new Random();
        this.values = new int[size][];
        for (int i = 0; i < values.length; i++) {
            int[] row = new int[size];
            for (int j = 0; j < row.length; j++) {
                if (i > 1 || j > 1) {
                    row[j] = random.nextInt(8) % 7 == 0 ? Definitions.OBSTACLE : Definitions.EMPTY;
                } else {
                    row[j] = Definitions.EMPTY;
                }
            }
            values[i] = row;
        }
        values[0][0] = Definitions.EMPTY;
        values[size - 1][size - 1] = Definitions.EMPTY;
        this.visited = new boolean[this.values.length][this.values.length];
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.buttonList = new ArrayList<>();
        this.rows = values.length;
        this.columns = values.length;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setLocationRelativeTo(null);
        GridLayout gridLayout = new GridLayout(rows, columns);
        this.setLayout(gridLayout);
        for (int i = 0; i < rows * columns; i++) {
            int value = values[i / rows][i % columns];
            JButton jButton = new JButton(String.valueOf(i));
            if (value == Definitions.OBSTACLE) {
                jButton.setBackground(Color.BLACK);
            } else {
                jButton.setBackground(Color.WHITE);
            }
            this.buttonList.add(jButton);
            this.add(jButton);
        }
        this.setVisible(true);
        this.setSize(Definitions.WINDOW_WIDTH, Definitions.WINDOW_HEIGHT);
        this.setResizable(false);
    }

    public void checkWayOut() {
        new Thread(() -> {
            boolean result = false;
            switch (this.algorithm) {
                case Definitions.ALGORITHM_DFS:

//                    runRecursive(new Node(0, 0));

                    result = traverse(0, 0);


//                    Node startNode = new Node(0, 0);
//                    HashSet<Node> nodesVisited = new HashSet<>();
//
//                    Stack<Node> stack = new Stack<>();
//                    stack.add(startNode);
//
//                    while (!stack.isEmpty()) {
//                        Node currentNode = stack.pop();
//                        if (!visited[currentNode.getX()][currentNode.getY()]) {
//                            visited[currentNode.getX()][currentNode.getY()] = true;
//                            setSquareAsVisited(currentNode.getX(), currentNode.getY(), true);
//                            nextMoves(currentNode);
//                            if (!visited[currentNode.getX()][currentNode.getY()]) {
//                                stack.add(currentNode);
//                            }
//                        }
//                    }


//                    (currentNode.isNodeVisited() ? nodesVisited.add(currentNode) : currentNode = neighbors.pop());










//                    Point start = new Point(rows, columns);
//
//                    for (int i = 0; i < rows; i++) {
//                        for (int j = 0; j < columns; j++) {
//                            if (buttonList.get(j).getBackground().equals(Color.white)) {
//                                setSquareAsVisited(j, i, true);
//                            } else {
//                                setSquareAsVisited(j, i + 1, true);
//
//                            }
//
//
//                        }
//                    }


                    break;
                case Definitions.ALGORITHM_BFS:
                    break;
            }
            JOptionPane.showMessageDialog(null,  result ? "FOUND SOLUTION" : "NO SOLUTION FOR THIS MAZE");

        }).start();
    }

    public boolean isValidSpot(int row, int column) {
        boolean validSpot = row >= 0 && row < rows && column >= 0 && column < columns && !visited[row][column];
        if (validSpot) {
            return values[row][column] == 0;
        }
        return false;
    }

    public boolean traverse(int row, int column) {
        if (isValidSpot(row , column)) {
            // it is a valid spot
            setSquareAsVisited(row, column, true);
            visited[row][column] = true;
            if (row == rows - 1 && column == columns - 1) {
                // we solve the maze so return true to the result variable to show (maze solved)
                return true;
            }
//            continue checking
            // down/deep
            boolean returnValue = traverse(row +1, column );

            // right
            if (!returnValue) {returnValue = traverse(row , column +1);}

            // left
            if (!returnValue) {returnValue = traverse(row , column -1);}

            //up/back
            if (!returnValue) {returnValue = traverse(row -1, column );}
            return returnValue;
        }

        return false;

    }


    public void runRecursive(Node node) {
        if (!visited[node.getX()][node.getY()]) {
            visited[node.getX()][node.getY()] = true;
            setSquareAsVisited(node.getX(), node.getY(), visited[node.getX()][node.getY()]);
            node.setX(node.getY() + 1);
            if (!visited[node.getX()][node.getY()]) {
                runRecursive(node);
            }
        }
    }

    private void nextMoves(Node currentNode) {
//        boolean allowToMoveDown = currentNode.getY() + 1 <= rows && !visited[currentNode.getX()][(currentNode.getY() + 1)];
//        boolean allowToMoveRight = currentNode.getX() + 1 <= columns && !visited[(int) currentNode.getX() + 1][currentNode.getY()];
//        boolean allowToMoveLeft = currentNode.getX() - 1 >= 0 && !visited[(currentNode.getX() - 1)][currentNode.getY()];
//        boolean allowToMoveUp = currentNode.getY() - 1 >= 0 && !visited[currentNode.getX()][(currentNode.getY() - 1)];
//
//        if (allowToMoveDown) {
//            currentNode.setY( (currentNode.getY() + 1));
//        } else if (allowToMoveRight) {
//            currentNode.setX( (currentNode.getX() + 1));
//        } else if (allowToMoveLeft) {
//            currentNode.setX((currentNode.getX() - 1));
//        } else if (allowToMoveUp) {
//            currentNode.setY((currentNode.getY() - 1));
//        }

        // go down if u can
        if (currentNode.getX() + 1 < values.length && buttonList.get(currentNode.getX()+1).getBackground().equals(Color.WHITE)) {
            currentNode.setX(currentNode.getX() + 1);
//            moveRight
        }
//        else if ((currentNode.getY() + 1 < values.length && buttonList.get(currentNode.getY() + 1).getBackground().equals(Color.WHITE))) {
//            currentNode.setX(currentNode.getY() + 1);
//        }


    }


    public void setSquareAsVisited(int x, int y, boolean visited) {
        try {
            if (visited) {
                if (this.backtracking) {
                    Thread.sleep(Definitions.PAUSE_BEFORE_NEXT_SQUARE * 5);
                    this.backtracking = false;
                }
                this.visited[x][y] = true;
                for (int i = 0; i < this.visited.length; i++) {
                    for (int j = 0; j < this.visited[i].length; j++) {
                        if (this.visited[i][j]) {
                            if (i == x && y == j) {
                                this.buttonList.get(i * this.rows + j).setBackground(Color.RED);
                            } else {
                                this.buttonList.get(i * this.rows + j).setBackground(Color.BLUE);
                            }
                        }
                    }
                }
            } else {
                this.visited[x][y] = false;
                this.buttonList.get(x * this.columns + y).setBackground(Color.WHITE);
                Thread.sleep(Definitions.PAUSE_BEFORE_BACKTRACK);
                this.backtracking = true;
            }
            if (!visited) {
                Thread.sleep(Definitions.PAUSE_BEFORE_NEXT_SQUARE / 4);
            } else {
                Thread.sleep(Definitions.PAUSE_BEFORE_NEXT_SQUARE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
