(ns kakeibo.system.browser
  (:require [clj-webdriver.remote.server :as server]
            [clj-webdriver.taxi :as taxi]
            [net.readmarks.compost :as compost]))

(defn localhost []
  (.getHostAddress (java.net.InetAddress/getLocalHost)))

(defn component [{:keys [host port browser]}]
  {:requires #{:server}
   :this     {:driver nil
              :server (server/init-remote-server {:host host
                                                  :port port
                                                  :existing true})}
   :get      :driver
   :start    (fn [this {:keys [server]}]
               (let [driver (server/new-remote-driver (:server this)
                                                      {:browser browser})]
                 (taxi/set-driver! driver)
                 (taxi/to (str "http://"
                               (localhost)
                               ":"
                               (:io.pedestal.http/port server)))
                 (assoc this :driver driver)))
   :stop     (fn [this]
               (taxi/quit)
               (assoc this :driver nil))})
