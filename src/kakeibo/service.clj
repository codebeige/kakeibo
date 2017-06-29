(ns kakeibo.service
  (:require [kakeibo.system.server :as server]
            [net.readmarks.compost :as compost])
  (:gen-class))

(def env
  {:server {:join? true}})

(defn system [{:keys [server]}]
  {:server (server/component server)})

(def merge-env (partial merge-with merge))

(defn start
  ([] (start {}))
  ([e]
   (-> env
       (merge-env e)
       system
       compost/start)))

(defn stop [s]
  (compost/stop s))

(defn -main []
  (compost/start (system env)))
