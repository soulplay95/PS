package programmers.level2;

import java.util.*;

class PROG_42883 {

    // 1. number의 앞에서부터 보면서 "현재 자릿수 ~ 남은 k범위까지"의 숫자들 중 현재 자릿수의 숫자보다 큰게 있다면 삭제한다.
    public String solution(String number, int k) {
        // init
        int length = number.length();
        int i = 0;
        StringBuilder sb = new StringBuilder();

        for (; i < number.length(); i++) {
            // 뒤의 k개의 수를 보고 현재 숫자보다 큰게 있으면 제거
            char cur = number.charAt(i);
            if (cur == '9') { // 9보다 큰 수는 없으므로
                sb.append(cur);
                continue;
            }

            int j = i + 1;
            boolean isDelete = false;
            while (j < length && j <= i + k) {
                if (number.charAt(j) > cur) {
                    // 제거
                    k--;
                    isDelete = true;
                    break;
                }
                j++;
            }

            if (!isDelete) {
                sb.append(cur);
            }
        }

        // 예외 처리 - k가 남아있는 경우
        // 뒤에서 k개를 삭제
        while (k-- > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

}

