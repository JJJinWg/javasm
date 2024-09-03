class Solution {
    public int solution(int n) {
        int answer = 0; // 약수의 합을 저장할 변수 초기화
        // 1부터 n까지 반복하여 약수를 찾음
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) { // n이 i로 나누어 떨어지면 i는 n의 약수
                answer += i; // 약수를 더함
            }
        }
        return answer; // 모든 약수를 더한 값을 반환
    }
}
