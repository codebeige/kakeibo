(ns kakeibo.log
  (:import [org.slf4j LoggerFactory]
           [ch.qos.logback.classic Logger Level]))

(def levels
  {:all   Level/ALL
   :trace Level/TRACE
   :debug Level/DEBUG
   :info  Level/INFO
   :warn  Level/WARN
   :error Level/ERROR
   :off   Level/OFF})

(defn root-logger []
  (LoggerFactory/getLogger Logger/ROOT_LOGGER_NAME))

(defn level []
  (let [l (.getLevel (root-logger))]
    (some (fn [[k v]] (when (= v l) k)) levels)))

(defn set-level [l]
  (.setLevel (root-logger) (l levels)))
