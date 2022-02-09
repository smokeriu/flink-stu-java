package org.ssiu.table.ex;

import org.apache.flink.table.functions.TableFunction;

public class TF extends TableFunction<String> {
    public void eval(String arg) {
        collect(arg);
    }
}
