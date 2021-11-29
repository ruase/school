import java.util.Scanner;

public class jisshu06 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("図形の大きさを入力：");
        int su1 = sc.nextInt();
        System.out.print("線の太さを入力：");
        int su2 = sc.nextInt();
        int mid_view = su1 - (su2 * 2);

        for (int i = 1; i <= su2; i++) {
            for (int l = 1; l <= su1; l++) {
                System.out.print("★");
            }
            System.out.println();
        }

        for (int i = 1; i <= mid_view; i++) {
            for (int l = 1; l <= su1; l++) {
                if (l <= su2 || l > su1 - su2) {
                    System.out.print("★");
                } else {
                    System.out.print("　");
                }
            }
            System.out.println();
        }

        for (int i = 1; i <= su2; i++) {
            for (int l = 1; l <= su1; l++) {
                System.out.print("★");
            }
            System.out.println();
        }
    }
}
