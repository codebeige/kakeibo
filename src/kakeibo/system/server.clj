(ns kakeibo.system.server
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]))

(def routes
  #{["/" :get (fn [_] {:status 200, :body "Hello, Kakeibo!"}) :route-name :hello]})

(def options
  #::http{:type   :jetty
          :port   80
          :join?  true
          :routes (route/expand-routes routes)})

(def component
  {:this  (http/create-server options)
   :start (fn [this _] (http/start this))
   :stop  http/stop})
