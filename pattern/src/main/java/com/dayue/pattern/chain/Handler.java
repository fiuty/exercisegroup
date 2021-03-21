package com.dayue.pattern.chain;

/**
 * @author Fiuty
 */
public abstract class Handler {

    protected Handler successor;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    abstract protected String handleWork(String input);

    public String handle(String input) {
        //本节点处理完之后返回下一节点
        String nextNode = handleWork(input);
        if (successor != null) {
            return successor.handle(nextNode);
        }
        return nextNode;
    }
}
