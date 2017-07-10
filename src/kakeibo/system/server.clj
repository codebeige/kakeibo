(ns kakeibo.system.server
  (:require [io.pedestal.http :as http]
            [kakeibo.routes :as routes]))

(def csp-defaults
  {:default-src "'self'"
   :object-src  "'none'"
   :script-src  "'self'"})


(defn options [{:keys [join? port csp]}]
  #::http{:type   :jetty
          :port   port
          :join?  join?
          :resource-path "public"
          :routes routes/expanded
          :secure-headers {:content-security-policy-settings
                           (merge csp-defaults csp)}})

(defn component [env]
  {:this  (-> env options http/create-server)
   :start (fn [this _] (http/start this))
   :stop  http/stop})
