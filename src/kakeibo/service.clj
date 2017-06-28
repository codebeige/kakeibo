(ns kakeibo.service
  (:require [kakeibo.system.server :as server]
            [net.readmarks.compost :as compost])
  (:gen-class))

(def system
  {:server server/component})

(defn -main []
  (compost/start system))
