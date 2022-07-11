package programmers.level2;

class PROG_42860 {

    // 커서 위치가 바뀌게 되는 경우
    // 1. 처음부터 쭉 오른쪽으로 가는 경우 => length - 1
    // 2. 오른쪽으로 갔다가 왼쪽으로 턴하는 경우 => ex) BBAAAAAYYY
    // 3. 왼쪽으로 갔다가 오른쪽으로 턴하는 경우 => ex) CCCAAAAAAY
    // 연속된 A 여부에 따라 위의 3가지 방식 중 하나를 택한다.
    public int solution(String name) {
        int length = name.length();
        int verticalMoves = 0; // 상하 움직임 수
        int horizontalMoves = length - 1; // 좌우 움직임 수. 1번 경우(최대)로 초기화

        for (int i = 0; i < length; i++) {
            char c = name.charAt(i);

            // 상하 움직인 횟수 계산
            verticalMoves += Math.min(c - 'A', 'Z' - c + 1);

            // A가 끝나는 지점을 구한다.
            int endAIndex = i + 1;
            while (endAIndex < length && name.charAt(endAIndex) == 'A') {
                endAIndex++;
            }

            // 처음 위치(index 0) 기준 2, 3번 경우 중 최소값을 따져본다.

            // i * 2: 처음 위치에서 현재 위치로 왔다가 다시 처음 위치로 돌아갈 때 조작 횟수
            // length - endAIndex: 처음 위치에서 A를 만나기 전까지 왼쪽으로 조작한 횟수
            horizontalMoves = Math.min(horizontalMoves, i * 2 + (length - endAIndex)); // 2
            // (length - endAIndex) * 2: 처음 위치에서 왼쪽으로 A를 만나기 전까지 조작한 후 처음 위치로 다시 조작하여 돌아올 때 조작 횟수
            // i: 처음 위치에서 현재 위치로 올 때 조작 횟수
            horizontalMoves = Math.min(horizontalMoves, (length - endAIndex) * 2 + i); // 3
        }


        return verticalMoves + horizontalMoves;
    }

}

/*class Solution {

    // 1. 현재 위치의 알파벳을 바꾸거나, 커서를 왼쪽 혹은 오른쪽으로 이동하거나 3개의 선택 중 최선의 선택을 한다.
    public int solution(String name) {
        // init
        int N = name.length();
        int answer = 0;
        int cursor = 0;
        String currentName = "A".repeat(N);

        while (!name.equals(currentName)) {
            // 현재 커서의 알파벳이 바꿀 필요가 있는지 확인한다.
            if (name.charAt(cursor) == currentName.charAt(cursor)) {
                // 왼쪽 혹은 오른쪽 중 바꿔야 할 알파벳의 위치로 이동
                // 왼쪽
                int leftCursor = (cursor + N - 1) % N;
                int leftDistance = 0;
                while (cursor != leftCursor) {
                    if (name.charAt(leftCursor) != currentName.charAt(leftCursor)) {
                        leftDistance++;
                        break;
                    }

                    leftDistance++;
                    leftCursor = (leftCursor + N - 1) % N;
                }
                // 오른쪽
                int rightCursor = (cursor + 1) % N;
                int rightDistance = 0;
                while (cursor != rightCursor) {
                    if (name.charAt(rightCursor) != currentName.charAt(rightCursor)) {
                        rightDistance++;
                        break;
                    }

                    rightDistance++;
                    rightCursor = (rightCursor + 1) % N;
                }

                if (leftDistance != 0 && leftDistance <= rightDistance) {
                    answer += leftDistance;
                    cursor = leftCursor;
                } else {
                    answer += rightDistance;
                    cursor += rightCursor;
                }
            } else {
                // 최소 움직임으로 알파벳을 변경한다.
                int distance = name.charAt(cursor) - currentName.charAt(cursor); // ? - 'A'
                if (distance <= 13) {
                    answer += distance;
                } else {
                    answer += distance - (distance - 13) * 2;
                }

                StringBuilder sb = new StringBuilder(currentName);
                sb.setCharAt(cursor, name.charAt(cursor));
                currentName = sb.toString();
            }
        }

        return answer;
    }
}*/

