package CTest;

import java.util.Arrays;
import java.util.Scanner;

public class Lv1_1_0902 {
    public long[] solution(int x, int n) {
        long[] answer = new long[n];
        for (int i = 0; i < n; i++) {
            answer[i] = (long) x * (i + 1);
        }
        return answer; // 완성된 배열 반환
    }
}
