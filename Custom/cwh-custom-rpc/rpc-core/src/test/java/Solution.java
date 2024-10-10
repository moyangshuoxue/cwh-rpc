import java.util.*;

/**
 * @Author 蔡文瀚
 * @Date 2024/6/10 8:46
 * @Version 1.0
 * @ClassName
 * @Description This is a general-purpose Java class.
 */
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == p || root==q || root==null) return  root;
        TreeNode left = lowestCommonAncestor(root.left, p,q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left !=null && right != null) return  root;
        if(left==null) return right;
        return left;

    }



     public class TreeNode {
      int val;
     TreeNode left;
     TreeNode right;
      TreeNode(int x) { val = x; }
  }

    List<List<Integer>> edges;
    int[] indeg;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        indeg = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            ++indeg[info[0]];
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }

        int visited = 0;
        while (!queue.isEmpty()) {
            ++visited;
            int u = queue.poll();
            for (int v: edges.get(u)) {
                --indeg[v];
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        return visited == numCourses;
    }




        List<Integer> t = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        public List<List<Integer>> subsets(int[] nums) {
            dfs(0, nums);
            return ans;
        }

        public void dfs(int cur, int[] nums) {
            if (cur == nums.length) {
                ans.add(new ArrayList<Integer>(t));
                return;
            }
            t.add(nums[cur]);
            dfs(cur + 1, nums);
            t.remove(t.size() - 1);
            dfs(cur + 1, nums);
        }





    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] infectTime = new int[m][n]; // -1: not infected yet; -2: empty
        int t = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) infectTime[i][j] = -2;
                if (grid[i][j] == 1) infectTime[i][j] = -1;
                if (grid[i][j] == 2) infectTime[i][j] = 0;
            }
        }
        while (true) {
            boolean infected = false;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (infectTime[i][j] == t) {
                        if (i - 1 >= 0 && infectTime[i - 1][j] == -1) {
                            infectTime[i - 1][j] = t + 1;
                            infected = true;
                        }
                        if (i + 1 < m && infectTime[i + 1][j] == -1) {
                            infectTime[i + 1][j] = t + 1;
                            infected = true;
                        }
                        if (j - 1 >= 0 && infectTime[i][j - 1] == -1) {
                            infectTime[i][j - 1] = t + 1;
                            infected = true;
                        }
                        if (j + 1 < n && infectTime[i][j + 1] == -1) {
                            infectTime[i][j + 1] = t + 1;
                            infected = true;
                        }
                    }
                }
            }
            if (!infected) break;
            t++;
        }
        for (int[] row : infectTime) {
            for (int x : row) {
                if (x == -1) return -1;
            }
        }
        return t;
    }






}


class Trie {
   private  Node root;    // 根节点

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        int n = word.length();
        Node node = root;
        for (int i = 0; i < n; i++) {
            int id =  word.charAt(i)-'a';
            if(node.children[id] == null){
                node.children[id] = new Node();
            }
            node = node.children[id];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Node node = searchPrefix(word);
        return node!=null && node.isEnd;

    }

    public boolean startsWith(String prefix) {

        return  searchPrefix(prefix) != null;
    }
    public Node searchPrefix(String word){

        int n = word.length();
        Node node = root;
        for (int i = 0; i < n; i++) {
            int id = word.charAt(i) - 'a';
            if(node.children[id] != null){
                node = node.children[id];
            }else{
                return  null;
            }
        }
        return  node;
    }

    /**
     * 查找字典树是否包含word前缀
     */

}

/**
 * 字典树节点
 */
class Node{
    Node[] children;

    boolean isEnd;
    public Node(){
        children = new Node[26];
        isEnd = false;
    }
}


