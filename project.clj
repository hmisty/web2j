(defproject simpleweb "0.1.0"
  :dependencies [[jetty/servlet-api "2.5-6.0.2"]
                 [com.github.spullara.mustache.java/compiler "0.8.14"]
                 [org.mortbay.jetty/jetty "6.1.26"]
                 [org.mortbay.jetty/jetty-util "6.1.26"]
                 [org.clojure/clojure "1.5.1"]
                 [ring "1.1.8"]
                 [compojure "1.1.5"]]
  :main demo.server
  :aot :all
  :source-paths ["src/clj"]
  :resource-paths ["src/tmpl" "src/java"] ;;for lein2-eclipse to add to src
  ;;:java-source-paths ["src/java"] ;;will be ignored by lein2-eclipse
  :plugins [[lein2-eclipse "2.0.0"]])
