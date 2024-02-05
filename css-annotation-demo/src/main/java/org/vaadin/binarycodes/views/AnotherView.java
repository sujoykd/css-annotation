package org.vaadin.binarycodes.views;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.vaadin.binarycodes.css.Css;
import org.vaadin.binarycodes.css.Property;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route(value = "another", layout = MainLayout.class)
@PageTitle("Another View")
@Css(styleCss = """
        vaadin-button {
            background-color: green;
            color: white;
        }
        """,
        properties = {
                @Property(name = "background-color", value = "rgb(204 225 255 / 53%)"),
                @Property(name = "margin", value = "1rem"),
                @Property(name = "width", value = "calc(100% - 2rem)")
        },
        classNames = {LumoUtility.Padding.LARGE})
public class AnotherView extends VerticalLayout {
    private static final Logger LOG = LogManager.getLogger(AnotherView.class);

    public AnotherView() {
        add("Hello again!");
        add(new SampleViewComponent1());
        add(new SampleViewComponent2());
        add(new Button("Outer Button"));
    }

}
