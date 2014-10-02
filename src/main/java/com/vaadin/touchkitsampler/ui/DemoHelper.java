package com.vaadin.touchkitsampler.ui;

// BEGIN-EXAMPLE: mobile.overview.phone
import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.NavigationManager.NavigationEvent;
import com.vaadin.addon.touchkit.ui.NavigationManager.NavigationListener;
import com.vaadin.addon.touchkit.ui.TabBarView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.touchkitsampler.data.Planet;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class DemoHelper {
    private static final long serialVersionUID = 511085335415683713L;

    public static BeanItemContainer<Planet> planetData() {
        BeanItemContainer<Planet> container =
                new BeanItemContainer<Planet>(Planet.class);
        
        Planet planets[] = {
                new Planet("Mercury",  0.3871, 0),
                new Planet("Venus",    0.7233, 0),
                new Planet("Earth",    1.0000, 1),
                new Planet("Mars",     1.5237, 2),
                new Planet("Jupiter",  5.2028, 63),
                new Planet("Saturn",   9.5388, 62),
                new Planet("Uranus",  19.1914, 27),
                new Planet("Neptune", 30.0611, 13)};
        container.addAll(Lists.newArrayList(planets));
        return container;
    }
}
// END-EXAMPLE: mobile.overview.phone
