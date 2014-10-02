package com.vaadin.touchkitsampler.ui;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Switch;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;

class SwitchView extends NavigationView {
    private static final long serialVersionUID = -310203104553728231L;

    protected void onBecomingVisible() {
        super.onBecomingVisible();
        setCaption("Switch toggle");
        
        VerticalComponentGroup group =
                new VerticalComponentGroup();
        Switch myswitch = new Switch("To be or not to be?");
        myswitch.setValue(true);
        group.addComponent(myswitch);
        setContent(group);
    }
}