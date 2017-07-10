(ns kakeibo.e2e.look-at-current-budget-test
  (:require [clojure.test :refer :all]
            [kakeibo.test :refer [pending xis]]
            [clj-webdriver.taxi :as taxi]
            [kakeibo.fixtures :as fixtures]))

(use-fixtures :once
              (fixtures/with-log-level :error)
              (fixtures/with-selenium-log-level :off))

(use-fixtures :each fixtures/with-system)

(deftest look-at-current-budget
  (is (= "1.234,56 €" (taxi/text ".limit"))))
