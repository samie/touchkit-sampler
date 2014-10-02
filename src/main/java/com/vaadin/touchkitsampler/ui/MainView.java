package com.vaadin.touchkitsampler.ui;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationButton.NavigationButtonClickEvent;
import com.vaadin.addon.touchkit.ui.NavigationButton.NavigationButtonClickListener;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.CssLayout;

public class MainView extends NavigationView {
    private static final long serialVersionUID = -7970887918515739005L;

    public MainView() {
        super("Vaadin TouchKit Sampler");
        CssLayout content = new CssLayout();
        setContent(content);

        // TouchKit Components group
        {
            VerticalComponentGroup group =
                new VerticalComponentGroup("TouchKit Components");
            group.setWidth("100%");
            
            // Navigation to sub-views
            
            // Get the button caption for the view caption
            group.addComponent(new NavigationButton(new PopoverView()));
    
            // Set the button caption explicitly
            group.addComponent(new NavigationButton("Decorated Popover",
                                   new DecoratedPopoverView()));
    
            NavigationButton toNavButton = new NavigationButton(new NaviButtonView());
            toNavButton.setDescription("New");
            toNavButton.addStyleName("pill");
            group.addComponent(toNavButton);
            
            // Override default target view caption
            NavigationButton toSwitch = new NavigationButton("Switch");
            toSwitch.setTargetView(new SwitchView());
            toSwitch.setTargetViewCaption("Switch toggle");
            group.addComponent(toSwitch);
    
            // Create the view object dynamically
            final NavigationButton fieldViewButton = new NavigationButton();
            fieldViewButton.setTargetViewCaption("Text Input Fields");
            fieldViewButton.addClickListener(
                new NavigationButtonClickListener() {
                private static final long serialVersionUID = 5744966310815951435L;
    
                @Override
                public void buttonClick(NavigationButtonClickEvent event) {
                    fieldViewButton.getNavigationManager()
                        .navigateTo(new FieldView());
                }
            });
            group.addComponent(fieldViewButton);
            
            group.addComponent(new NavigationButton(new HBGView()));
    
            content.addComponent(group);
        }

        // TouchKit Features group
        {
            VerticalComponentGroup features =
                    new VerticalComponentGroup("Features");
            features.addComponent(new NavigationButton(new LocalStorageView()));
            features.addComponent(new NavigationButton(new UploadView()));
            features.addComponent(new NavigationButton(new ImageUploadView()));
            features.addComponent(new NavigationButton(new ThemeingView()));
            features.addComponent(new NavigationButton(new DownloadView()));
            content.addComponent(features);
        }
    }
    
    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
    }
}