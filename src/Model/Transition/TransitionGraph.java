package Model.Transition;

import Model.Entity;

public class TransitionGraph extends TransitionNode{

    TransitionNode rootNode;
    TransitionNode currentNode;

    public TransitionGraph(TransitionNode rootNode){
        this.rootNode = rootNode;
        this.currentNode = rootNode;
    }

    public void setCurrentNode(TransitionNode currentNode){
        this.currentNode = currentNode;
    }

    @Override
    public TransitionNode apply(Entity entity) {
        if(this.currentNode != null) {
            TransitionNode newNode = this.currentNode.apply(entity);
            while (newNode != this.currentNode){
                this.currentNode = newNode;
                newNode = this.currentNode.apply(entity);
            }
        }
        return this.currentNode;
    }

}
