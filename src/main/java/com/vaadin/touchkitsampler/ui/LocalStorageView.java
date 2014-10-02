package com.vaadin.touchkitsampler.ui;

import com.vaadin.addon.touchkit.extensions.LocalStorage;
import com.vaadin.addon.touchkit.extensions.LocalStorageCallback;
import com.vaadin.addon.touchkit.ui.HorizontalButtonGroup;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

class LocalStorageView extends NavigationView {
    private static final long serialVersionUID = -310203104553728231L;

    public LocalStorageView() {
        super("Local Storage");
    }

    protected void onBecomingVisible() {
        super.onBecomingVisible();
        
        final VerticalComponentGroup vertical =
                new VerticalComponentGroup();
        final TextField valueEditor = new TextField("Value");
        valueEditor.setNullRepresentation("");
        vertical.addComponent(valueEditor);

        final LocalStorage storage = LocalStorage.get();
        storage.get("value", new LocalStorageCallback() {
            private static final long serialVersionUID = 7842668649729234419L;

            @Override
            public void onSuccess(String value) {
                valueEditor.setValue(value);
                Notification.show("Value Retrieved");
            }

            @Override
            public void onFailure(FailureEvent error) {
                Notification.show("Failed because: " +
                    error.getMessage());
            }
        });

        HorizontalButtonGroup buttons =
                new HorizontalButtonGroup();
        buttons.addComponent(new Button("Store", new ClickListener() {
            private static final long serialVersionUID = 5477613264771189859L;

            @Override
            public void buttonClick(ClickEvent event) {
                storage.put("value", valueEditor.getValue(),
                            new LocalStorageCallback() {
                    private static final long serialVersionUID = 5999537256431186976L;

                    @Override
                    public void onSuccess(String value) {
                        Notification.show("Stored");
                    }
                    
                    @Override
                    public void onFailure(FailureEvent error) {
                        Notification.show("Storing Failed");
                    }
                });
            }
        }));
        vertical.addComponent(buttons);

        setContent(vertical);
    }
}