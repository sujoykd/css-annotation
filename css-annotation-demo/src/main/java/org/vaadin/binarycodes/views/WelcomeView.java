package org.vaadin.binarycodes.views;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.vaadin.binarycodes.css.Css;
import org.vaadin.binarycodes.css.Property;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Welcome")
@Css(properties = {
        @Property(name = "background-color", value = "#e7e7e7"),
        @Property(name = "margin", value = "1rem"),
        @Property(name = "width", value = "calc(100% - 2rem)")
}, classNames = {
        LumoUtility.Padding.XLARGE
}, styleCssPath = "/welcomeview.css")
public class WelcomeView extends VerticalLayout {
    private static final Logger LOG = LogManager.getLogger(WelcomeView.class);

    public WelcomeView() {
        add(new Span("Hello World!"));
    }

}
