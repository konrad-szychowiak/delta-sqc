package pl.put.poznan.sqc.domain.translation;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HashMapJSONTest {
    @Test
    void stringify() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("hello", 1);

        var stringifier = new HashMapJSON<String,Integer>(hashMap).stringify();

        assertThat(stringifier)
            .isEqualTo("{\"hello\":1}");
    }
}