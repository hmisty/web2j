# web2j

web2j follows the No Framework Principle (NFP), hence it is not yet another Java web framework.

web2j gives you a flavor of how to develop Java web app with mixing with clojure.

Also it shows the double entries to the app, either from Java or from clojure.

Why? Entry from Java makes it looks like a Java web project extended with clojure servlets. The approach may enable you to answer your boss that you are coding a Java web project with some extensions written by some hosted language.

## No Framework Principle (NFP)

Using a framework is about inheriting.

NFP is about composing.

## Double Entries

```
eclipse:
+-------------------------+                                        +------------------+
| java web server (jetty) |----- servlet mapping --( /somepath )-->| demo.SomeServlet |
+-------------------------+             | ( / )                    +------------------+
                                        | 
                              +---------v---------+
                              | demo.IndexServlet |
                              +---------+---------+
lein:                                   |
+-------------------------+   +---------v---------+
| clj web server (jetty)  |-->|   demo.core/app   |--> ring/compoojure/...
+-------------------------+   +-------------------+
```

## Project Structure

```
project
project.clj .project .classpath
 |
 +- src
 |   +- clj
 |   +- java
 |
 +- tmpl
 |   +- *.mustache 
 |
 +- static
 |   +- css/
 |   +- js/
 |   +- img/
 |
 +- target
 |   +- classes/
 |
 ...
```

## Development

Develop clojure servlets first.

Then lein eclipse to generate the eclipse project. And lein compile to generate the class files of the servlets for Java code to refer.

At last, develop the Java servlets.

## Packaging and Deployment

### Standalone Jar

Eclipse is perfect for exporting the project to a standalone jar.

File structure after being exported:
```
demoapp.jar (exported)
demoapp_lib (exported)
static      (to be shipped together)
```
The beauty here is all needed jars are copied from ~/.m2/repositories/ to demoapp\_lib and will be automatically added to CLASSPATH when executing demoapp.jar .

Deployment:

1. Ship the 3 items above to the server and place them somewhere e.g. /srv/demoapp/
2. java -jar demoap.jar
3. Configure nginx to proxy the request to :8081 and serve the static contents

### War

