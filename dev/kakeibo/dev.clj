(ns kakeibo.dev
  (:require [kakeibo.service :as service]
            [net.readmarks.compost :as compost]))

(defonce system nil)

(def env
  {:server {:port  3000
            :join? false}})

(defn start []
  (alter-var-root #'system (fn [_] (->> env
                                        (merge-with merge service/env)
                                        service/system
                                        compost/start))))

(defn stop []
  (alter-var-root #'system (fn [s] (some-> s compost/stop))))

(defn restart []
  (stop)
  (start))
