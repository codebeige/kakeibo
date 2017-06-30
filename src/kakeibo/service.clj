(ns kakeibo.service
  (:require [kakeibo.system.server :as server]
            [net.readmarks.compost :as compost])
  (:gen-class))

(def env
  {:server {:port  80
            :join? true}})

(defn system [{:keys [server]}]
  {:server (server/component server)})

(defn -main []
  (-> env system compost/start))
