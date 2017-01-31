(ns kc-example.test-config
  (:require [kc-example.config :as config :refer [config]]))

(defn embedded-kafka?
  "If true, use embedded Kafka fixtures from `kafka.test`."
  []
  (get-in config [:test :embedded-kafka?]))

(defn test-zk
  "Config for test ZooKeeper."
  []
  (get-in config [:test :zookeeper]))

(defn test-broker
  "Config for test Kafka."
  []
  (get-in config [:test :broker]))

(defn test-producer
  "Config for test producer."
  []
  (get-in config [:test :producer]))

(defn test-consumer
  "Config for test consumer."
  []
  (get-in config [:test :consumer]))

(defn fuse-timeout-ms
  "Timeout (ms) before throwing exception.

  Intended for use in `timed-log-messages`."
  []
  (get-in config [:test :fuse-timeout-ms]))
