package com.boxtrotstudio.aws;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogParameters {
    private List<String> paths = new ArrayList<>();

    public LogParameters() { }

    public LogParameters(List<String> paths) {
        this.paths = paths;
    }

    public LogParameters(File... paths) {
        this.paths = Lists.transform(Arrays.asList(paths), new Function<File, String>() {
            @Override
            public String apply(File file) {
                return file.getAbsolutePath();
            }
        });
    }

    public List<String> getPaths() {
        return paths;
    }
}
