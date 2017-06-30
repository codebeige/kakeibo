(def project 'kakeibo)
(def version "0.1.0-SNAPSHOT")

(set-env! :resource-paths #{"src" "resources" "config"}
          :source-paths   #{"dev" "test"}
          :dependencies   '[[org.clojure/clojure "1.9.0-alpha17"]
                            [org.clojure/core.async "0.3.443"]

                            [ch.qos.logback/logback-classic "1.2.3"]
                            [io.pedestal/pedestal.jetty     "0.5.2"]
                            [io.pedestal/pedestal.service   "0.5.2"]
                            [net.readmarks/compost          "0.2.0"]

                            [adzerk/boot-test      "1.2.0"           :scope "test"]
                            [cider/cider-nrepl     "0.15.0-SNAPSHOT" :scope "test"]
                            [codebeige/boot-reset  "0.1.3"           :scope "test"]
                            [metosin/boot-alt-test "0.3.2"           :scope "test"]
                            [samestep/boot-refresh "0.1.0"           :scope "test"]]
          :exclusions     '[org.clojure/clojure
                            org.clojure/core.async])

(require '[adzerk.boot-test :refer [test] :rename {test boot-test}]
         '[cider.nrepl :refer [cider-middleware]]
         '[cider.tasks :refer [add-middleware]]
         '[codebeige.boot-reset :refer [reset]]
         '[metosin.boot-alt-test :refer [alt-test]]
         '[samestep.boot-refresh :refer [refresh]])

(task-options!
 alt-test {:report 'eftest.report.pretty/report}
 aot      {:namespace #{'kakeibo.service}}
 jar      {:main 'kakeibo.service
           :file "kakeibo-service.jar"}
 pom      {:project     project
           :version     version
           :description "Manage shared budgets online."
           :url         "https://github.com/codebeige/kakeibo"
           :scm         {:url "https://github.com/codebeige/kakeibo"}
           :license     {"MIT" "https://opensource.org/licenses/MIT"}}
 repl     {:bind "0.0.0.0"}
 reset    {:start 'kakeibo.dev/start
           :stop  'kakeibo.dev/stop}
 target   {:dir #{"builds"}})

(deftask run
  "Run the project."
  [a args ARG [str] "Arguments passed to application."]
  (require '[kakeibo.service :as service])
  (with-pass-thru _
    (apply (resolve 'service/-main) args)))

(deftask build
  "Build the project locally as a JAR."
  []
  (comp
   (aot)
   (pom)
   (uber)
   (jar)
   (sift :include #{#"\.jar$"})
   (target)))

(deftask dev
  "Start up interactive development environment."
  []
  (comp
   (add-middleware :middleware cider-middleware)
   (repl :server true :port 7888)
   (watch)
   (reset)
   (refresh)))

(deftask test
  "Run all rests."
  []
  (comp
   (boot-test)))

(deftask autotest
  "Automatically run related tests on file changes."
  []
  (comp
   (watch)
   (alt-test)))
