(ns kakeibo.e2e-test
  (:require [clojure.test :refer :all]
            [kakeibo.fixtures :as fixtures]))

(use-fixtures :once (fixtures/with-log-level :error) fixtures/with-system)

(deftest test-system
  (is (= (get-in fixtures/*system* [:server :status]) :started)))
