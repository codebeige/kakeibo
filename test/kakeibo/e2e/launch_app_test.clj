(ns kakeibo.e2e.launch-app-test
  (:require [clojure.test :refer :all]
            [clj-webdriver.taxi :as taxi]
            [kakeibo.fixtures :as fixtures]))

(use-fixtures :once
              (fixtures/with-log-level :error)
              (fixtures/with-selenium-log-level :off))

(use-fixtures :each fixtures/with-system)

(deftest launch-app
  (is (= (taxi/text "body") "Hello, Kakeibo!")))
