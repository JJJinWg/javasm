package CTest;

class Lv1_3_0902 {
    public long solution(long n) {
        // n의 제곱근을 계산
        long sqrt = (long) Math.sqrt(n);

        // 제곱근의 제곱이 n과 같은지 확인
        if (sqrt * sqrt == n) {
            // n이 x의 제곱이라면 (x + 1)의 제곱을 반환
            return (sqrt + 1) * (sqrt + 1);
        } else {
            // n이 x의 제곱이 아니라면 -1을 반환
            return -1;
        }
    }
}
