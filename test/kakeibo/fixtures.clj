(ns kakeibo.fixtures
  (:require [kakeibo.log :as log]
            [kakeibo.selenium-log :as selenium-log]
            [kakeibo.system.browser :as browser]
            [kakeibo.system.server :as server]
            [net.readmarks.compost :as compost]))

(def system
  {:server  (server/component  {:port 8080
                                :join? false})
   :browser (browser/component {:browser :firefox
                                :host "firefox"
                                :port 4444})})

(def ^:dynamic *system*)

(defn with-system [f]
  (binding [*system* (compost/start system)]
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

(defn with-selenium-log-level [level]
  (fn [f]
    (let [original-level (selenium-log/level)]
      (try
       (selenium-log/set-level level)
       (f)
       (finally
        (selenium-log/set-level original-level))))))
