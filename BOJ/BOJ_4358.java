/**
 * @문제 생태학_S1
 * @날짜 220121
 * @분류 문자열, 트리, 해시
 * @조건
 * # 입력 <= 100만
 * @풀이
 * # HashMap<종, 개수>
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_4358 {

	private static int totalCounts; // 총 나무 개수
	private static HashMap<String, Integer> countsPerSpecies; // 나무 종 당 개수
	private static ArrayList<String> species; // 종의 이름
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// init
		totalCounts = 0;
		countsPerSpecies = new HashMap<>();
		species = new ArrayList<>();

		while (true) {
			String input = br.readLine();
			if (input == null || "".equals(input)) {
				break;
			}

			if (countsPerSpecies.containsKey(input)) {
				countsPerSpecies.put(input, countsPerSpecies.get(input) + 1);
			} else {
				countsPerSpecies.put(input, 1);
				species.add(input);
			}

			totalCounts++;
		}

		Collections.sort(species); // 종 이름 사전순 정렬

		for (String type : species) {
			int counts = countsPerSpecies.get(type);
			sb.append(type).append(" ").append(String.format("%.4f", ((double)counts * 100.0) / totalCounts)).append("\n");
		}

		// print
		System.out.println(sb.toString());
	}

}
