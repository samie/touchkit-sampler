package com.vaadin.touchkitsampler.ui;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.addon.touchkit.ui.NavigationButton.NavigationButtonClickEvent;
import com.vaadin.addon.touchkit.ui.NavigationButton.NavigationButtonClickListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ThemeResource;
import com.vaadin.touchkitsampler.data.Planet;
import com.vaadin.ui.Image;
import com.vaadin.ui.TextField;

class NaviButtonView extends NavigationView {
    private static final long serialVersionUID = -310203104553728231L;
    
    public NaviButtonView() {
        super("Navigation Button");
    }

    protected void onBecomingVisible() {
        super.onBecomingVisible();

        class PlanetDetails extends NavigationView {
            private static final long serialVersionUID = -3036154002273858614L;

            BeanItem<Planet> planetItem;
            public PlanetDetails(BeanItem<Planet> planetItem) {
                super(planetItem.getBean().getName());
                this.planetItem = planetItem;
            }
            
            @Override
            protected void onBecomingVisible() {
                super.onBecomingVisible();

                VerticalComponentGroup group =
                        new VerticalComponentGroup();
                
                Planet planet = planetItem.getBean();
                group.addComponent(new Image(null, new ThemeResource(
                    "../book-examples/img/planets/" +
                    planet.getName() + ".jpg")));
                group.addComponent(new TextField("Name",
                    planetItem.getItemProperty("name")));
                group.addComponent(new TextField("Orbit",
                    planetItem.getItemProperty("orbitRadius")));
                group.addComponent(new TextField("Moons",
                    planetItem.getItemProperty("moons")));
                
                setContent(group);
            }
        }
        
        VerticalComponentGroup group =
                new VerticalComponentGroup();

        final BeanItemContainer<Planet> planets = DemoHelper.planetData();
        for (final Planet planet: planets.getItemIds()) {
            final NavigationButton planetNav =
                    new NavigationButton(planet.getName());
            planetNav.setTargetViewCaption(planet.getName());
            planetNav.setIcon(new ThemeResource(
                "../book-examples/img/planets/" + planet.getName() + "_symbol.png"));
            if (planet.getMoons() > 0) {
                planetNav.setDescription(planet.getMoons() + " moons");
                if (planet.getMoons() == 1)
                    planetNav.addStyleName("emphasis");
                if (planet.getMoons() == 2)
                    planetNav.addStyleName("pill");
            }

            // Create view dynamically
            planetNav.addClickListener(
                new NavigationButtonClickListener() {
                private static final long serialVersionUID = -8172534021981013020L;

                @Override
                public void buttonClick(NavigationButtonClickEvent event) {
                    planetNav.getNavigationManager().
                        navigateTo(new PlanetDetails(planets.getItem(planet)));
                }
            });
            
            group.addComponent(planetNav);
        }
        
        setContent(group);
    }
}