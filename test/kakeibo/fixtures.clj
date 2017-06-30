(ns kakeibo.fixtures
  (:require [kakeibo.service :as service]
            [kakeibo.log :as log]
            [net.readmarks.compost :as compost])
  )

(def env
  {:server {:port  8080
            :join? false}})

(defn system [e]
  (->> e (merge-with merge service/env) service/system))

(def ^:dynamic *system*)

(defn with-system [f]
  (binding [*system* (-> env system compost/start)]
    (try
     (f)
     (finally
      (compost/stop *system*)))))

(defn with-log-level [level]
  (fn [f]
    (let [original-level (log/level)]
      (try
       (log/set-level level)
       (f)
       (finally
        (log/set-level original-level))))))
