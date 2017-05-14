(def project 'kakeibo)
(def version "0.1.0-SNAPSHOT")

(set-env! :resource-paths #{"resources" "src"}
          :source-paths   #{"test"}
          :dependencies   '[[org.clojure/clojure "1.9.0-alpha16"]

                            [adzerk/boot-test      "1.2.0" :scope "test"]
                            [samestep/boot-refresh "0.1.0" :scope "test"]]
          :exclusions     '[org.clojure/clojure])

(task-options!
 repl   {:bind "0.0.0.0"}
 aot    {:namespace   #{'kakeibo.web}}
 pom    {:project     project
         :version     version
         :description "Manage shared budgets online."
         :url         "https://github.com/codebeige/kakeibo"
         :scm         {:url "https://github.com/codebeige/kakeibo"}
         :license     {"MIT" "https://opensource.org/licenses/MIT"}}
 jar    {:main        'kakeibo.web
         :file        (str "kakeibo-" version "-standalone.jar")}
 target {:dir #{"target"}})

(require '[adzerk.boot-test :refer [test]]
         '[samestep.boot-refresh :refer [refresh]])

(deftask run
  "Run the project."
  [a args ARG [str] "the arguments for the application."]
  (require '[kakeibo.web :as web])
  (with-pass-thru _
    (apply (resolve 'web/-main) args)))

(deftask build
  "Build the project locally as a JAR."
  []
  (comp
   (aot)
   (pom)
   (uber)
   (jar)
   (target)))

(deftask dev
  "Start up interactive development environment."
  []
  (comp
   (repl :server true :port 7888)
   (watch)
   (refresh)))
