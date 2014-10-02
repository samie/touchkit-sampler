package com.vaadin.touchkitsampler.ui;

import com.vaadin.addon.touchkit.ui.HorizontalButtonGroup;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;

public class HBGView extends NavigationView {
    private static final long serialVersionUID = -310203104553728231L;

    public HBGView() {
        super("Horizontal Button Group");
    }

    protected void onBecomingVisible() {
        super.onBecomingVisible();

        VerticalComponentGroup vertical =
                new VerticalComponentGroup();
        vertical.addComponent(new TextField("Name"));

        HorizontalButtonGroup buttons =
                new HorizontalButtonGroup();
        buttons.addComponent(new Button("OK"));
        buttons.addComponent(new Button("Cancel"));
        vertical.addComponent(buttons);

        setContent(vertical);
    }
}