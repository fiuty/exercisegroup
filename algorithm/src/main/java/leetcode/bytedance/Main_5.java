package leetcode.bytedance;

import java.math.BigDecimal;

/**
 * @author zhengdayue
 */
public class Main_5 {

    public static void main(String[] args) {
        System.out.println(reverse(-123));
    }

    public static int reverse(int x) {
        Boolean neg = true;
        if (x > 0) {
            neg = false;
        }
        String num = x+"";
        boolean first = true;
        StringBuilder real = new StringBuilder();
        for(int i=num.length()-1;i>0;--i){
            if((num.charAt(i)+"").equals("0") && first){
            }else{
                first = false;
                real.append(num.charAt(i));
            }
        }
        if((num.charAt(0)+"").equals("-")){
            real.insert(0, "-");
        }else{
            real.append(num.charAt(0));
        }
        BigDecimal bigDecimal = new BigDecimal(real.toString());
        if (neg) {
            if (bigDecimal.subtract(BigDecimal.valueOf(-Math.pow(2, 31))).compareTo(BigDecimal.ZERO) < 0) {
                return 0;
            }
        } else {
            if (bigDecimal.subtract(BigDecimal.valueOf((Math.pow(2, 31) - 1d))).compareTo(BigDecimal.ZERO) > 0) {
                return 0;
            }
        }
        return Integer.parseInt(real.toString());
    }
}
