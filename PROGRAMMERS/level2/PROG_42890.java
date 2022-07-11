package programmers.level2;

import java.util.*;

class PROG_42890 {

    static String[][] map;
    static int R, C;
    static ArrayList<String> superKeyList;
    static HashSet<String> set;

    // 1. 속성의 가능한 모든 조합을 구한다. => 부분 집합(공집합 제외)
    // 2. 튜플에 중복이 있는지 체크한다. 없다면, 후보키 가능성(슈퍼키)이 있다.
    // 3. 각 슈퍼키에 대해 부분집합이 슈퍼키 집합 내에 포함되는지 확인한다. 없다면 후보키
    public int solution(String[][] relation) {
        init(relation);

        // 1. 속성의 가능한 모든 조합을 구한다.
        dfs(0, "");

        // 3. 각 슈퍼키의 부분집합이 슈퍼키 리스트 내에 포함되는지 확인한다.
        // 길이 순으로 오름차순 정렬
        Collections.sort(superKeyList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        for (String superKey : superKeyList) {
            if (superKey.length() == 1) {
                set.add(superKey);
            } else {
                // 부분 집합을 만들어 set에 포함되는지 확인
                if (isAnswer(superKey)) { // 포함되지 않으면(후보키이면)
                    set.add(superKey);
                }
            }
        }

        return set.size();
    }

    static boolean isAnswer(String superKey) {
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String candidateKey = it.next();
            int counts = 0;

            // 부분집합 검사
            // 하나씩 쪼개서 후보키에 포함되는 개수가 후보키 길이라면, 후보키는 superKey의 부분집합이다.
            for (char c : superKey.toCharArray()) {
                if (candidateKey.contains(String.valueOf(c))) {
                    counts++;
                }
            }

            if (counts == candidateKey.length()) {
                return false;
            }
        }

        return true;
    }

    static void init(String[][] relation) {
        map = relation;
        R = map.length;
        C = map[0].length;
        superKeyList = new ArrayList<>();
        set = new HashSet<>();
    }

    static void dfs(int depth, String cur) {
        if (depth == C) {
            // 공집합 제외
            if (cur.isEmpty()) {
                return;
            }

            // 2. 해당 속성 조합의 튜플에 중복이 있는지 확인한다.
            if (isValidate(cur)) {
                superKeyList.add(cur);
            }

            return;
        }

        dfs(depth + 1, cur);
        dfs(depth + 1, cur + depth);
    }

    static boolean isValidate(String key) {
        HashSet<String> set = new HashSet<>();

        // key를 구성하는 column에 해당하는 값을 이어 붙여서 set에 저장한다.
        // 파싱. "012" => {0, 1, 2}
        char[] charColumns = key.toCharArray();
        int[] columns = new int[charColumns.length];
        for (int i = 0; i < charColumns.length; i++) {
            columns[i] = (int) (charColumns[i] - '0');
        }

        for (int r = 0; r < R; r++) {
            StringBuilder sb = new StringBuilder();
            for (int c : columns) {
                sb.append(map[r][c]);
            }

            if (set.contains(sb.toString())) { // 중복
                return false;
            }
            set.add(sb.toString());
        }

        return true;
    }
}