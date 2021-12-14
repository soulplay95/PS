/**
 * @문제 이진 검색 트리_S1
 * @날짜 211214
 * @분류
 * @조건
 * # 노드의 수 <= 1만
 * @풀이
 * # 전위 순회한 결과를 가지고 크기 비교를 하면서 트리를 구성한다.
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_5639 {

	private static class Node {
		int key;
		Node left, right;

		public Node(int key) {
			this.key = key;
		}

		public void insert(int key) {
			if (key < this.key) {
				if (this.left == null) {
					this.left = new Node(key);
				} else {
					this.left.insert(key);
				}
			} else {
				if (this.right == null) {
					this.right = new Node(key);
				} else {
					this.right.insert(key);
				}
			}
		}
	}

	private static Node tree;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		tree = new Node(Integer.parseInt(br.readLine()));
		while (true) {
			String input = br.readLine();
			if (input == null || "".equals(input)) {
				break;
			}
			int key = Integer.parseInt(input);
			tree.insert(key);
		}

		postOrder(tree);

		// print
		System.out.println(sb.toString());
	}

	private static void postOrder(Node node) {
		if (node == null) {
			return;
		}

		postOrder(node.left);
		postOrder(node.right);
		sb.append(node.key).append("\n");
	}

}
