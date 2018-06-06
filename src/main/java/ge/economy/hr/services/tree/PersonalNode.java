package ge.economy.hr.services.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nl
 */
public class PersonalNode {

    private int id;
    private String text;// tree label
    private NodeState state;
    private String type;
    private List<PersonalNode> children = new ArrayList<PersonalNode>();

    public void addState(boolean selected, boolean opened) {
        NodeState nodeState = new NodeState();
        nodeState.setSelected(selected);
        nodeState.setOpened(opened);
        setState(nodeState);
    }

    public NodeState getState() {
        return state;
    }

    public void setState(NodeState state) {
        this.state = state;
    }

    public List<PersonalNode> getChildren() {
        return children;
    }

    public void setChildren(List<PersonalNode> children) {
        this.children = children;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
