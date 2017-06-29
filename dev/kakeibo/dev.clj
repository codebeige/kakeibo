(ns kakeibo.dev
  (:require [kakeibo.service :as service]))

(defonce current-system nil)

(def env
  {:server {:join? false}})

(defn start []
  (alter-var-root #'current-system (fn [_] (->> env
                                                (merge-with merge service/env)
                                                service/start))))

(defn stop []
  (alter-var-root #'current-system (fn [s] (some-> s service/stop))))

(defn restart []
  (stop)
  (start))
