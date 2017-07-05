(ns kakeibo.selenium-log
  (:import [java.util.logging Level Logger]))

(def levels
  {:all     Level/ALL
   :finest  Level/FINEST
   :finer   Level/FINER
   :fine    Level/FINE
   :config  Level/CONFIG
   :info    Level/INFO
   :warning Level/WARNING
   :severe  Level/SEVERE
   :off     Level/OFF})

(defn logger []
  (Logger/getLogger "org.openqa.selenium.remote"))

(defn level []
  (let [l (loop [p (logger)]
            (or (.getLevel p)
                (recur (.getParent p))))]
    (some (fn [[k v]] (when (= v l) k)) levels)))

(defn set-level [l]
  (.setLevel (logger) (l levels)))
