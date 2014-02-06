(ns demo.HejServlet
  (:gen-class :extends javax.servlet.http.HttpServlet)
  (:use [compojure.core :only (GET POST defroutes)])
  (:require [ring.util.response :as resp]
            [ring.util.servlet :as servlet]
            [compojure.handler]
            [compojure.route]))

(defn hello2 [name]
  (str "Hello 2 " (or name "stranger")))

(defroutes handler
  (GET "/hej/:id" [id] (hello2 id))
  (GET "/hej" [] "Hej!"))

(def app
  (-> handler (compojure.handler/site)))

(servlet/defservice app)
