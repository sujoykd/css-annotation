package org.vaadin.binarycodes.initializer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.binarycodes.css.Css;

import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

public class CssInjectionServiceInitListener implements VaadinServiceInitListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(CssInjectionServiceInitListener.class);

    @Override
    public void serviceInit(ServiceInitEvent serviceInitEvent) {
        serviceInitEvent.getSource().addUIInitListener(uiInitEvent -> {
            uiInitEvent.getUI().addAfterNavigationListener(event -> {
                var viewList = event.getActiveChain();
                viewList.forEach(view -> {
                    final Css css = view.getClass().getAnnotation(Css.class);
                    if (css != null) {
                        processAnnotationForComponent(view, css);
                    }
                });
            });
        });
    }

    private void processAnnotationForComponent(final HasElement component, final Css css) {
        for (final var prop : css.properties()) {
            component.getElement().getStyle().set(prop.name(), prop.value());
        }

        for (final var className : css.classNames()) {
            component.getElement().getClassList().add(className);
        }

        var styleCss = css.styleCss();
        if (StringUtils.isNotEmpty(styleCss)) {
            addStyleContentToComponent(component, styleCss);
        }

        final var styleCssPath = css.styleCssPath();
        if (StringUtils.isNotEmpty(styleCssPath)) {
            try (final var stream = this.getClass().getResourceAsStream(styleCssPath)) {
                if (stream != null) {
                    addStyleContentToComponent(component, stream.readAllBytes());
                } else {
                    LOGGER.error("cannot find resource {} in classpath", styleCssPath);
                }
            } catch (IOException ex) {
                LOGGER.error("exception applying styleCssPath", ex);
            }
        }
    }

    private void addStyleContentToComponent(final HasElement component, final String styleContent) {
        final var styleEl = new Element("style");
        styleEl.setText(styleContent);
        component.getElement().appendChild(styleEl);
    }

    private void addStyleContentToComponent(final HasElement component, final byte[] styleContent) {
        final var content = new String(styleContent, StandardCharsets.UTF_8);
        addStyleContentToComponent(component, content);
    }

}
