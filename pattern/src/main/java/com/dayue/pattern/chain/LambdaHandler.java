package com.dayue.pattern.chain;

import java.util.function.Function;

/**
 * @author Fiuty
 */
public class LambdaHandler {

    public static Function<String, String> addHeaderHandler() {
        return (input) -> "From Raoul, Mario and Alan: " + input;
    }

    public static Function<String, String> checkSpellHandler() {
        return (input) -> input.replaceAll("labda", "lambda");
    }

    public static Function<String, String> addFooterHandler() {
        return (input) -> input + " Kind regards";
    }
}
