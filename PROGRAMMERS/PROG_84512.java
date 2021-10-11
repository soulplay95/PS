// 규칙 찾기
// A, AA, AAA, AAAA
// AAAAA, AAAAE, AAAAI, AAAAO, AAAAU
// AAAE
// AAAEA, AAAEE, AAAEI, AAAEO, AAAEU
// AAAI
// ...

// AAA
// AAAA, AAAE, AAAI, ..

// AAE

// => 문자열의 각 자리마다 맨 끝자리의 경우 1씩 증가
// 그 앞자리(4번째 자리)의 경우 6씩 증가(1 * 5 + 1)
// 그 앞자리(3번째 자리)의 경우 31씩 증가(6 * 5 + 1)

import java.util.*;
import java.io.*;

public class PROG_84512 {

	public int solution(String word) {
		String vowel = "AEIOU";
		int[] x = {781, 156, 31, 6, 1};
		int result = word.length();
		for(int i = 0; i < word.length(); i++) {
			int index = vowel.indexOf(word.charAt(i));
			result += x[i] * index;
		}

		return result;
	}

}