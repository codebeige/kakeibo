(ns kakeibo.dev
  (:require [kakeibo.service :as service]))

(defonce system nil)

(def env
  {:server {:port  3000
            :join? false}})

(defn start []
  (alter-var-root #'system (fn [_] (->> env
                                        (merge-with merge service/env)
                                        service/start))))

(defn stop []
  (alter-var-root #'system (fn [s] (some-> s service/stop))))

(defn restart []
  (stop)
  (start))
