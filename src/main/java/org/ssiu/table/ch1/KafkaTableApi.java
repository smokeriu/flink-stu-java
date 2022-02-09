package org.ssiu.table.ch1;

import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.*;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.expressions.AggregateExpression;
import org.apache.flink.table.functions.TemporalTableFunction;
import org.apache.flink.table.planner.plan.rules.logical.DecomposeGroupingSetsRule;
import org.ssiu.table.util.DDL;

import static org.apache.flink.table.api.Expressions.*;

public class KafkaTableApi {
    public static void main(String[] args) {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        env.setMaxParallelism(1);
        env.setParallelism(1);

        final StreamTableEnvironment tEnv = StreamTableEnvironment.create(env);

        tEnv.executeSql(DDL.kafkaIn1());
        tEnv.executeSql(DDL.kafkaIn2());


        final Table out = tEnv.sqlQuery("select user_id,item_id,count(1) cnt,group_id() id1, grouping(user_id, item_id) id2,grouping_id(item_id, user_id) id3 from KafkaIn1 group by cube(user_id,item_id)");


        final Schema outSchema = Schema.newBuilder()
                .fromResolvedSchema(out.getResolvedSchema())
                .build();
        final TableDescriptor sinkDescriptor = TableDescriptor.forConnector("print")
                .schema(outSchema).build();

        tEnv.createTable("outTb", sinkDescriptor);

        out.executeInsert("outTb");
    }
}
