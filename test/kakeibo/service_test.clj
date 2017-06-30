(ns kakeibo.service-test
  (:require [clojure.test :refer :all]
            [kakeibo.service :as service]))

(deftest env
  (is (true? (get-in service/env [:server :join?]))))
