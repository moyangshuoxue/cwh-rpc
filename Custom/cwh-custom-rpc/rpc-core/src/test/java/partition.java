import java.util.*;

/**
 * @Author 蔡文瀚
 * @Date 2024/6/10 10:17
 * @Version 1.0
 * @ClassName partition
 * @Description This is a general-purpose Java class.
 */
public class partition {
    private final List<List<String>> ans = new ArrayList<>();
    private final List<String> path = new ArrayList<>();
    private String s;

    public List<List<String>> partition(String s) {
        this.s = s;
        dfs(0);
        return ans;
    }

    private boolean isPalindrome(int left, int right) {
        while (left < right)
            if (s.charAt(left++) != s.charAt(right--))
                return false;
        return true;
    }

    private void dfs(int i) {
        if (i == s.length()) {
            ans.add(new ArrayList<>(path)); // 复制 path
            return;
        }
        for (int j = i; j < s.length(); ++j) { // 枚举子串的结束位置
            if (isPalindrome(i, j)) {
                path.add(s.substring(i, j + 1));
                dfs(j + 1);
                path.remove(path.size() - 1); // 恢复现场
            }
        }
    }

    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxLength = 0  , n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxLength = Math.max(maxLength, left * 2);
            }
            if (right > left) {
                right = left = 0;
            }
        }
        right = left =0;
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxLength = Math.max(maxLength, left * 2);
            }
            if (right < left) {
                right = left = 0;
            }
        }
        return maxLength;
    }




    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int temperature = temperatures[i];
            while(!deque.isEmpty() && temperatures[deque.peek()]<temperature){
                Integer prev = deque.pop();
                ans[prev] = i-prev;
            }
            deque.push(i);
        }
        return  ans;
    }


    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right =new int[n];
        Deque<Integer> deque = new LinkedList();
        int ans =0;
        Arrays.fill(right,n);
        for (int i = 0; i < n; ++i) {
            while(!deque.isEmpty() && heights[i]<=heights[deque.peek()]){
                right[deque.peek()] = i;
                deque.pop();
            }
            left[i] = deque.isEmpty() ? -1: deque.peek();
            deque.push(i);
        }
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans,(right[i] - left[i]-1)*heights[i]);
        }
        return  ans;
    }


    public int minDistance(String word1, String word2) {
        int n =word1.length();
        int m = word2.length();
        if (n * m == 0) {
            return n + m;
        }
        int[][] dp = new int[n+1][m+1];
        for (int i = 0; i < n+1; i++) {
            dp[i][0]=i;
        }
        for (int i = 0; i < m+1; i++) {
            dp[0][i] =i;
        }
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                int left =  dp[i][j-1]+1;
                int down = dp[i-1][j]+1;
                int left_down =dp[i-1][j-1];
                if(word1.charAt(i-1) != word2.charAt(j-1)){
                    left_down+=1;
                }
                dp[i][j] = Math.min(left,Math.min(down,left_down));
            }
        }
        return dp[n][m];
    }

}


