TouchKitSampler Vaadin TouchKit app skeleton created by maven archetype
===========

The project is a standard Maven web app project and should thus
be compatible with IDE's supporting Maven. The application stub
contains usage examples of some basic components and also provides a
method to serve different UI for desktop browsers.

### Usage

Make sure you have installed [Maven](http://maven.apache.org/) and [Git](http://git-scm.com/).

    git clone https://github.com/vaadin-samples/touchkit-sampler.git
    cd hello-world
    mvn vaadin:compile jetty:run
    
After these steps you have the application up and running at http://localhost:8080/. Future starts require only ```mvn jetty:run``` as the widgetset is already compiled.

## Debugging the client-side code

If you are modifying client-side code and you want to debug it using
the gwt devmode, open one terminal an run the command ```mvn vaadin:run```

Then open another terminal and run: ```mvn jetty:run```

Finally open your browser and open the url http://localhost:8080/?gwt.codesvr=127.0.0.1:9997

### License

This sample is open source and licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).
