package programmers.level2;

class PROG_49993 {

    // 스킬트리의 각 스킬을 배우는 것이 가능한지 순서대로 따져본다.
    // - String.indexOf()를 활용해 skill 문자열 내에 없으면(-1) 다음 스킬을 따져본다.
    // - 있다면 현재 스킬 순서(0부터 시작) 내에 있는지 확인한다.
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for (String skillTree : skill_trees) {
            if (isValidate(skill, skillTree)) answer++;
        }

        return answer;
    }

    static boolean isValidate(String skill, String skillTree) {
        int currentStep = 0; // skill에서 currentStep 이하의 스킬들은 배울 수 있는 상태를 나타냄

        for (char currentSkill : skillTree.toCharArray()) {
            int step = skill.indexOf(currentSkill);

            if (step == -1) continue;
            if (step == currentStep) currentStep++; // 다음 스킬을 배울 수 있게 된다.
            if (step > currentStep) return false; // 선행 스킬을 배우지 않은 상황
        }

        return true;
    }
}

/*
class Solution {
    static int[] order;

    static boolean isValidate(String skill_tree) {
        int stage = 1;

        for (int i = 0; i < skill_tree.length(); i++) {
            char c = skill_tree.charAt(i);

            if (order[c - 'A'] == 0) {
                continue;
            }

            if (order[c - 'A'] == stage) {
                stage++;
            } else {
                return false;
            }
        }

        return true;
    }

    // skill에 나온 순서대로 int[] 배열에 1부터 차례대로 값을 넣는다.
    // 모든 스킬 트리에 대해 order에 맞게 나열되었는지 확인한다.
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        order = new int[26];

        // skill 순서 배치
        for (int i = 1; i <= skill.length(); i++) {
            char c = skill.charAt(i - 1);

            order[c - 'A'] = i;
        }

        for (String skill_tree : skill_trees) {
            if (isValidate(skill_tree)) {
                answer++;
            }
        }

        return answer;
    }
}*/
