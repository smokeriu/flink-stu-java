package org.ssiu.table.ch1;

import org.apache.flink.table.api.*;
import org.apache.flink.connector.datagen.table.DataGenConnectorOptions;

import javax.swing.plaf.nimbus.State;

import static org.apache.flink.table.api.Expressions.*;

public class FirstTableApi {
    public static void main(String[] args) {
        EnvironmentSettings settings = EnvironmentSettings
                .newInstance()
                .inStreamingMode()
                .build();

        TableEnvironment tEnv = TableEnvironment.create(settings);


        final TableDescriptor sourceDescriptor = TableDescriptor.forConnector("datagen")
                .schema(Schema.newBuilder()
                        .column("f0", DataTypes.STRING())
                        .build())
                .option(DataGenConnectorOptions.ROWS_PER_SECOND, 100L)
                .build();




        tEnv.createTable("source_tb", sourceDescriptor);



        final Table sourceTb = tEnv.from("source_tb");


        final Table orders = sourceTb.addColumns($("f0").rowtime());


        final Schema schema = Schema.newBuilder().fromResolvedSchema(orders.getResolvedSchema()).build();

        // create output
        TableDescriptor sinkDescriptor = TableDescriptor.forConnector("print")
                .schema(schema)
                .build();
        tEnv.createTable("print_table", sinkDescriptor);

        orders.executeInsert("print_table");

    }
}
