(ns kakeibo.e2e.look-at-current-budget-test
  (:require [clojure.test :refer :all]
            [kakeibo.test :refer :all]
            [clj-webdriver.taxi :as taxi]
            [kakeibo.fixtures :as fixtures]))

(use-fixtures :once
              (fixtures/with-log-level :error)
              (fixtures/with-selenium-log-level :off))

(use-fixtures :each fixtures/with-system)

(deftest look-at-current-budget
  (pending (taxi/exists? ".limit")))
