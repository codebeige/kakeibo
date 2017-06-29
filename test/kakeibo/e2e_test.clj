(ns kakeibo.e2e-test
  (:require [clojure.test :refer :all]
            [kakeibo.test :as test]))

(use-fixtures :once test/with-system)

(deftest test-system
  (is (= (get-in test/*system* [:server :status]) :started)))
