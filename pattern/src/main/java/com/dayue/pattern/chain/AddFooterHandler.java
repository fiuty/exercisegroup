package com.dayue.pattern.chain;

/**
 * 短文头部处理器
 * @author Fiuty
 */
public class AddFooterHandler extends Handler{
    @Override
    protected String handleWork(String input) {
        return  input + " Kind regards";
    }
}
