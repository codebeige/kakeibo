(ns kakeibo.dev
  (:require [kakeibo.system.server :as server]
            [net.readmarks.compost :as compost]))

(defonce current-system nil)

(def system
  {:server (server/component {:port 3000 :join? false})})


(defn start []
  (alter-var-root #'current-system (fn [_] (compost/start system))))

(defn stop []
  (alter-var-root #'current-system (fn [s] (some-> s compost/stop))))

(defn restart []
  (stop)
  (start))
