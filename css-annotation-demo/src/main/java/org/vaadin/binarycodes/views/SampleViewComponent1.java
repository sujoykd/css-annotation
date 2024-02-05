package org.vaadin.binarycodes.views;

import org.vaadin.binarycodes.css.Css;
import org.vaadin.binarycodes.css.CssBeforeEnterObserver;
import org.vaadin.binarycodes.css.Property;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexLayout;


@Css(styleCss = """
        vaadin-button {
            background-color: blue;
            color: white;
        }
        """,
        properties = {
                @Property(name = "padding", value = "3rem"),
                @Property(name = "width", value = "100%")
        }
)
public class SampleViewComponent1 extends FlexLayout implements CssBeforeEnterObserver {
    public SampleViewComponent1() {
        add(new Button("sample1 view button"));
    }
}
