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
2. java -jar demoapp.jar
3. Configure nginx to proxy the request to :8081 and serve the static contents

### War

A war file which can be extracted by jetty and whose structure is like this:

```
$JETTY_HOME/webapps/
demoapp(.war)
 |
 +- WEB-INF
 |   +- web.xml
 |   +- classes/
 |   +- lib/
 |
 +- static/
 |
 ...
```

It is fine to just copy the files to the demoapp folder according to the structure above, instead of package a .war file then throw the file there.

web.xml is created for defining the servlets and mapping info like this:

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE web-app PUBLIC "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
						 "http://java.sun.com/dtd/web-app_2_3.dtd">
<web-app>

	<filter>
		<filter-name>helloFilter</filter-name>
		<filter-class>demo.HelloFilter</filter-class>
	</filter>

	<filter-mapping>
		<filter-name>helloFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>

	<servlet>
		<servlet-name>hello_world</servlet-name>
		<servlet-class>demo.HelloServlet</servlet-class>
		<load-on-startup>1</load-on-startup>
	</servlet>

	<servlet-mapping>
		<servlet-name>hello_world</servlet-name>
		<url-pattern>/hello</url-pattern>
	</servlet-mapping>

	<servlet>
		<servlet-name>page_servlet</servlet-name>
		<servlet-class>demo.PageServlet</servlet-class>
		<load-on-startup>1</load-on-startup>
	</servlet>

	<servlet-mapping>
		<servlet-name>page_servlet</servlet-name>
		<url-pattern>/page</url-pattern>
	</servlet-mapping>

	<servlet>
		<servlet-name>index_servlet</servlet-name>
		<servlet-class>demo.IndexServlet</servlet-class>
		<load-on-startup>1</load-on-startup>
	</servlet>

	<servlet-mapping>
		<servlet-name>index_servlet</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>

</web-app>
```

## License
(c)Copyright Evan Liu (hmisty).
Licensed with GPL v2.
