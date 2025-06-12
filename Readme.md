
 
# Guice Serve

Use the property `guice.version` to specify the Guice version you want to use.
It's also possible to configure the port with the `port` property and run two applications on different ports and different Guice versions.

```shell
    mvn clean compile jetty:run -Dport=10003 -Dguice.version=3.0
``` 

```shell
    mvn clean compile jetty:run -Dport=10006 -Dguice.version=6.0.0
```
