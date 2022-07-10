package programmers.level2;

import java.util.*;

class PROG_72412 {

    static HashMap<String, ArrayList<Integer>> map; // key: 조건의 조합("cppbackendjuniorpizza"), value: 조건에 해당하는 점수 리스트

    static final int CONDITION_COUNTS = 4; // 조건 개수

    public int[] solution(String[] info, String[] query) {
        // init
        map = new HashMap<>();

        // 1. 조건을 조합해 조합별로 분류한다.
        for (String conditions : info) {
            recursive(conditions.split(" "), 0, "");
        }

        // 2. 이분 탐색을 위해 모든 리스트를 오름차순 정렬한다.
        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }

        // 3. 모든 쿼리에 대해 이분 탐색으로 lower bound를 구한다. + 정답 구성
        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            // 파싱 - 쿼리를 map의 key 조합처럼 문자열을 이어붙인다.
            String[] split = query[i].replaceAll(" and ", "").split(" ");
            String conditions = split[0];
            int score = Integer.parseInt(split[1]);

            answer[i] = lowerBound(conditions, score);
        }

        return answer;
    }

    // 부분조합으로 조건 문자열을 이어 붙여 조합을 만든다.
    static void recursive(String[] conditions, int conditionIndex, String cur) {
        if (conditionIndex == CONDITION_COUNTS) { // 모든 조건을 조합한 경우
            // 해당 조합에 점수를 추가한다.
            if (!map.containsKey(cur)) { // 처음 등장한 조합의 경우
                map.put(cur, new ArrayList<>());
            }
            int score = Integer.parseInt(conditions[CONDITION_COUNTS]);
            map.get(cur).add(score);

            return;
        }

        recursive(conditions, conditionIndex + 1, cur + "-");
        recursive(conditions, conditionIndex + 1, cur + conditions[conditionIndex]);
    }

    static int lowerBound(String key, int score) {
        if (!map.containsKey(key)) { // 없는 조합이면
            return 0;
        }

        ArrayList<Integer> scoreList = map.get(key);
        int L = 0, R = scoreList.size() - 1, result = R + 1;

        while (L <= R) {
            int M = (L + R) / 2;

            if (scoreList.get(M) >= score) {
                result = M;
                R = M - 1;
            } else {
                L = M + 1;
            }
        }

        return scoreList.size() - result; // lowerBound 이상의 원소 개수
    }

}

/*class PROG_72412 {

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"},
                new String[] {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"}));
    }

    static class Info {
        String[] conditions;

        Info(String[] _conditions) {
            conditions = new String[CONDITION_COUNTS];

            for (int i = 0; i < CONDITION_COUNTS; i++) {
                conditions[i] = _conditions[i];
            }
        }

        Info(Info _info) {
            conditions = _info.conditions;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Info)) return false;

            Info other = (Info) o;

            for (int i = 0; i < CONDITION_COUNTS; i++) {
                if (!(conditions[i].equals(other.conditions[i]))) return false;
            }

            return true;
        }
    }

    static HashMap<Info, ArrayList<Integer>> scoreListMappedByCondition;

    // static HashMap<String, Integer> indexMappedByTag; // key: tag, value: tags 배열에서의 index
    // static HashMap<String, ArrayList<Integer>> ScoreListMappedByCondition; // key: 4자리 조건, value: 점수 리스트

    static final int CONDITION_COUNTS = 4; // 조건 개수
    // static final String[] tags = {"-", "cpp", "java", "python", "backend", "frontend", "junior", "senior", "chicken", "pizza"};

    // 0. 하나의 info 원소는 총 16(2^4)가지 그룹에 속할 수 있다.
    // 1. info를 한 번 순회하여 각 원소를 16가지 그룹에 점수를 넣는다.
    // 1-1. HashMap<String, ArrayList<Integer>> => key: 각 조건을 숫자로 매핑한 4자리 String. ex) "0140" => -, cpp, backend, - / value: 점수 리스트
    // 1-2. 이분 탐색을 위해 각 리스트를 오름차순 정렬한다.
    // 2. 각 쿼리마다 해당 그룹에서 이분탐색으로 lower bound를 찾는다.
    public static int[] solution(String[] info, String[] query) {
        init(info);

        // log
        scoreListMappedByCondition.forEach((key, value) -> {
            System.out.println(key + " " + value);
        });

        System.out.println(scoreListMappedByCondition.size());

        return null;
    }

    static void init(String[] info) {
        scoreListMappedByCondition = new HashMap<>();

        // 1. info 당 가능한 16가지 조건 조합에 점수를 넣는다.
        for (String infoString : info) {
            // 각 조건을 파싱한다.
            String[] split = infoString.split(" ");
            int score = Integer.parseInt(split[CONDITION_COUNTS]);

            recursive(0, split, new Info(new String[]{"-", "-", "-", "-"}), score);
        }
    }

    static void recursive(int conditionIndex, String[] conditions, Info info, int score) {
        if (conditionIndex == CONDITION_COUNTS) { // 모든 조건에 대해 처리를 마친 경우
            // 현재 index의 조건 리스트에 점수를 추가한다.
            if (!scoreListMappedByCondition.containsKey(info)) { // 해당 조건 조합이 처음 등장한 경우
                scoreListMappedByCondition.put(info, new ArrayList<>());
            }
            scoreListMappedByCondition.get(info).add(score); // 점수 추가
            return;
        }

        // 부분 집합
        recursive(conditionIndex + 1, conditions, info, score); // 조건 스킵
        Info newInfo = new Info(info);
        newInfo.conditions[conditionIndex] = conditions[conditionIndex];
        recursive(conditionIndex + 1, conditions, newInfo, score); // 조건 추가
    }

}*/

