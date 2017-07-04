(ns kakeibo.service-test
  (:require [clojure.test :refer :all]
            [io.pedestal.http :as http]
            [kakeibo.service :as service]))

(deftest system
  (is (= (get-in service/system [:server :this ::http/join?]) true)))
