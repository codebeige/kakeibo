(ns kakeibo.system.server
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]))

(def routes
  #{["/" :get (fn [_] {:status 200, :body "Hello, Kakeibo!"}) :route-name :hello]})

(defn options [{:keys [join? port]}]
  #::http{:type   :jetty
          :port   port
          :join?  join?
          :routes (route/expand-routes routes)})

(defn component [env]
  {:this  (-> env options http/create-server)
   :start (fn [this _] (http/start this))
   :stop  http/stop})
