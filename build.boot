(def project 'kakeibo)
(def version "0.1.0-SNAPSHOT")

(set-env! :resource-paths #{"src" "resources"}
          :source-paths   #{"test" "dev"}
          :dependencies   '[[org.clojure/clojure "1.9.0-alpha17"]
                            [org.clojure/core.async "0.3.443"]

                            [io.pedestal/pedestal.jetty   "0.5.2"]
                            [io.pedestal/pedestal.service "0.5.2"]
                            [net.readmarks/compost        "0.2.0"]
                            [org.slf4j/slf4j-simple       "1.7.25"]

                            [adzerk/boot-test      "1.2.0" :scope "test"]
                            [codebeige/boot-reset  "0.1.3" :scope "test"]
                            [samestep/boot-refresh "0.1.0" :scope "test"]]
          :exclusions     '[org.clojure/clojure
                            org.clojure/core.async])

(require '[adzerk.boot-test :refer [test]]
         '[codebeige.boot-reset :refer [reset]]
         '[samestep.boot-refresh :refer [refresh]])

(task-options!
 aot    {:namespace   #{'kakeibo.service}}
 jar    {:main        'kakeibo.service
         :file        "kakeibo-service.jar"}
 pom    {:project     project
         :version     version
         :description "Manage shared budgets online."
         :url         "https://github.com/codebeige/kakeibo"
         :scm         {:url "https://github.com/codebeige/kakeibo"}
         :license     {"MIT" "https://opensource.org/licenses/MIT"}}
 repl   {:bind "0.0.0.0"}
 reset  {:start 'kakeibo.dev/start
         :stop  'kakeibo.dev/stop}
 target {:dir #{"target"}})

(deftask run
  "Run the project."
  [a args ARG [str] "the arguments for the application."]
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
   (repl :server true :port 7888)
   (watch)
   (reset)
   (refresh)))
