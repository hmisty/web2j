(ns demo.core
  (:gen-class)
  (:use [compojure.core :only (GET POST defroutes)])
  (:require [ring.util.response :as resp]
            [compojure.handler]
            [compojure.route]))

(defn hello2 [name]
  (str "Hello 2 " (or name "stranger")))

(defroutes handler
  (GET "/hej/:id" [id] (hello2 id))
  (GET "/hej" [] "Hej!"))

(def app
  (-> handler (compojure.handler/site)))
