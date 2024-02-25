package com.murik.lite.configuration;

import com.murik.lite.module.SummaryModule;

//import dagger.Component;

//@Component(modules = {SummaryModule.class})
public interface ApplicationComponent {
    public SummaryModule getSummaryModule();
}
