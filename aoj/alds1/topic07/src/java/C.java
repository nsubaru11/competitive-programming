import java.io.*;
import java.util.*;
import static java.util.Arrays.*;

public class C {

	public static void main(String[] args){
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
		for (int i: t.Pre) {
			sb.append(" ").append(i);
		}
		sb.append("\nInorder\n");
		for (int i: t.In) {
			sb.append(" ").append(i);
		}
		sb.append("\nPostorder\n");
		for (int i: t.Post) {
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
			for (Node n: tree) {
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

	private static final FastScanner sc = new FastScanner();
	@SuppressWarnings("unused")
	private static class FastScanner {
		private static final BufferedInputStream reader=new BufferedInputStream(System.in);
		private static final byte[]buffer=new byte[0x10000];
		private static int i=0,len=0;private long sum,low,high;

		public int read(){
			if(i==len){i=0;try{len=reader.read(buffer);}catch(IOException ignored){}
			if(len==-1)return 0;}if(len<=i)throw new RuntimeException();return buffer[i++];}

		String next(){
			StringBuilder sb=new StringBuilder();
			int b=read();while(b<'!'||'~'<b){b=read();}
			while('!'<=b&&b<='~'){sb.appendCodePoint(b);b=read();}return sb.toString();}

		String nextLine(){
			StringBuilder sb=new StringBuilder();
			int b=read();while(b!=0&&b!='\r'&&b!='\n'){sb.appendCodePoint(b);b=read();}
			if(b=='\r')read();return sb.toString();}

		int nextInt(){return(int)nextLong();}

		long nextLong(){
			int b=read(),c=0;while(b<'!'||'~'<b){b=read();}
			boolean neg=false;if(b=='-'){neg=true;b=read();}
			long n=0;while('0'<=b&&b<='9'){n=n*10+b-'0';b=read();c++;}
			if(c==0||c>=2&&n==0)throw new NumberFormatException();return neg?-n:n;}

		double nextDouble(){return Double.parseDouble(next());}

		String[]nextStrings(int n){String[]s=new String[n];setAll(s,x->next());return s;}

		String[][]nextStringMat(int n,int m){
			String[][]s=new String[n][m];setAll(s,x->nextStrings(m));return s;}

		char[]nextChars(){return next().toCharArray();}

		char[]nextChars(int n){char[]c=new char[n];for(int i=0;i<n;i++)c[i]=next().charAt(0);return c;}

		char[]nextSortedChars(){char[]c=nextChars();sort(c);return c;}

		char[]nextSortedChars(int n){char[]c=nextChars(n);sort(c);return c;}

		char[][]nextCharMat(int n){char[][]c=new char[n][];setAll(c,x->nextChars());return c;}

		char[][]nextCharMat(int n,int m){char[][]c=new char[n][m];setAll(c,x->nextChars(m));return c;}

		int[]nextInts(int n){
			low=0x7fffffff;high=0x80000000;sum=0;int[]a=new int[n];
			for(int i=0;i<n;i++){a[i]=nextInt();sum+=a[i];
			if(low>a[i])low=a[i];if(high<a[i])high=a[i];}return a;}

		Integer[]nextIntegers(int n){
			low=0x7fffffff;high=0x80000000;sum=0;Integer[]a=new Integer[n];
			for (int i=0;i<n;i++){a[i]=nextInt();sum+=a[i];
			if(low>a[i])low=a[i];if(high<a[i])high=a[i];}return a;}

		int[]nextSortedInts(int n){int[]a=nextInts(n);sort(a);return a;}

		Integer[]nextSortedIntegers(int n){Integer[]a=nextIntegers(n);sort(a);return a;}

		int[]nextIntSum(int n){int[]a=new int[n];a[0]=nextInt();low=a[0];high=a[0];for(int i=1;i<n;i++)
		{a[i]=nextInt();if(low>a[i])low=a[i];if(high<a[i])high=a[i];a[i]+=a[i-1];}sum=a[n-1];return a;}

		int[]nextSortedIntSum(int n){int[]a=nextSortedInts(n);for(int i=1;i<n;i++)a[i]+=a[i-1];return a;}

		int[][]nextIntMat(int h,int w){int[][]a=new int[h][w];setAll(a,x->nextInts(w));return a;}

		int[][]nextIntMatSum(int h,int w){int[][]a=new int[h][w];setAll(a,x->nextIntSum(w));
			for(int i=1;i<h;i++)for(int j=0;j<w;j++)a[i][j]+=a[i-1][j];return a;}

		long[]nextlongs(int n){low=0x7fffffffffffffffL;high=0x8000000000000000L;sum=0;long[]a=new long[n];
		for(int i=0;i<n;i++){a[i]=nextLong();sum+=a[i];if(low>a[i])low=a[i];if(high<a[i])high=a[i];}return a;}

		Long[]nextLongs(int n){low=0x7fffffffffffffffL;high=0x8000000000000000L;sum=0;Long[]a=new Long[n];
		for(int i=0;i<n;i++){a[i]=nextLong();sum+=a[i];if(low>a[i])low=a[i];if(high<a[i])high=a[i];}return a;}

		long[]nextSortedlongs(int n){long[]a=nextlongs(n);sort(a);return a;}

		Long[]nextSortedLongs(int n){Long[]a=nextLongs(n);sort(a);return a;}

		long[]nextlongSum(int n){long[]a=new long[n];a[0]=nextLong();low=a[0];high=a[0];for(int i=1;i<n;i++){
			a[i]=nextLong();if(low>a[i])low=a[i];if(high<a[i])high=a[i];a[i]+=a[i-1];}sum=a[n-1];return a;}

		long[]nextSortedlongSum(int n){long[]a=nextSortedlongs(n);for(int i=1;i<n;i++)a[i]+=a[i-1];return a;}

		long[][]nextlongMat(int h,int w){long[][]a=new long[h][w];setAll(a,x->nextlongs(w));return a;}

		long[][]nextlongMatSum(int h,int w){long[][]a=new long[h][w];setAll(a,x->nextlongSum(w));
			for(int i=1;i<h;i++)for(int j=0;j<w;j++)a[i][j]+=a[i-1][j];return a;}

	}
}