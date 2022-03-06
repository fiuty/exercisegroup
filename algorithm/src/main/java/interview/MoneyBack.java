package interview;

import java.util.HashMap;

/**
 * 找零钱，二面笔试题
 * @author zhengdayue
 */
public class MoneyBack {

    public static void main(String[] args) {
        int[] num = {5, 5, 5, 10, 20};
        int[] num2 = {5, 5, 10, 10, 20};
        int[] num3 = {5, 5, 10};
        int[] num4 = {10, 10};
        System.out.println(compute(num));
        System.out.println(compute(num2));
        System.out.println(compute(num3));
        System.out.println(compute(num4));
    }

    public static boolean compute(int[] num) {
        //现有的存钱，默认都为0
        HashMap<Integer, Integer> map = new HashMap<>(8);
        for (int i = 0; i < num.length; i++) {
            //返回的钱
            int backMoney = num[i] - 5;
            //如果不需要返回，5块钱，那么存起来
            if (backMoney == 0) {
                map.merge(5, 1, Integer::sum);
            } else {
                //需要返回,有两种情况，一种是返回5块钱，一种是返回15块钱（10+5/5+5+5）
                if (backMoney == 5) {
                    Integer currentBackNum = map.get(5);
                    if (currentBackNum == null || currentBackNum <= 0) {
                        return false;
                    } else {
                        //可以返回的话，存钱的5减去1操作
                        map.merge(5, -1, Integer::sum);
                        //同时把现在的面额存进来
                        map.merge(num[i], 1, Integer::sum);
                    }
                } else {
                    //返回15块钱，有两种情况（10+5/5+5+5）
                    Integer fiveNums = map.get(5);
                    Integer tenNums = map.get(10);
                    if ((tenNums >= 1 && fiveNums >= 1) || (fiveNums >= 5)) {
                        map.merge(num[i], 1, Integer::sum);
                        if (tenNums >= 1 && fiveNums >= 1) {
                            map.merge(10, -1, Integer::sum);
                            map.merge(5, -1, Integer::sum);
                        } else {
                            map.merge(5, -3, Integer::sum);
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
