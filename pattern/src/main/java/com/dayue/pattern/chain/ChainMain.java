package com.dayue.pattern.chain;

import java.util.function.Function;

/**
 * @author Fiuty
 */
public class ChainMain {
    public static void main(String[] args) {
        AddHeaderHandler addHeaderHandler = new AddHeaderHandler();
        CheckSpellHandler checkSpellHandler = new CheckSpellHandler();
        AddFooterHandler addFooterHandler = new AddFooterHandler();
        addHeaderHandler.setSuccessor(checkSpellHandler);
        checkSpellHandler.setSuccessor(addFooterHandler);
        String test = addHeaderHandler.handle("labda");
        System.out.println(test);

        Function<String, String> addHeaderHandler1 = LambdaHandler.addHeaderHandler();
        Function<String, String> checkSpellHandler1 = LambdaHandler.checkSpellHandler();
        Function<String, String> addFooterHandler1 = LambdaHandler.addFooterHandler();
        String test1 = addHeaderHandler1.andThen(checkSpellHandler1).andThen(addFooterHandler1).apply("labda");
        System.out.println(test1);
    }
}
