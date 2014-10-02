package com.vaadin.touchkitsampler;

import com.vaadin.addon.touchkit.annotations.CacheManifestEnabled;
import com.vaadin.addon.touchkit.annotations.OfflineModeEnabled;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is served for browsers that don't support TouchKit.
 */
@SuppressWarnings("serial")

// Disable browser caching the app for running it when offline
@CacheManifestEnabled(false)

// Prevent showing OfflineMode client UI if network fails
@OfflineModeEnabled(false)
public class TouchKitSamplerFallbackUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

        layout.addComponent(new Label("You seem to be using a desktop browser."));
        
        Button button = new Button("Continue anyway.", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getSession().setAttribute("mobile", true);
                getUI().getPage().reload();
            }
        });
        button.addStyleName("link");
        layout.addComponent(button);

        setContent(layout);
    }
}
