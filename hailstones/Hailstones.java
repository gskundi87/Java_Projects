import java.util.ArrayList;
import java.util.List;

public class Hailstones
{
    public static void main(String[] args)
    {
//        int n = 3;
//        while (n != 1) {
//            System.out.println(n);
//            if (n % 2 == 0) {
//                n = n / 2;
//            } else {
//                n = 3 * n + 1;
//            }
//        }
//        System.out.println(n);

        int[] a = new int[100];  // <==== DANGER WILL ROBINSON
        int i = 0;
        int n = 7;
        while (n != 1)
        {
            a[i] = n;
            i++;  // very common shorthand for i=i+1
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
        }

        a[i] = n;
        i++;

        List<Integer> list = new ArrayList<Integer>(hailstoneSequence(7));

        for(int j = 0; j < i; j++ )
        {
            System.out.print(a[j]);
            System.out.print(" ");
            System.out.print(list.get(j));
            System.out.println();
        }
    }

    public static List<Integer> hailstoneSequence(int n) {
        List<Integer> list = new ArrayList<Integer>();
        while (n != 1) {
            list.add(n);
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
        }
        list.add(n);
        return list;
    }
}
