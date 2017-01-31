(ns kc-example.config
  (:require [clojure.java.io :as io]
            [kc-example.aero :as aero]))

(def config (aero/read-config (io/resource "config.edn")))

(def producer (get-in config [:kafka :producer]))

(def consumer (get-in config [:kafka :consumer]))

(def zookeeper (get-in config [:kafka :zookeeper]))

(def topic-metadata {:topic.metadata/partitions 1
                     :topic.metadata/replication-factor 1
                     :topic.metadata/name "kc-example-1"})
