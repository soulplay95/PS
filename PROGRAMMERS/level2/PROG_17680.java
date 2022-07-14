package programmers.level2;

import java.util.*;

class PROG_17680 {

    static final int HIT = 1;
    static final int MISS = 5;

    // 1. 도시 순서대로 캐싱 여부를 확인한다. => O(N)
    // 1-1. 최대 cacheSize 만큼의 크기를 유지하는 Queue로 캐시 구현
    // 1-2. Cache hit or miss 여부는 contains() 메소드 사용 => O(cacheSize)
    // 1-3. LRU: Cache miss시 queue의 첫번째 원소를 poll하고 새로운 원소를 push한다.
    // 정답의 최대치: All cache miss => 50만
    public int solution(int cacheSize, String[] cities) {
        // init
        int answer = 0;
        int N = cities.length;
        Queue<String> cache = new LinkedList<>();

        // 예외처리 - cacheSize가 0인 경우
        if (cacheSize == 0) return N * MISS;

        // 1. 도시 순서대로 캐싱 여부를 확인
        for (String city : cities) {
            city = city.toLowerCase(); // 대소문자 구분 없애기

            if (cache.contains(city)) { // HIT
                // 최신화
                cache.remove(city);
                answer += HIT;
            } else {
                if (cache.size() >= cacheSize) { // 캐시가 꽉 찬 경우
                    cache.poll();
                }
                answer += MISS;
            }

            cache.offer(city);
        }

        return answer;
    }
}

/*
class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        HashSet<String> cachedSet = new HashSet<>();
        Queue<String> q = new LinkedList<>();

        for (String city : cities) {
            city = city.toLowerCase();

            // 캐싱된 데이터인지 확인
            if (cachedSet.contains(city)) {
                answer += 1;
            } else {
                answer += 5;
            }

            // LRU
            if (cachedSet.size() == cacheSize && !q.isEmpty()) {
                cachedSet.remove(q.poll()); // 오래된 데이터 제거
            }
            if (cachedSet.size() < cacheSize) {
                if (!cachedSet.contains(city)) {
                    q.offer(city);
                    cachedSet.add(city);
                }
            }
        }

        return answer;
    }
}*/
