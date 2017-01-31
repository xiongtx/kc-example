(ns kc-example.fixture
  (:require [clojure.test :refer [join-fixtures]]
            kafka.admin.topic
            [kafka.test.fixtures :as fixtures]
            kafka.zk
            [kc-example.test-config :as test-config]))

(defn kafka-fixture
  "Kafka fixture."
  []
  (join-fixtures [(kafka.test.fixtures/zookeeper (test-config/test-zk))
                  (kafka.test.fixtures/broker (test-config/test-broker))]))

(defn topics-fixture
  "Returns a fixture that creates the topics needed for the test."
  [topic-metadata zk-config]
  (fn [t]
    (kafka.admin.topic/ensure-topics! (-> zk-config
                                          kafka.zk/client
                                          kafka.zk/utils)
                                      topic-metadata)
    (t)))
