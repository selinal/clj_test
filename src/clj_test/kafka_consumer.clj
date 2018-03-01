(ns clj-test.kafka-consumer
  (:require [clojure.string :as str])
  (:import (java.util Properties UUID)
           (org.apache.kafka.clients.producer KafkaProducer ProducerRecord)))
(require '[clojure.core.async :as a
           :refer [>! <! >!! <!! go go-loop chan buffer close! thread alts! alts!! timeout]]
         '[clj-test.core :refer :all])

(def topic "TEST.TOPIC")

(defn ^KafkaProducer create-producer [prop]
  (KafkaProducer. prop))

(defn- get-properties []
  prop)

(defn- ^String message-key []
  (str/replace (UUID/randomUUID) "-" ""))

(def producer (atom (create-producer (get-properties))))

(defn close-producer! []
  (.close @producer))

(defn- ^ProducerRecord create-record [topic message]
  (ProducerRecord. topic, (message-key), message))

(defn send-message [message topic]
  (let [r (create-record topic message)]
    (try
      (.send @producer r)
      (catch Exception e
        (println e))
      (finally (println "fin")))))

(def echo-chan (chan))

;(let [ch value]
;
;  (go-loop
;    ((when-let [record (<! ch)]
;       (println (pr-str record))
;       (recur)))))
;
;(defn loop-chan [x]
;  ;(println (str "Hellow: " x))
;  (throw (Exception. (str "my exception: " x)))
;  )
;
;(go-loop []
;  (let [x (<! echo-chan)
;        rez (try
;              (loop-chan x)
;              :ok
;              (catch Exception e
;                (println (str ">>>>" (.getMessage e)))
;                :error
;                ))]
;    ;(when (= rez :ok)
;    (recur)                                                 ;)
;    ))
;
;;(>!! echo-chan "tra ta ta")
;
;(let [c1 (go (<! (timeout (rand-int 1000))) 5)
;      c2 (go (<! (timeout (rand-int 1000))) 7)]
;  (go (let [v1 (<! c1)
;            v2 (<! c2)]
;        (println {:v1   v1
;                  :v2   v2
;                  :summ (+ v1 v2)}))))
;
;
;
;(let [ch (ka/consumer {:bootstrap.servers "localhost:9092"
;                       :group.id          (str (UUID/randomUUID))}
;                      (client/string-deserializer)
;                      (client/string-deserializer))
;      topic "tests"]
;
;  (a/go-loop []
;    (when-let [record (a/<! ch)]
;      (println (pr-str record))
;      (recur)))
;  (a/put! ch {:op :partitions-for :topic topic})
;  (a/put! ch {:op :subscribe :topic topic})
;  (a/put! ch {:op :commit})
;  (a/put! ch {:op :pause :topic-partitions [{:topic topic :partition 0}
;                                            {:topic topic :partition 1}
;                                            {:topic topic :partition 2}
;                                            {:topic topic :partition 3}]})
;  (a/put! ch {:op :resume :topic-partitions [{:topic topic :partition 0}
;                                             {:topic topic :partition 1}
;                                             {:topic topic :partition 2}
;                                             {:topic topic :partition 3}]})
;  (a/put! ch {:op :stop}))