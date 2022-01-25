import java.util.Arrays;
import java.util.Scanner;

public class Jisshu13_fix {
    public static Scanner sc = new Scanner(System.in);
    public static int[] hBan = {101, 102, 103, 104, 105, 106, 107, 108, 201, 202, 0};
    public static String[] hMei = {"　食パン　　　", "　あんぱん　　", "　ジャムパン　", "　カレーパン ", "　クロワッサン",
            "　メロンパン　", "　野菜サンド　", "　カツサンド　", "　コーヒー　　", "　紅茶　　　　"};
    public static int[] hTan = {250, 90, 130, 150, 110, 200, 350, 400, 250, 250};
    public static int[] sSum = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static int[] sCnt = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static int[] kSum = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static int[] kCnt = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static int Total_Sum = 0, Total_Cnt = 0, K_Total_Sum = 0, K_Total_Cnt = 0, K_Total_WARI = 0, flg = 0, S_CODE = 0;

    public static void main(String[] args) {
        S_CODE = first_IN_Code();
        while (S_CODE != 0 || flg == 2) {
            if (S_CODE < 0) {
                S_CODE = S_CODE * -1;
                flg = 1;
            }
            int i = 0;
            while (hBan[i] != S_CODE && i <= 9) {
                i++;
            }
            if (i <= 9 && flg == 0) {
                System.out.printf("%s\t\t\t\t%3d円\n", hMei[i], hTan[i]);
                int cnt = 1;
                add_info(i, hTan[i], cnt);
            } else if (flg == 1 && sCnt[i] > 0) {
                System.out.printf("%s\t\t\t\t%s\n", hMei[i], "取消");
                int cnt = -1;
                int price = hTan[i] * -1;
                add_info(i, price, cnt);
            } else if (flg == 1 && sCnt[i] <= 0) {
                System.out.printf("%s\t\t\t\t購入数量が０です。\n", hMei[i]);
            } else if(flg != 2){
                System.out.println("　入力番号に誤りがあります。");
            }
            if (Total_Cnt == 0) {
                S_CODE = first_IN_Code();
            }else{
                S_CODE = IN_Code();
                if(S_CODE == 0){
                    view_buy();
                    flg = 2;
                }
            }
        }
        view_all_buy();
    }

    public static int IN_Code() {
        System.out.print("数値を入力（０：購入一覧を表示）：");
        S_CODE = sc.nextInt();
        flg = 0;
        return S_CODE;
    }

    public static int first_IN_Code() {
        System.out.print("数値を入力（０：売上一覧を表示）：");
        S_CODE = sc.nextInt();
        flg = 0;
        return S_CODE;
    }

    public static void add_info(int i, int price, int cnt) {
        sSum[i] += price;
        sCnt[i] += cnt;
        kSum[i] += price;
        kCnt[i] += cnt;
        Total_Cnt += cnt;
        Total_Sum += price;
    }

    public static void view_buy() {
        System.out.println("\n購入一覧");
        for (int i = 0; i <= 9; i++) {
            if (sCnt[i] != 0) {
                System.out.printf("%s\t\t%2d個\t\t%,5d円\n", hMei[i], sCnt[i], sSum[i]);
            }
        }
        discount_buy();
        System.out.printf("　　合　計　\t\t%2d個\t\t%,5d円\n\n", Total_Cnt, Total_Sum);
        K_Total_Sum += Total_Sum;
        K_Total_Cnt += Total_Cnt;
        reset();
    }

    public static void discount_buy() {
        int B_Cnt = 0, B_Sum = 0, N_Cnt = 0;
        for (int i = 0; i <= 7; i++) {
            B_Sum += sSum[i];
            B_Cnt += sCnt[i];
        }
        for (int i = 8; i <= 9; i++) {
            N_Cnt += sCnt[i];
        }
        if (B_Cnt >= 10) {
            double WARI = B_Sum * -0.1;
            System.out.printf("　　10個割　\t\t\t\t\t%,5d円\n", (int) WARI);
            dis_calc(WARI);
        } else if (N_Cnt != 0) {
            int WARI = 0;
            while (N_Cnt != 0 && B_Cnt != 0) {
                WARI -= 30;
                N_Cnt--;
                B_Cnt--;
            }
            System.out.printf("　　セット割　\t\t%2d組\t\t%,5d円\n",(WARI/30)*-1, WARI);
            dis_calc(WARI);
        }
    }

    public static void dis_calc(double WARI) {
        Total_Sum += (int) WARI;
        K_Total_WARI += WARI;
    }

    public static void reset() {
        Total_Sum = 0;
        Total_Cnt = 0;
        Arrays.fill(sCnt, 0);
        Arrays.fill(sSum, 0);
    }

    public static void view_all_buy() {
        System.out.println("\n売上一覧");
        for (int i = 0; i <= 9; i++) {
            if (kCnt[i] != 0) {
                System.out.printf("%s\t\t%2d個\t\t%,5d円\n", hMei[i], kCnt[i], kSum[i]);
            }
        }
        System.out.printf("　　割引金額合計　\t\t\t\t%,5d円\n", K_Total_WARI);
        System.out.printf("　　合　計　\t\t%2d個\t\t%,5d円\n\n", K_Total_Cnt, K_Total_Sum);
    }
}
