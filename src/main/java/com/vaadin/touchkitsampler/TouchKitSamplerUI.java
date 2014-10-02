package com.vaadin.touchkitsampler;

import com.vaadin.addon.touchkit.annotations.CacheManifestEnabled;
import com.vaadin.addon.touchkit.annotations.OfflineModeEnabled;
import com.vaadin.addon.touchkit.extensions.OfflineMode;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.NavigationManager.NavigationEvent;
import com.vaadin.addon.touchkit.ui.NavigationManager.NavigationListener;
import com.vaadin.addon.touchkit.ui.TabBarView;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.touchkitsampler.gwt.client.TouchKitSamplerPersistToServerRpc;
import com.vaadin.touchkitsampler.ui.MainView;
import com.vaadin.touchkitsampler.ui.SlideShow;
import com.vaadin.touchkitsampler.ui.SlideShow2;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;

/**
 * The UI's "main" class
 */
@SuppressWarnings("serial")

// Use the TouchKit widget set for the TouchKit UI
@Widgetset("com.vaadin.touchkitsampler.gwt.TouchKitSamplerWidgetSet")

// Use a custom theme
@Theme("mobiletheme")

// Cache static application files so as the application can be started
// and run even when the network is down.
@CacheManifestEnabled

// Switch to the OfflineMode client UI when the server is unreachable
@OfflineModeEnabled

// Make the server retain UI state whenever the browser reloads the app
@PreserveOnRefresh
public class TouchKitSamplerUI extends UI {

    // TODO This is currently unused in the sampler
    private final TouchKitSamplerPersistToServerRpc serverRpc = new TouchKitSamplerPersistToServerRpc() {
        @Override
        public void persistToServer() {
            // TODO this method is called from client side to store offline data
        }
    };

    @Override
    protected void init(VaadinRequest request) {
        TabBarView tabBarView = new TabBarView();

        final NavigationManager manager =
                new NavigationManager(new MainView());
        manager.addNavigationListener(new NavigationListener() {
            private static final long serialVersionUID = -7331692292134082502L;

            @Override
            public void navigate(NavigationEvent event) {
                if (event.getDirection() ==
                        NavigationEvent.Direction.BACK) {
                    // Do something
                    Notification.show("You came back to " +
                        manager.getCurrentComponent().getCaption());
                }
            }
        });

        Tab tab1 = tabBarView.addTab(manager, "Main");
        tab1.setIcon(FontAwesome.LIST);

        // Swipe views
        Tab tab2 = tabBarView.addTab(new SlideShow(), "Swipe");
        tab2.setIcon(FontAwesome.FORWARD);
        Tab tab3 = tabBarView.addTab(new SlideShow2(), "Swipe 2");
        tab3.setIcon(FontAwesome.IMAGE);
        
        setContent(tabBarView);

        // Use of the OfflineMode connector is optional
        OfflineMode offlineMode = new OfflineMode();
        offlineMode.extend(this);

        // Maintain the session when the browser application closes
        offlineMode.setPersistentSessionCookie(true);

        // Define the timeout in seconds to wait when a server
        // request is sent before falling back to offline mode
        offlineMode.setOfflineModeTimeout(15);
    }
}
