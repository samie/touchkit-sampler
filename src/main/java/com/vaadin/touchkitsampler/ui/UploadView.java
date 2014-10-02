package com.vaadin.touchkitsampler.ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.vaadin.addon.touchkit.ui.HorizontalButtonGroup;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.ProgressListener;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

class UploadView extends NavigationView {
    private static final long serialVersionUID = -310203104553728231L;

    public UploadView() {
        super("Regular Upload");
    }

    protected void onBecomingVisible() {
        super.onBecomingVisible();
        
        final VerticalComponentGroup layout =
                new VerticalComponentGroup();

        // Display the image - only a placeholder first
        final Image image = new Image();
        image.setWidth("100%");
        image.setVisible(false);
        layout.addComponent(image);

        // Implement both receiver that saves upload in a file and
        // listener for successful upload
        class ImageUploader implements Receiver, SucceededListener,
                                       ProgressListener {
            private static final long serialVersionUID = 1L;

            final static int maxLength = 10000000;
            ByteArrayOutputStream fos = null;
            String filename;
            Upload upload;
            
            public ImageUploader(Upload upload) {
                this.upload = upload;
            }
         
            public OutputStream receiveUpload(String filename,
                                              String mimeType) {
                this.filename = filename;
                fos = new ByteArrayOutputStream(maxLength + 1);
                return fos; // Return the output stream to write to
            }

            @Override
            public void updateProgress(long readBytes,
                                       long contentLength) {
                if (readBytes > maxLength) {
                    Notification.show("Too big content");
                    upload.interruptUpload();
                }
            }

            public void uploadSucceeded(SucceededEvent event) {
                // Show the uploaded file in the image viewer
                image.setSource(new StreamResource(new StreamSource() {
                    private static final long serialVersionUID = 7063779452227893200L;

                    @Override
                    public InputStream getStream() {
                        byte[] bytes = fos.toByteArray();
                        return new ByteArrayInputStream(bytes);
                    }
                }, filename));
                
                image.setVisible(true);
            }
        };

        Upload upload = new Upload();
        ImageUploader uploader = new ImageUploader(upload);
        upload.setReceiver(uploader);
        upload.addSucceededListener(uploader);
        upload.setImmediate(true); // Only button

        HorizontalButtonGroup group = new HorizontalButtonGroup();
        group.addComponent(upload);
        layout.addComponent(group);
        
        setContent(layout);
    }
}