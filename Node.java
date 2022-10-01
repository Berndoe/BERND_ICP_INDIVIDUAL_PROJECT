import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Node {
    private String nodeState;
    private String airlineCode;
    private Node parent;
    int numberOfStops;

    /**
     * This instantiates a node
     * @param nodeState the state of the node
     * @param airlineCode the airline code
     * @param parent the node parent
     * @param stopNumber the number of stops
     */
    public Node(String nodeState, String airlineCode, int stopNumber, Node parent) {
        this.nodeState = nodeState;
        this.airlineCode = airlineCode;
        this.parent = parent;
        this.numberOfStops = stopNumber;
    }

    // this represents the node without a parent
    public Node(String nodeState) {
        this.nodeState = nodeState;
        this.parent = null;
        this.airlineCode = "";
        this.numberOfStops = 0;
    }

    /**
     * This function returns the node state
     *
     * @return The nodeState variable is being returned.
     */
    public String getNodeState() {
        return nodeState;
    }

    /**
     * This function returns the airline code of the flight
     *
     * @return The airline code.
     */
    public String getAirlineCode() {
        return airlineCode;
    }

    /**
     * Returns the parent of this node.
     *
     * @return The parent node of the current node.
     */
    public Node getParent() {
        return parent;
    }

    /**
     * This function returns the number of stops the flight takes if any
     *
     * @return The stop number.
     */
    public int getNumberOfStops() {
        return numberOfStops;
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeState='" + nodeState + '\'' +
                ", airlineCode='" + airlineCode + '\'' +
                ", parent=" + parent +
                ", numberOfStops=" + numberOfStops +
                '}';
    }

    @Override
    // This function compares two node objects.
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return numberOfStops == node.numberOfStops && Objects.equals(nodeState, node.nodeState)
                && Objects.equals(airlineCode, node.airlineCode) && Objects.equals(parent, node.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeState, airlineCode, parent, numberOfStops);
    }

    public ArrayList<String> flightPath() {
        ArrayList<String> pathList = new ArrayList<>();
        String path = "";

        Node currentNode = this;

        // getting the flight paths
        while(currentNode.parent != null) {
            path = currentNode.getAirlineCode() + " from " +
                   currentNode.getParent().getNodeState() + " to " +
                   currentNode.getNodeState() + " " + currentNode.numberOfStops + " stops";

            pathList.add(path);
            currentNode = currentNode.parent;
        }
        Collections.reverse(pathList);

        // writing to the output file
        try {
            FileWriter flightPathWriter = new FileWriter("trial-input_output.txt");
            PrintWriter writer = new PrintWriter(flightPathWriter);

            for (String item: pathList) {
                writer.println(item);
            }
            writer.println("Total flights: " + " " + pathList.size());
            writer.println("Total additional stops: " + " " + numberOfStops);
            writer.close();
            }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return pathList;
    }
}
