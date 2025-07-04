package com.shimi.gsf.util;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serial;
import java.io.Serializable;
import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.util.Enumeration;

/**
 * SnowflakeIdGenerator is a custom ID generator that generates unique IDs based on the Snowflake algorithm.
 * It uses a combination of timestamp, data center ID, and node ID to create a unique 64-bit integer ID.
 * This ID is suitable for distributed systems and can be used as a primary key in databases.
 * It is thread-safe and can generate IDs at a high rate.
 */
public class SnowflakeIdGenerator implements IdentifierGenerator {
    @Serial
    private static final long serialVersionUID = 1553757058989689684L;
    private static final Logger log = LoggerFactory.getLogger(SnowflakeIdGenerator.class);

    // Custom Epoch (January 1, 2015 Midnight UTC = 2015-01-01T00:00:00Z)
    private static final long EPOCH = 1420070400000L;
    private static final long NODE_ID_BITS = 5L;
    private static final long DATA_CENTER_ID_BITS = 5L;
    private static final long MAX_NODE_ID = ~(-1L << NODE_ID_BITS);
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);
    private static final long SEQUENCE_BITS = 12L;
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + NODE_ID_BITS;
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + NODE_ID_BITS + DATA_CENTER_ID_BITS;
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    /**
     * Node ID(0~31)
     */
    private static long nodeId = generateWorkId();

    /**
     * Data center ID(0~31)
     */
    private static long datacenterId = 0L;
    private static long sequence = 0L;
    private static long lastTimestamp = -1L;


    public SnowflakeIdGenerator() {
    }

    private SnowflakeIdGenerator(long nodeId, long datacenterId) {
        if (nodeId > MAX_NODE_ID || nodeId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", MAX_NODE_ID));
        }
        if (datacenterId > MAX_DATA_CENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", MAX_DATA_CENTER_ID));
        }
        SnowflakeIdGenerator.nodeId = nodeId;
        SnowflakeIdGenerator.datacenterId = datacenterId;
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        return nextId();
    }

    /**
     * Generate a new ID, this method is thread-safe.
     * @return a new ID
     */
    public static synchronized long nextId() {
        long timestamp = System.currentTimeMillis();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = waitForNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - EPOCH) << TIMESTAMP_LEFT_SHIFT)
                | (datacenterId << DATA_CENTER_ID_SHIFT)
                | (nodeId << WORKER_ID_SHIFT)
                | sequence;
    }

    protected static long waitForNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    private static long generateWorkId() {
        long nodeId;
        try {
            StringBuilder sb = new StringBuilder();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                byte[] mac = networkInterface.getHardwareAddress();
                if (mac != null) {
                    for (byte b : mac) {
                        sb.append(String.format("%02X", b));
                    }
                }
            }
            nodeId = sb.toString().hashCode();
        } catch (Exception ex) {
            nodeId = (new SecureRandom().nextInt());
        }
        nodeId = nodeId & MAX_NODE_ID;
        if (log.isDebugEnabled()) {
            log.debug("Snowflake worker ID {} initialized", nodeId);
        }
        return nodeId;
    }
}
