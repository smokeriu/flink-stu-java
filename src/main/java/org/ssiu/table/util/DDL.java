package org.ssiu.table.util;

public class DDL {
    public static String kafkaIn1() {
        return "CREATE TEMPORARY TABLE KafkaIn1 (\n" +
                "  `user_id` BIGINT,\n" +
                "  `item_id` BIGINT,\n" +
                "  `behavior` STRING,\n" +
                "  `ts1` TIMESTAMP(3), \n" +
                " WATERMARK FOR ts1 as ts1" +
                ") WITH (\n" +
                "  'connector' = 'kafka',\n" +
                "  'topic' = 'user_behavior1',\n" +
                "  'properties.bootstrap.servers' = 'hadoop:9092',\n" +
                "  'properties.group.id' = 'testGroup1',\n" +
                "  'scan.startup.mode' = 'latest-offset',\n" +
                "  'format' = 'csv'\n" +
                ")";
    }

    public static String kafkaIn2() {
        return "CREATE TEMPORARY TABLE KafkaIn2 (\n" +
                "  `user_id` BIGINT,\n" +
                "  `item_id` BIGINT,\n" +
                "  `behavior` STRING,\n" +
                "  `ts2` TIMESTAMP(3), \n" +
                "   WATERMARK FOR ts2 as ts2 \n" +
                ") WITH (\n" +
                "  'connector' = 'kafka',\n" +
                "  'topic' = 'user_behavior2',\n" +
                "  'properties.bootstrap.servers' = 'hadoop:9092',\n" +
                "  'properties.group.id' = 'testGroup2',\n" +
                "  'scan.startup.mode' = 'latest-offset',\n" +
                "  'format' = 'csv'\n" +
                ")";
    }
}
