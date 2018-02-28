(ns clj-test.core
  (:gen-class)
  (:import (java.util Properties)))
(require '[clojure.java.io :as io])

(def prop (new Properties))
(.put prop "bootstrap.servers", "localhost:9092")
(.put prop "acks", "1")
(.put prop "retries", (int 0))
(.put prop "batch.size", (int 16384))
(.put prop "linger.ms", (int 1))
(.put prop "buffer.memory", (int 33554432))
(.put prop "key.serializer", "org.apache.kafka.common.serialization.StringSerializer") ;
(.put prop "value.serializer", "org.apache.kafka.common.serialization.StringSerializer") ;


