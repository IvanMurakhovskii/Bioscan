package com.murik.lite.module;

import com.murik.lite.service.Impl.SummaryServiceImpl;

//@Module
public class SummaryModule {

//    @Provides
    public SummaryServiceImpl summaryService() {
        return new SummaryServiceImpl();
    }
}
