package programmers.level2;

import java.util.*;

class PROG_87377 {

    static class Pair {
        long r, c;

        Pair(long _r, long _c) {
            r = _r;
            c = _c;
        }
    }

    // 1. 모든 직선 쌍에 대해 정수로 표현되는 교점을 구한 뒤 Set에 넣는다. => O(N^2)
    // 2. 정답 배열의 크기, 매핑에 쓰일 최소, 최대 x, y 좌표를 구한다.
    // 2-1. 정답 배열 크기: max - min + 1
    // 2-2. 매핑된 좌표
    // - nr = maxR - r;
    // - nc = c - minC;
    public String[] solution(int[][] line) {
        // init
        int N = line.length; // 직선 개수
        HashSet<Pair> intersectionPairSet = new HashSet<>();
        long minR = Long.MAX_VALUE;
        long minC = Long.MAX_VALUE;
        long maxR = Long.MIN_VALUE;
        long maxC = Long.MIN_VALUE;

        // 1
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                long a = (long) line[i][0];
                long b = (long) line[i][1];
                long c = (long) line[j][0];
                long d = (long) line[j][1];
                long e = (long) line[i][2];
                long f = (long) line[j][2];

                long adbc = a * d - b * c;
                if (adbc == 0) continue; // 두 직선이 평행 또는 일치하는 경우

                long bfed = b * f - e * d;
                long ecaf = e * c - a * f;
                if (bfed % adbc != 0 || ecaf % adbc != 0) continue; // 정수로 표현되지 않는 좌표인 경우

                long nr = ecaf / adbc;
                long nc = bfed / adbc;
                intersectionPairSet.add(new Pair(nr, nc));

                // 최소, 최대값 갱신
                minR = Math.min(minR, nr);
                minC = Math.min(minC, nc);
                maxR = Math.max(maxR, nr);
                maxC = Math.max(maxC, nc);
            }
        }

        // 2
        // 정답 크기
        int R = (int) (maxR - minR) + 1;
        int C = (int) (maxC - minC) + 1;
        String[] answer = new String[R];
        String init = ".".repeat(C);
        for (int r = 0; r < R; r++) {
            answer[r] = init;
        }

        // Set 순회하여 좌표 매핑해서 별 찍기
        Iterator<Pair> it = intersectionPairSet.iterator();
        while (it.hasNext()) {
            Pair cur = it.next();
            int nr = (int) (maxR - cur.r);
            int nc = (int) (cur.c - minC);

            answer[nr] = answer[nr].substring(0, nc) + "*" + answer[nr].substring(nc + 1);
        }

        return answer;
    }
}