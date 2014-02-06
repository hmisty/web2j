(ns demo.server
  (:use [demo.core :only (app)])
  (:require [ring.adapter.jetty :as jetty]))

(defn -main [& [port]]
  (let [port (if port (Integer/parseInt port) 8081)]
    (jetty/run-jetty #'app {:join? false :port port})))
