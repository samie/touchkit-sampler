package com.vaadin.touchkitsampler.ui;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

class ThemeingView extends NavigationView {
    private static final long serialVersionUID = -310203104553728231L;

    public ThemeingView() {
        super("Themeing");
    }

    protected void onBecomingVisible() {
        super.onBecomingVisible();
        
        CssLayout layout = new CssLayout();
        
        Label label = new Label("Check this style!");
        label.addStyleName("stylishlabel");
        layout.addComponent(label);
        
        setContent(layout);
    }
}