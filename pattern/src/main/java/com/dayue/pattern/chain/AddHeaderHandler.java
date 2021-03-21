package com.dayue.pattern.chain;

/**
 * 短文尾部处理器
 * @author Fiuty
 */
public class AddHeaderHandler extends Handler {
    @Override
    protected String handleWork(String input) {
        return  "From Raoul, Mario and Alan: " + input;
    }
}
