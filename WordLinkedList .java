package application;

public class WordLinkedList {
	WordNode head;

    void add(String w) {
        if (!contains(w)) {
            WordNode n = new WordNode(w);
            n.next = head;
            head = n;
        }
    }

    boolean contains(String w) {
        WordNode cur = head;
        while (cur != null) {
            if (cur.word.equals(w)) return true;
            cur = cur.next;
        }
        return false;
    }

    int size() {
        int c = 0;
        WordNode cur = head;
        while (cur != null) {
            c++;
            cur = cur.next;
        }
        return c;
    }
}
