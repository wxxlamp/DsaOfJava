/**
 * Cubic maximum contiguous subsequence sum algorithm
 * @version 1.0 2019-03-22
 * @author Stonee(http://www.stonee.club)
 */
package Algorithm;
import java.util.Scanner;
import java.lang.Math;

public class maxSubSum {
    public static void main(String[] args){
        int [] a = new int[10];

        Scanner cin = new Scanner(System.in);
        System.out.println("Please input 10 numbers:");

        for(int i = 0; i < 10; i++)
            a[i] = cin.nextInt();

        System.out.printf("The maximum1 is %d \n ",maxSubSum1( a ));
        System.out.println("The maxSubSum is " + maxSubSum2( a ));
        System.out.println("The maxSubSum is " + maxSubSum3( a,0, a.length-1));
        System.out.println("The maxSubSum is " + maxSubSum4( a ));

    }

    // maxSubSum1计算了每个字段和（PS：甚至还多重复计算了好多），时间复杂度为o(n^3)
    private static int maxSubSum1(int [] a){

        int maxSum = 0;

        for (int i = 0; i < a.length; i++)  //起点

            for (int j = i ; j < a.length; j++){    //终点

                int thisSum = 0;

                for (int k = i; k <= j; k++)    //起点终点之间来回跑,但是当起点不变时会有重复
                    thisSum += a[k];

                if (thisSum > maxSum)
                    maxSum = thisSum;
            }

        return maxSum;
    }

    // maxSubSum2计算了每个字段和（PS：没有重复计算，但计算了每一个），时间复杂度为o(n^2)
    private static int maxSubSum2(int [] a){
        int maxSum = 0;

        for (int i = 0; i < a.length; i++) {

            int thisSum = 0;

            for (int j = i; j < a.length; j++) {

                thisSum += a[j];

                if (thisSum > maxSum)
                    maxSum = thisSum;
            }
        }

        return maxSum;
    }

    // maxSubSum3通过递归的形式，比较三种条件：左边、右边和中间，时间复杂度为o(nlogn)
    private static int maxSubSum3(int [] a, int left, int right){

        int center = (left + right) / 2, leftBorderSum = 0, rightBorderSum = 0, maxLeftBorderSum = 0, maxRightBorderSum = 0;
        int maxLeftSum, maxRightSum;

        if (left == right)  // 递归终止条件
            if (a[left] > 0)
                return a[left];
            else 
                return 0;
//            return (a[left] > 0) ? a[left] : 0; // >0的话就取，<0的话就舍弃，即换成0代替

        // 找出左右两边最大值
        maxLeftSum = maxSubSum3(a, left, center);
        maxRightSum = maxSubSum3(a,center + 1, right);

        // 下面的两个循环是找出中间最大值
        for( int i = center; i >= left; i--){
            leftBorderSum += a[i];
            if (leftBorderSum > maxLeftBorderSum)
                maxLeftBorderSum = leftBorderSum;
        }

        for (int i = center; i <= right; i++){
            rightBorderSum += a[i];
            if (rightBorderSum > maxRightBorderSum)
                maxRightBorderSum = rightBorderSum;
        }

        //比较并返回
        return Math.max( maxLeftBorderSum + maxRightBorderSum, Math.max ( maxRightSum, maxLeftSum ) );
    }

    //maxSubSum只遍历一次，时间复杂度为O(n)
    private static int maxSubSum4(int [] a){

        int maxSum = 0, thisSum = 0;

        for (int i = 0; i < a.length; i++){

            thisSum += a[i];

            if(thisSum > maxSum)
                maxSum = thisSum;
            else if (thisSum < 0)       //把<0的变为0并且跳过
                thisSum = 0;
        }

        return maxSum;
    }
}

