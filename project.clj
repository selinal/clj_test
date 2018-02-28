(defproject clj_test "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :resource-paths ["resources"]
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.apache.kafka/kafka-clients "1.0.0"]
                 [org.clojure/core.async "0.4.474"]
                 [spootnik/kinsky "0.1.21"]
                 ]
  :main clj-test.core
  )
