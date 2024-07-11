package com.example.writeBackCachePractice.common.config;

import com.github.f4b6a3.tsid.TsidCreator;

public class TsidGenerator {

    public String generateTsid() {
        return TsidCreator.getTsid().toString();
    }
}
