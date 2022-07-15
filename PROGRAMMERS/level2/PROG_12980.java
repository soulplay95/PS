package programmers.level2;

import java.util.*;

public class PROG_12980 {

    // n 위치에서 처음 위치(0)로 되돌아가는 방식으로 생각해본다.
    // - n이 짝수인 경우: 건전지 소모 없이 n / 2 위치로 이동
    // - n이 홀수인 경우: 1의 건전지 소모를 통해 짝수 위치로 이동
    public int solution(int n) {
        int ans = 0;

        while (n != 0) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n = (n - 1) / 2;
                ans++;
            }
        }

        return ans;
    }
}

/*
public class Solution {
    // 짝수, 홀수의 경우로 나누어 생각한다.
    // 순간이동을 최대한 활용하려면 현재 위치(N)에서 2로 나누어 떨어지지 않을 때 까지 최대한 나눈다.
    // 현재 위치가 2로 나누어 떨어지지 않으면(홀수이면) 최소 1칸은 점프한 후 다시 순간 이동을 최대한 활용
    public int solution(int n) {
        int ans = 0;

        while (n != 0) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n--;
                ans++;
            }
        }

        return ans;
    }
}*/
