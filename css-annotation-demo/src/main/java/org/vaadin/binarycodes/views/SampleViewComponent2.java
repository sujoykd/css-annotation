package org.vaadin.binarycodes.views;

import org.vaadin.binarycodes.css.Css;
import org.vaadin.binarycodes.css.CssBeforeEnterObserver;
import org.vaadin.binarycodes.css.Property;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexLayout;


@Css(styleCss = """
        vaadin-button {
            background-color: red;
            color: white;
        }
        """,
        properties = {
                @Property(name = "padding", value = "3rem"),
                @Property(name = "width", value = "100%")
        }
)
public class SampleViewComponent2 extends FlexLayout implements CssBeforeEnterObserver {
    public SampleViewComponent2() {
        add(new Button("sample2 view button"));
    }
}
