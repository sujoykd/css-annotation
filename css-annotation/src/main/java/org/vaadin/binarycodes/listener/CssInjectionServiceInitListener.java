package org.vaadin.binarycodes.listener;

import org.vaadin.binarycodes.css.CssAnnotationProcessing;

import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

public class CssInjectionServiceInitListener implements VaadinServiceInitListener {


    @Override
    public void serviceInit(ServiceInitEvent serviceInitEvent) {
        serviceInitEvent.getSource().addUIInitListener(uiInitEvent -> {
            uiInitEvent.getUI().addAfterNavigationListener(event -> {
                event.getActiveChain().forEach(CssAnnotationProcessing::of);
            });
        });
    }


}
