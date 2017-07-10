(ns kakeibo.routes
  (:require [io.pedestal.http.route :as route]
            [ring.util.response :as response]))

(defn app-response [_]
  (-> (response/resource-response "app.html")
      (response/content-type "text/html; charset=utf-8")))

(def routes
  #{["/" :get app-response :route-name :app]})

(def expanded
  (route/expand-routes routes))
