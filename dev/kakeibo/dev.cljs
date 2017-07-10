(ns kakeibo.dev)

(enable-console-print!)

(extend-type js/Symbol
  IPrintWithWriter
  (-pr-writer [obj writer _]
    (write-all writer (str "#object[" (.toString obj) "]"))))

(println "Development environment loaded.")
