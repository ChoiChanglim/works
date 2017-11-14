import java.text.DecimalFormat;

import com.nhn.common.util.NumberUtil;



public class RandomTest {

    public static void main(String[] args) {
        double abc = 0.01;
        //boolean isInProbability = isInProbability(abc);

        for(int i=0;i<10000;i++){
            boolean isInProbability = isInProbability(abc);
            if(isInProbability == true){
                System.err.println((i+1)+"번째에 성공");
                break;
            }
        }
        //System.err.println("cnt:"+cnt);
    }


    /**
     * 단일 확률 계산 소수점2자리 까지 지원
     * @param percent
     * @return
     */
    public static boolean isInProbability(double percent){
        DecimalFormat dformat = new DecimalFormat(".##");
        String tmp = dformat.format(percent);
        String[] dots = tmp.split("[.]");
        if(dots.length < 2){
            throw new RuntimeException();
        }
        String decimalPoint = dots[1];
        int scale = (int)(Math.pow(10, (decimalPoint.length())));

        //System.err.println("decimalPoint:"+decimalPoint+", scale:"+scale);
        double b = Double.valueOf(tmp);

        int range = (int)(b * scale);
        int max = 100 * scale;


        int selected = NumberUtil.randomRange(1, max);
        //System.err.println("max:"+max+", range:"+range+", selected:"+selected);
        if(selected <= range){
            return true;
        }


        return false;
    }

}
