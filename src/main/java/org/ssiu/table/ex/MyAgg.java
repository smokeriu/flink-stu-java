package org.ssiu.table.ex;

import org.apache.flink.table.expressions.AggregateExpression;
import org.apache.flink.table.expressions.CallExpression;
import org.apache.flink.table.expressions.FieldReferenceExpression;
import org.apache.flink.table.functions.FunctionDefinition;
import org.apache.flink.table.types.DataType;

import javax.annotation.Nullable;
import java.util.List;

public class MyAgg extends AggregateExpression {
    public MyAgg(FunctionDefinition functionDefinition, List<FieldReferenceExpression> args, @Nullable CallExpression filterExpression, DataType resultType, boolean distinct, boolean approximate, boolean ignoreNulls) {
        super(functionDefinition, args, filterExpression, resultType, distinct, approximate, ignoreNulls);
    }
}
