
import java.util.Comparator;

/**
 * Created by enderWoice on 05.02.2017.
 */
public class Solver {

    private static final boolean DEBUG = false;
    private static final boolean PROFILE = true;
    private Comparator<SearchNode> Order = new MinOrder();

    private Board current;
    private boolean canSolve = false;
    private int minMove = -1;
    private int moves = 0;
    private Queue<Board> queueResult;
    private MinPQ<SearchNode> min;
    private MinPQ<SearchNode> sm;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board board) {
        current = board;
        canSolve = true;

        Stopwatch sw = new Stopwatch();

        queueResult = new Queue<Board>();
        min = new MinPQ<SearchNode>(Order);
        sm = new MinPQ<SearchNode>(Order);

        if (current.hamming() == 0) {
            canSolve = true;
            minMove = moves;
            return;
        }
        SearchNode curSearchNode = new SearchNode(current, 0);
        SearchNode preSearchNode = new SearchNode(null, 0);
        SearchNode twinSearchNode = new SearchNode(current.twin(), 0);
        SearchNode preTwinSearchNode = new SearchNode(null, 0);
        queueResult.enqueue(curSearchNode.board);

        while (true) {
            searchForNode(twinSearchNode, preTwinSearchNode, sm);
            preTwinSearchNode = twinSearchNode;
            SearchNode twinN, n;
            do {
                twinN = sm.delMin();
            } while (twinN.board.equals(preTwinSearchNode.board));
            twinSearchNode = twinN;
            if (twinN.board.isGoal()) {
                canSolve = false;
                minMove = -1;
                break;
            }

            searchForNode(curSearchNode, preSearchNode, min);
            preSearchNode = curSearchNode;
            do {
                n = min.delMin();
            } while (n.board.equals(preSearchNode.board));
            queueResult.enqueue(n.board);
            curSearchNode = n;

            if (curSearchNode.board.isGoal()) {
                canSolve = true;
                minMove = curSearchNode.moves;
                break;
            }
        }
        min = null;
        sm = null;

        if (PROFILE)
            StdOut.printf("time on solve :%f ms\n", sw.elapsedTime());
    }

    private void searchForNode(SearchNode node, SearchNode preNode, MinPQ<SearchNode> q) {
        for (Board n : node.board.neighbors()) {
            if (!n.equals(preNode.board)) {
                q.insert(new SearchNode(n, node.moves + 1));
            }
        }
    }

    private class SearchNode implements Comparable<SearchNode> {
        private int moves;
        private Board board;

        public SearchNode(Board b, int m) {
            board = b;
            moves = m;
        }

        public int compareTo(SearchNode that) {
            if (that == null)
                throw new RuntimeException();
            int m = this.board.manhattan() + this.board.hamming();
            m += this.moves;

            int t = that.board.manhattan() + that.board.hamming();
            t += that.moves;

            if (m > t)
                return 1;
            else if (m < t)
                return -1;
            else
                return 0;

        }
    }

    private boolean solveOneBoard(Board oneBoard) {
        return true;
    }

    private class MinOrder implements Comparator<SearchNode> {
        public int compare(SearchNode a, SearchNode b) {
            return a.compareTo(b);
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return canSolve;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return minMove;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (canSolve)
            return queueResult;
        return null;
    }

    // solve a slider puzzle
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        Solver solver = new Solver(initial);

        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                if (DEBUG)
                    StdOut.printf("manhatan:%d hamming:%d moves:%d \n",
                            board.manhattan(),
                            board.hamming() /*, board.moves */);
                StdOut.println(board);
            }
        }
    }

}