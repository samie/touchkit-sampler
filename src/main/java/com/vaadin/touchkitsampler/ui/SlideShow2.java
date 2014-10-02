package com.vaadin.touchkitsampler.ui;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.SwipeView;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;

public class SlideShow2 extends SlideShow {
    private static final long serialVersionUID = -707028635734423916L;

    /* Create a view by its position index */
    @Override
    SwipeView createView(int pos) {
        SwipeView swipe = new SwipeView();
        swipe.setSizeFull();

        // Use an inner layout to center the image
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();

        Image image = new Image(null, new ThemeResource(
                "img/planets/" + imageNames[pos]));
        layout.addComponent(image);
        layout.setComponentAlignment(image,
            Alignment.MIDDLE_CENTER);

        NavigationView view = new NavigationView(layout);
        view.getNavigationBar().setCaption(imageNames[pos]);
        swipe.setContent(view);
        return swipe;
    }
}