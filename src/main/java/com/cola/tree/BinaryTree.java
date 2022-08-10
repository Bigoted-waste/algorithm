package com.cola.tree;


import com.cola.linear.Queue;

/**
 * 二叉树
 *
 * @param <Key>
 * @param <Value>
 */
public class BinaryTree<Key extends Comparable<Key>, Value> {

    // 跟节点
    private Node root;

    // 记录树中元素的个数
    private int N;

    /**
     * 获取书中元素的个数
     *
     * @return
     */
    public int size() {
        return N;
    }

    /**
     * 向树中添加元素key-value
     *
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    /**
     * 向指定的树x中添加key-value，并返回添加元素后新的树
     *
     * @param x
     * @param key
     * @param value
     * @return
     */
    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            N++;
            return new Node(key, value, null, null);
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            // 新节点的key大于当前节点的key，继续找当前节点的右子节点
            x.right = put(x.right, key, value);
        } else if (cmp < 0) {
            // 新节点的key小于当前节点的key，继续找当前节点的左子节点
            x.left = put(x.left, key, value);
        } else {
            // 新节点的key等于当前节点key，把当前节点的value进行替换
            x.value = value;
        }
        return x;
    }

    /**
     * 查找树中指定key对应的value
     *
     * @param key
     * @return
     */
    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * 从指定的树x中，查找key对应的值
     *
     * @param x
     * @param key
     * @return
     */
    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            // 如果要查询的key大于当前节点的key，则继续找当前节点的右子节点
            return get(x.right, key);
        } else if (cmp < 0) {
            // 如果要查询的key小于当前节点的key，则继续找当前节点的左子节点
            return get(x.left, key);
        } else {
            // 如果要查找的key等于当前节点的key，则书中返回当前节点的value
            return x.value;
        }
    }

    /**
     * 删除树中key对应的value
     *
     * @param key
     */
    public void delete(Key key) {
        root = delete(root, key);
    }

    /**
     * 删除指定树x中的key对应的value，并返回删除后的新树
     *
     * @param x
     * @param key
     * @return
     */
    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            // 新节点的key大于当前节点的key，则继续找当前节点的右子节点；
            x.right = delete(x.right, key);
        } else if (cmp < 0) {
            // 新节点的key小于当前节点的key，则继续找当前节点的左子节点；
            x.left = delete(x.left, key);
        } else {
            // 新节点的key等于当前节点的key，当前x就是要删除的节点
            // 1.如果当前节点的右子树不存在，则直接返回当前节点的左子节点
            if (x.right == null) {
                N--;
                return x.left;
            }
            // 2.如果当前节点的左子树不存在，则直接返回当前节点的右子节点
            if (x.left == null) {
                N--;
                return x.right;
            }

            // 3.当前节点的左右子树都存在
            // 3.1找到右子树中最小的节点
            Node minNode = x.right;
            while (minNode.left != null) {
                minNode = minNode.left;
            }
            // 3.2删除右子树中最小的节点
            Node n = x.right;
            while (n.left != null) {
                if (n.left.left == null) {
                    n.left = null;
                } else {
                    n = n.left;
                }
            }
            // 3.3让被删除节点的右子树称为最小节点minNode的左子树，让被删除节点
            // 右子树称为最小节点minNode的右子树。
            minNode.left = x.left;
            minNode.right = x.right;
            // 3.4让被删除节点的父节点指向最小节点minNode
            x = minNode;
            N--;
        }
        return x;
    }

    /**
     * 找出整个树中最小的键
     *
     * @return
     */
    public Key min() {
        return min(root).key;
    }

    /**
     * 找出指定树x中最小的键所在的节点
     *
     * @param x
     * @return
     */
    private Node min(Node x) {
        if (x.left != null) {
            return min(x.left);
        } else {
            return x;
        }
    }

    /**
     * 找出整个树中最大的键
     *
     * @return
     */
    public Key max() {
        return max(root).key;
    }

    /**
     * 找出指定树x中最大键所在的节点
     *
     * @param x
     * @return
     */
    private Node max(Node x) {
        if (x.right != null) {
            return max(x.right);
        } else {
            return x;
        }
    }

    /**
     * 使用前序遍历，获取整个树中的所有键
     *
     * @return
     */
    public Queue<Key> preErgodic() {
        Queue<Key> keys = new Queue<>();
        preErgodic(root, keys);
        return keys;
    }

    /**
     * 使用前序遍历，把指定树x中的所有键放入到keys队列中
     *
     * @param x
     * @param keys
     */
    private void preErgodic(Node x, Queue<Key> keys) {
        if (x == null) {
            return;
        }
        // 1.把当前节点的key放入到队列中
        keys.enqueue(x.key);
        // 2.找到当前节点的左子树，如果不为空，递归遍历左子树
        if (x.left != null) {
            preErgodic(x.left, keys);
        }
        // 3.找到当前节点的右子树，如果部位空，递归遍历右子树
        if (x.right != null) {
            preErgodic(x.right, keys);
        }
    }

    /**
     * 使用中序遍历，获取整个树中的所有键
     *
     * @return
     */
    public Queue<Key> midErgodic() {
        Queue<Key> keys = new Queue<>();
        midErgodic(root, keys);
        return keys;
    }

    /**
     * 使用中序遍历，把指定树x中的所有键放入到keys队列中
     *
     * @param x
     * @param keys
     */
    private void midErgodic(Node x, Queue<Key> keys) {
        if (x == null) {
            return;
        }

        // 1.找到当前节点的左子树，如果不为空，递归遍历左子树
        if (x.left != null) {
            midErgodic(x.left, keys);
        }
        // 2.把当前节点的key放入到队列中
        keys.enqueue(x.key);
        // 3.找到当前节点的右子树，如果不为空，递归遍历右子树
        if (x.right != null) {
            midErgodic(x.right, keys);
        }
    }

    /**
     * 使用后续遍历，获取整个树中所有的键
     *
     * @return
     */
    public Queue<Key> afterErgodic() {
        Queue<Key> keys = new Queue<>();
        afterErgodic(root, keys);
        return keys;
    }

    /**
     * 使用后续遍历，把指定树x中的所有键放入到keys队列中
     *
     * @param x
     * @param keys
     */
    private void afterErgodic(Node x, Queue<Key> keys) {
        if (x == null) {
            return;
        }

        // 1.找到当前节点的左子树，如果不为空，递归遍历左子树
        if (x.left != null) {
            afterErgodic(x.left, keys);
        }

        // 2.找到当前节点的右子树，如果不为空，递归遍历右子树
        if (x.right != null) {
            afterErgodic(x.right, keys);
        }
        // 3.把当前节点的key放入到队列中
        keys.enqueue(x.key);
    }

    /**
     * 使用层序遍历得到树中所有的键
     *
     * @return
     */
    public Queue<Key> layerErgodic() {
        Queue<Key> keys = new Queue<>();
        Queue<Node> nodes = new Queue<>();
        nodes.enqueue(root);
        while (!nodes.isEmpty()) {
            Node x = nodes.dequeue();
            keys.enqueue(x.key);
            if (x.left != null) {
                nodes.enqueue(x.left);
            }
            if (x.right != null) {
                nodes.enqueue(x.right);
            }
        }
        return keys;
    }

    /**
     * 计算整个树的最大省深度
     *
     * @return
     */
    public int maxDepth() {
        return maxDepth(root);
    }

    /**
     * 计算指定树x的最大深度
     *
     * @param x
     * @return
     */
    private int maxDepth(Node x) {
        // 1、如果根节点为空，则最大深度为0；
        if (x == null) {
            return 0;
        }
        int max = 0;
        int maxL = 0;
        int maxR = 0;
        // 2、计算左子树的最大深度
        if (x.left!=null){
            maxL = maxDepth(x.left);
        }

        // 3、计算右子树的最大深度
        if (x.right !=null){
            maxR = maxDepth(x.right);
        }

        // 4、当前树的最大省督 = 左子树的最大深度和右子树的最大深度中的较大者+1
        max = maxL > maxR ? maxL+1 : maxR+1;
        return max;
    }

    private class Node {
        // 键
        public Key key;

        // 值
        public Value value;

        // 左子节点
        public Node left;

        // 右子节点
        public Node right;

        public Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}

class BinaryTreeTest {
    public static void main(String[] args) {
        basicTest();
//        ErgodicTest();
    }

    private static void basicTest() {
        BinaryTree<Integer, String> bt = new BinaryTree<>();
        bt.put(4, "詹姆斯");
        bt.put(1, "科比");
        bt.put(3, "杜兰特");
        bt.put(5, "库里");
        bt.put(2, "东契奇");
        System.out.println("二叉树的最大深度："+bt.maxDepth());
        System.out.println(bt.size());
        bt.put(1, "乔丹");
        System.out.println(bt.get(1));
        System.out.println(bt.size());
        bt.delete(1);
        System.out.println(bt.size());
    }


    private static void ErgodicTest() {
        BinaryTree<String, String> bt = new BinaryTree<>();
        bt.put("E", "5");
        bt.put("B", "2");
        bt.put("G", "7");
        bt.put("A", "1");
        bt.put("D", "4");
        bt.put("F", "6");
        bt.put("H", "8");
        bt.put("C", "3");

        Queue<String> queue = bt.layerErgodic();
        for (String key : queue) {
            System.out.println(key + "=" + bt.get(key));
        }
    }
}
