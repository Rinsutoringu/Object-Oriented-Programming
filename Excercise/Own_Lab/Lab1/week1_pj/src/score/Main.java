package score;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
        
        // 创建一个获取器，从键盘接受输入
		Scanner in = new Scanner(System.in);

        // 实例化Fraction类a，使用获取器in获取两个数据作为类的初始化参数
		Fraction a = new Fraction(in.nextInt(), in.nextInt());
        // int[] test = Fraction.simp_fraction(in.nextInt(), in.nextInt());
        // System.out.println(Arrays.toString(test));

        // 实例化Fraction类b，使用获取器in获取两个数据作为类的初始化参数
        Fraction b = new Fraction(in.nextInt(),in.nextInt());

		a.print();
		b.print();
		a.plus(b).print();
		a.multiply(b).plus(new Fraction(5,6)).print();
		a.print();
		b.print();
		in.close();
        
	}

}

class Fraction {
    int a = 0;
    int b = 0;

    public Fraction(int a, int b){
        if (b == 0) {
            throw new IllegalArgumentException("分母不能为零");
        }
        int[] frac = simp_fraction(a,b);
        this.a = frac[0];
        this.b = frac[1];
    }

    // 将分数转换为double
    public double toDouble(){
        return (double) a / b;
    }

    // 将自己的分数和r的分数相加，产生一个新的Fraction的对象。
    public Fraction plus(Fraction r){
        // 分数相加 取得分母的最小公倍数，两个分数分子分母同时乘最小公倍数再相加然后化简
        int[] fracplus = fracplus(this, r);
        return new Fraction(fracplus[0], fracplus[1]);
    }

    // 将自己的分数和r的分数相乘，产生一个新的Fraction的对象。
    public Fraction multiply(Fraction r){
        int[] frac = simp_fraction((r.a * this.a), (r.b * this.b));
        return new Fraction(frac[0], frac[1]);
    }
   
    // 将自己以“分子/分母”的形式输出到标准输出，并带有回车换行。
    public void print(){
        if (this.a == this.b) {
            System.out.printf("%d\n", this.b);
        }
        else{
            System.out.printf("%d/%d\n", this.a, this.b);
        }
    }

    // 分数化简
    private static int[] simp_fraction(int a,int b){
        
        if (b == 0) {
            throw new IllegalArgumentException("分母不能为零");
        }

        int gcd = gcd(a, b);
        return new int[]{a / gcd, b / gcd};
    }

    // 两个分数相加
    private static int[] fracplus(Fraction a, Fraction b){
        // System.out.printf("%d, %d, %d, %d", a.a, a.b, b.a, b.b);
        int lcm = lcm(a.b,b.b);
        return simp_fraction((a.a * (lcm / a.b)) + (b.a * (lcm / b.b)), lcm);
    }
    /***
     * 求最大公因数
     * @param int a
     * @param int b
     * @return 最大公因数
     */
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    /***
     * 求最小公倍数
     * @param int a
     * @param int b
     * @return 最小公倍数
     */
    private static int lcm(int a, int b){
        
        if (a == 0 || b == 0) {
            throw new IllegalArgumentException("参数不能为零");
        }

        int i = 0;
        if (a>b) i = a;
        else i = b;
        for (;i<=a*b; i++) {
            if (i%a == 0 && i%b == 0) {
                return i;
            }
        }
        return 0;
    }

}
