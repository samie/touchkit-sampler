package com.vaadin.touchkitsampler.ui;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.SwipeView;
import com.vaadin.addon.touchkit.ui.NavigationManager.NavigationListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;

public class SlideShow extends NavigationManager
      implements NavigationListener {
    private static final long serialVersionUID = -707028494174423916L;

    String imageNames[] = {"Mercury.jpg", "Venus.jpg",
        "Earth.jpg", "Mars.jpg", "Jupiter.jpg",
        "Saturn.jpg", "Uranus.jpg", "Neptune.jpg"};
    int pos = 0;

    public SlideShow() {
        addStyleName("slideshow");
        
        // Set up the initial views
        navigateTo(createView(pos));
        setNextComponent(createView(pos+1));
        
        addNavigationListener(this);
    }

    // Update the next or previous view after a swipe
    @Override
    public void navigate(NavigationEvent event) {
        switch (event.getDirection()) {
            case FORWARD:
                if (++pos < imageNames.length-1)
                    setNextComponent(createView(pos+1));
                break;
            case BACK:
                if (--pos > 0)
                    setPreviousComponent(createView(pos-1));
        }
    }

    /* Create a view by its position index */
    SwipeView createView(int pos) {
        SwipeView view = new SwipeView();
        view.setSizeFull();
        
        // Use an inner layout to center the image
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();

        Image image = new Image(null, new ThemeResource(
                "img/planets/" + imageNames[pos]));
        layout.addComponent(image);
        layout.setComponentAlignment(image,
            Alignment.MIDDLE_CENTER);

        view.setContent(layout);
        return view;
    }
}