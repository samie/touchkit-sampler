package com.vaadin.touchkitsampler.ui;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Link;

public class DownloadView extends NavigationView {
    private static final long serialVersionUID = -310203104553728231L;

    public DownloadView() {
        super("Download");
    }

    protected void onBecomingVisible() {
        super.onBecomingVisible();

        VerticalComponentGroup vertical =
                new VerticalComponentGroup();
        Link link = new Link("Click here to download PDF",
            new ThemeResource("../book-examples/pdfexample.pdf"));
        vertical.addComponent(link);

        setContent(vertical);
    }
}