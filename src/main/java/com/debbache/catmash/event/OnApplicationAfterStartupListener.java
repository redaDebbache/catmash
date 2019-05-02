package com.debbache.catmash.event;

import com.debbache.catmash.configuration.ExcludeFromTests;
import com.debbache.catmash.manager.DataCatManager;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

@ExcludeFromTests
public class OnApplicationAfterStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    private final DataCatManager dataCatManager;

    public OnApplicationAfterStartupListener(DataCatManager dataCatManager) {
        this.dataCatManager = dataCatManager;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        dataCatManager.dowloadCats();
    }
}
