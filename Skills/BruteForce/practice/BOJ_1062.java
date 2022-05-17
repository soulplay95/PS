package algorithm.brute_force.practice;

/**
 * @문제 가르침 [G4]
 * @날짜 220517
 * @분류 완전 탐색 / 비트마스킹 / 백트래킹
 * @조건 # 1 <= N <= 50
 * # 0 <= K <= 26
 * # 8 <= 단어 길이 <= 15
 * @풀이 # 조합
 * - a, n, t, i, c 5개의 글자는 뽑아야 함. => k < 5이면 0
 * - 모든 단어에 나온 알파벳 중 k - 5개를 뽑은 뒤, 최대값 구하기
 * @comments # 정답의 최대치: Integer => max 50
 * # 시간 복잡도:
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1062 {

    static StringBuilder sb = new StringBuilder();

    static int N, K, max;
    static String[] words;
    static boolean[] isUsable;
    static Character[] candidates;
    static char[] mustPicked = {'a', 'n', 't', 'i', 'c'};

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (K < 5) {
            System.out.println(0);
            System.exit(0);
        } else if (K == 26) {
            System.out.println(N);
            System.exit(0);
        }

        // init
        max = 0;
        words = new String[N];
        isUsable = new boolean[26];

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            words[i] = word.substring(4, word.length() - 4);
        }
    }

    static void solve() {
        preprocess();
        recursive(0, 0); // 후보 알파벳 중에서 K - 5개를 뽑는다.
    }

    static void preprocess() {
        // 사용 가능한 알파벳에 a, n, t, i, c을 마킹한다.
        for (char alphabet : mustPicked) {
            isUsable[alphabet - 'a'] = true;
        }

        Set<Character> set = new HashSet<>();
        // a, n, t, i, c 를 제외한 필요한 알파벳을 candidates에 넣는다.
        for (String word : words) {
            for (char alphabet : word.toCharArray()) {
                if (isUsable[alphabet - 'a']) continue;

                set.add(alphabet);
            }
        }

        candidates = set.toArray(new Character[0]);

        if (candidates.length < K - 5) {
            K = candidates.length;
        } else {
            K -= 5;
        }
    }

    static void recursive(int depth, int start) {
        if (depth == K) {
            max = Math.max(max, getMax());
        } else {
            for (int candidateIndex = start; candidateIndex < candidates.length; candidateIndex++) {
                char alphabet = candidates[candidateIndex];
                isUsable[alphabet - 'a'] = true;
                recursive(depth + 1, candidateIndex + 1);
                isUsable[alphabet - 'a'] = false;
            }
        }
    }

    static int getMax() {
        // isUsable에 마킹된 알파벳으로 몇개의 단어를 만들 수 있는지 체크한다.
        int canMakeCounts = 0;

        for (String word : words) {
            canMakeCounts += canMake(word) ? 1 : 0;
        }

        return canMakeCounts;
    }

    static boolean canMake(String word) {
        for (char alphabet : word.toCharArray()) {
            if (!isUsable[alphabet - 'a']) {
                return false;
            }
        }

        return true;
    }

    static void print() {
        System.out.println(max);
    }

}
