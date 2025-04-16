import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrieTest {

    private final Trie trie = new Trie();

    @Test
    void test() {
        try {
            trie.insert("apple");
            trie.insert("apparent");
            assertTrue(trie.search("apple"), "Search apple should be true");
            assertFalse(trie.search("app"), "Search app should be false");
            assertTrue(trie.startsWith("app"), "Starts with app should be true");
            trie.insert("app");
            assertTrue(trie.search("app"), "Search app should be true");
        } finally {
            System.out.println(trie);
        }
    }
}
