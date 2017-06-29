(ns kakeibo.test
  (:require [kakeibo.service :as service]))

(def env
  {:server {:port  8080
            :join? false}})

(def ^:dynamic *system*)

(defn start []
  (->> env (merge-with merge service/env) service/start))

(defn stop [s]
  (some-> s service/stop))

(defn with-system [f]
  (binding [*system* (start)]
    (f)
    (stop *system*)))
