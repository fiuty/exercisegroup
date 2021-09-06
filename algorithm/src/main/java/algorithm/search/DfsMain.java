package algorithm.search;

/**
 * 深度优先搜索算法（Depth First Search）：类似于广度优先搜索，也是对连通图进行遍历的算法，思想是从一个顶点开始，
 * 沿着一条路走到低，如果发现不能到达目标解，那就返回上一个节点，然后从另一条路走到底，这种尽量往深处走的概念即是深度优先搜索。
 * 缺点是难以寻找最优解，无法知道达到终点的路径是否为最短。可以用暴力法解决，但可以在暴力法的基础上进行优化，
 * 当已经走完达到终止条件，记录状态，当其他路径深度搜索过程中超过该状态则不继续深度搜索下去，提前结束。
 *
 * 代码基本流程：明确起点和终点（起始条件和终止条件）--> 当前节点可以继续走，继续前进，设置已访问 --> 递归下去 -->
 * --> 设置未访问（别的路径要走，恢复初始状态）--> 递归终止条件（/比较最终路线结果）
 *
 * 深度优先搜索算法基本上需要用到递归，设置好递归结束条件，递归前设置节点已被访问，递归后恢复初始条件，以供其他路线考虑
 * 最后可以通过全局变量比较路线结果（深搜结束的结果）。
 * @author zhengdayue
 */
public class DfsMain {


}