import java.util.*;

public class C {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		//ArrayList<Node> tree = new ArrayList<>();
		Node[] tree = new Node[n];
		for (int i = 0; i < n; i++) {
			int label = sc.nextInt();
			int left = sc.nextInt();
			int right = sc.nextInt();
			tree[label] = new Node(label, left, right);
			//tree.add(new Node(label, left, right));
		}

//		Stack<Integer> stack = new Stack<>();
//		stack.push(tree.get(0).right);
//		stack.push(tree.get(0).left);
//		int node = 0;
//		while (!stack.isEmpty()) {
//			System.out.print(" " + node);
//			node = stack.pop();
//			int r = tree.get(node).right;
//			int l = tree.get(node).left;
//			if (r != -1) stack.push(r);
//			if (l != -1) stack.push(l);
//			if (r == -1 && l == -1 && stack.isEmpty()) {
//				System.out.println(" " + node);
//			}
//		}

		StringBuilder sb = new StringBuilder();
		Tree t = new Tree(tree);
		sb.append("Preorder\n");
		for (int i : t.Pre) {
			sb.append(" ").append(i);
		}
		sb.append("\nInorder\n");
		for (int i : t.In) {
			sb.append(" ").append(i);
		}
		sb.append("\nPostorder\n");
		for (int i : t.Post) {
			sb.append(" ").append(i);
		}
		System.out.println(sb);
	}

	private static class Tree {
		private ArrayList<Integer> Pre, In, Post;
		private Node[] tree;

		Tree(Node[] tree) {
			this.tree = tree;
			Pre = new ArrayList<>();
			In = new ArrayList<>();
			Post = new ArrayList<>();
			int root = search(tree);
			traverse(root);
		}

		private int search(Node[] tree) {
			boolean[] s = new boolean[tree.length];
			for (Node n : tree) {
				if (n.left != -1) s[n.left] = true;
				if (n.right != -1) s[n.right] = true;
			}
			for (int i = 0; i < tree.length; i++) {
				if (!s[i]) return i;
			}
			return 0;
		}

		private void traverse(int label) {
			if (label == -1) return;
			Pre.add(label);
			traverse(tree[label].left);
			In.add(label);
			traverse(tree[label].right);
			Post.add(label);
		}

	}

	private static class Node {
		int label, left, right;

		Node(int label, int left, int right) {
			this.label = label;
			this.left = left;
			this.right = right;
		}
	}
}
