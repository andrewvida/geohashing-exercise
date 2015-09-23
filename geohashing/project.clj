(defproject geohashing "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [ring/ring-defaults "0.1.2"]
                 [clj-time "0.11.0"]
                 [clj-http "2.0.0"]
                 [org.clojure/data.json "0.2.6"]
                 [digest "1.4.4"]]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler geohashing.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
