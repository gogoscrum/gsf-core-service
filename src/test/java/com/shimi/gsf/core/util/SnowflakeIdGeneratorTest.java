package com.shimi.gsf.core.util;

import com.shimi.gsf.util.SnowflakeIdGenerator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SnowflakeIdGeneratorTest {
    public static final Logger log = LoggerFactory.getLogger(SnowflakeIdGeneratorTest.class);

    @Test
    void testOneId() {
        long id = SnowflakeIdGenerator.nextId();
        assertTrue(id > 0);
    }

    @Test
    void testMultipleIds() {
        int size = 1000;
        List<Long> results = new ArrayList<>();
        long startTimeMillis = System.currentTimeMillis();
        long lastId = 0;

        for (int i = 0; i < size; i++) {
            long id = SnowflakeIdGenerator.nextId();
            assertTrue(id > 0);

            // Check if the generated ID is unique
            if (!CollectionUtils.isEmpty(results)) {
                assertFalse(results.contains(id));
            }

            // The new ID should always be greater than the previous ID
            if (i > 0) {
                assertTrue(id > lastId);
            }

            lastId = id;
            results.add(id);
        }

        long endTimeMillis = System.currentTimeMillis();
        long timeCost = endTimeMillis - startTimeMillis;
        log.info("Generated {} snowflake IDs in {} milliseconds", size, timeCost);
        assertTrue(timeCost < 100);
    }
}
