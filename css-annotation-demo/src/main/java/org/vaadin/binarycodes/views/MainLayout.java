package org.vaadin.binarycodes.views;

import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouteData;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainLayout extends AppLayout {
    public MainLayout() {
        prepareNavbar();
        prepareDrawer();
    }

    private void prepareNavbar() {
        DrawerToggle toggle = new DrawerToggle();
        H1 title = new H1("Demo");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
        addToNavbar(toggle, title);
    }

    private void prepareDrawer() {
        SideNav nav = prepareNavigations();
        Scroller scroller = new Scroller(nav);
        scroller.setClassName(LumoUtility.Padding.SMALL);
        addToDrawer(scroller);
    }

    private SideNav prepareNavigations() {
        SideNav sideNav = new SideNav();
        List<RouteData> availableRoutes = RouteConfiguration.forSessionScope()
                .getAvailableRoutes();
        for (var route : availableRoutes) {
            var title = pageTitle(route.getNavigationTarget());
            sideNav.addItem(new SideNavItem(title, route.getNavigationTarget()));
        }
        return sideNav;
    }


    private String pageTitle(Class<? extends Component> page) {
        var pageTitle = page.getAnnotation(PageTitle.class);
        return pageTitle != null ? pageTitle.value() : page.getCanonicalName();
    }
}
