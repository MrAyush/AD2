package DynamicProgrammingPack.FibonacciPack;

class Fibonacci {
    static int fib(int pos) {
        int[] f = new int[pos + 2];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i <= pos; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[pos];
    }
}

public class NthFibonacci {
    public static void main(String[] args) {
        int pos = 5;
        int n = Fibonacci.fib(pos);
        System.out.println("Fibonacci number at position " + pos + " : " + n);
        System.out.println("-----------------------------------------------");
        pos = 7;
        n = Fibonacci.fib(pos);
        System.out.println("Fibonacci number at position " + pos + " : " + n);
        System.out.println("-----------------------------------------------");
        pos = 2;
        n = Fibonacci.fib(pos);
        System.out.println("Fibonacci number at position " + pos + " : " + n);
        System.out.println("-----------------------------------------------");
        pos = 10;
        n = Fibonacci.fib(pos);
        System.out.println("Fibonacci number at position " + pos + " : " + n);
    }
}
