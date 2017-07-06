(ns kakeibo.test
  (:require [clojure.test :as test]
            [eftest.report.pretty :as pretty]
            [io.aviso.ansi :as ansi]
            [puget.printer :as puget]))

(defmacro pending
  ([form] `(pending ~form nil))
  ([form message]
   `(test/do-report {:type :pending, :message ~message, :expected '~form})))

(defmacro xis
  ([form] `(pending ~form nil))
  ([form message] `(pending ~form ~message)))

(defn- test-var-str []
  (let [test-var (first test/*testing-vars*)]
    (println (str ansi/yellow-font
                  "PENDING"
                  (:reset pretty/*fonts*)
                  " in"
                  (-> test-var meta :ns ns-name)
                  "/"
                  (-> test-var meta :name)))))

(defmethod pretty/report :pending [{:keys [message expected]}]
  (test/inc-report-counter :pending)
  (test/with-test-out
    (print pretty/*divider*)
    (test-var-str)
    (when (seq test/*testing-contexts*) (println (test/testing-contexts-str)))
    (when message (println message))
    (println "expected:" (pr-str expected))))

(defn- pluralize [word count]
  (if (= count 1) word (str word "s")))

(defn- format-interval [duration]
  (format "%.3f seconds" (double (/ duration 1e3))))

(defmethod pretty/report :summary [{:keys [test pass fail error pending duration]
                                    :or {pending 0}}]
  (let [total (+ pass fail error)
        color (if (> (+ error fail) 0)
                (:error pretty/*fonts*)
                (if (> pending 0) ansi/yellow-font (:pass pretty/*fonts*)))]
    (test/with-test-out
      (print pretty/*divider*)
      (println "Ran" test "tests in" (format-interval duration))
      (println (str color
                    total " " (pluralize "assertion" total) ", "
                    fail  " " (pluralize "failure" fail) ", "
                    error " " (pluralize "error" error) ", "
                    pending " pending.")
                    (:reset pretty/*fonts*)))))
