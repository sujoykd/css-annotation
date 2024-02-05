package org.vaadin.binarycodes.css;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.dom.Element;

public final class CssAnnotationProcessing {
    private static final String SCOPED_TEMPLATE = """
            @scope   {
                %s
            }
            """;
    private static final Logger LOGGER = LoggerFactory.getLogger(CssAnnotationProcessing.class);

    private CssAnnotationProcessing() {
        // do nothing
    }

    public static void of(final HasElement component) {
        final Css css = component.getClass().getAnnotation(Css.class);
        if (css != null) {
            processCssForComponent(component, css);
        }
    }

    private static void processCssForComponent(final HasElement component, final Css css) {
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
            try (final var stream = CssAnnotationProcessing.class.getResourceAsStream(styleCssPath)) {
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

    private static void addStyleContentToComponent(final HasElement component, final String styleContent) {
        var styleToInject = String.format(SCOPED_TEMPLATE, styleContent);
        final var styleEl = new Element("style");
        styleEl.setText(styleToInject);
        component.getElement().appendChild(styleEl);
    }

    private static void addStyleContentToComponent(final HasElement component, final byte[] styleContent) {
        final var content = new String(styleContent, StandardCharsets.UTF_8);
        addStyleContentToComponent(component, content);
    }
}
