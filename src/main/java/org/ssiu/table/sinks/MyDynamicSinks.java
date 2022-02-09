package org.ssiu.table.sinks;

import org.apache.flink.table.connector.ChangelogMode;
import org.apache.flink.table.connector.sink.DynamicTableSink;
import org.apache.flink.table.connector.sink.abilities.SupportsOverwrite;
import org.apache.flink.table.connector.sink.abilities.SupportsPartitioning;
import org.apache.flink.table.connector.sink.abilities.SupportsWritingMetadata;
import org.apache.flink.table.types.DataType;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liushengwei
 */
public class MyDynamicSinks implements DynamicTableSink, SupportsOverwrite , SupportsPartitioning, SupportsWritingMetadata {

    @Override
    public void applyOverwrite(boolean overwrite) {
        System.out.println("call overwrite");
    }

    @Override
    public void applyStaticPartition(Map<String, String> partition) {
        System.out.println("call applyStaticPartition");
    }

    @Override
    public Map<String, DataType> listWritableMetadata() {
        final Map<String, DataType> metadataMap = new LinkedHashMap<>();
        System.out.println("call listWritableMetadata");
        return metadataMap;
    }

    @Override
    public void applyWritableMetadata(List<String> metadataKeys, DataType consumedDataType) {
        System.out.println("call applyWritableMetadata with:");
    }

    @Override
    public ChangelogMode getChangelogMode(ChangelogMode requestedMode) {
        return null;
    }

    @Override
    public SinkRuntimeProvider getSinkRuntimeProvider(Context context) {
        return null;
    }

    @Override
    public DynamicTableSink copy() {
        return null;
    }

    @Override
    public String asSummaryString() {
        return null;
    }
}
