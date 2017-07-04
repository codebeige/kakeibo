(ns kakeibo.system.browser
  (:require [clj-webdriver.remote.server :as server]
            [clj-webdriver.taxi :as taxi]
            [net.readmarks.compost :as compost]))

(defn component [{:keys [host port browser]}]
  {:this  (server/new-remote-session {:host host
                                      :port port
                                      :existing true}
                                     {:browser browser})
   :get   second
   :start (fn [this _]
            (taxi/set-driver! (second this)))
   :stop  (fn [this]
            (taxi/set-driver! nil)
            this)})
