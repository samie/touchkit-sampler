package com.vaadin.touchkitsampler.ui;

import java.util.Collection;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Button.ClickEvent;

public class DecoratedPopoverView extends NavigationView {

    private static final long serialVersionUID = 3750679255269899661L;

    Table table = new Table("Planets", DemoHelper.planetData());

    public DecoratedPopoverView() {
        super("Decorated Popover View");
        
        CssLayout content = new CssLayout();

        content.addComponent(new Label("This view demonstrates the Popover component"));
        table.setWidth("100%");
        table.setPageLength(table.size());
        content.addComponent(table);
        
        table.setSelectable(true);
        table.setImmediate(true);
        table.addItemClickListener(new ItemClickListener() {
            private static final long serialVersionUID = -6439972483179810841L;

            @SuppressWarnings("unchecked")
            @Override
            public void itemClick(ItemClickEvent event) {
                Object itemId = event.getItemId();
                
                // Show the details in a decorated Popover
                class DetailsPopover extends Popover
                      implements Button.ClickListener {
                    private static final long serialVersionUID = 1L;

                    public DetailsPopover(Table table, Object itemId) {
                        setWidth("350px");
                        setHeight("65%");
                        
                        // Have some details to display
                        FormLayout layout = new FormLayout();
                        for (String pid: (Collection<String>) table.getContainerPropertyIds()) {
                            Label label = new Label(table.getContainerProperty(itemId, pid));
                            label.setCaption(table.getColumnHeader(pid));
                            layout.addComponent(label);
                        }
                        layout.setMargin(true);

                        // Decorate with a navigation view
                        NavigationView content = new NavigationView(layout);
                        content.setCaption("Details");
                        setContent(content);

                        // Have a close button
                        Button close = new Button(null, this);
                        close.setStyleName("close-popover");
                        close.setIcon(new ThemeResource("img/close64.png"));
                        content.setRightComponent(close);
                    }

                    public void buttonClick(ClickEvent event) {
                        close();
                    }
                }

                DetailsPopover popover = new DetailsPopover(table, itemId);
                popover.showRelativeTo(getNavigationBar());
            }
        });
        
        setContent(content);
    }

    protected void onBecomingVisible() {
        super.onBecomingVisible();
        
        table.select(null);
    }
}