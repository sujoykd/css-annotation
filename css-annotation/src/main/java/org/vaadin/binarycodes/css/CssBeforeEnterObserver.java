package org.vaadin.binarycodes.css;

import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

public interface CssBeforeEnterObserver extends BeforeEnterObserver, HasElement {
    default void beforeEnter(BeforeEnterEvent event) {
        CssAnnotationProcessing.of(this);
    }
}
