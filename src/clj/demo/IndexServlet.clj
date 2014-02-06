(ns demo.IndexServlet
  (:gen-class :extends javax.servlet.http.HttpServlet)
  (:use [ring.util.servlet :only (defservice)]
        [demo.core :only (app)]))

(defservice app)
