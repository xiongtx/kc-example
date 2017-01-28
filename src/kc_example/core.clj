(ns kc-example.core
  (:require [kafka
             [client :as kc]
             [serdes :as serdes]]
            [kafka.admin
             [topic :refer [ensure-topics!]]
             [zk :as zk]]
            [kc-example.config :as config]))

(def topic-metadata {:topic {:topic.metadata/partitions 1
                             :topic.metadata/replication-factor 1
                             :topic.metadata/name "kc-example-1"}})

(defn -main
  "Consume from `kc-example-1` topic and print results."
  [& args]
  (ensure-topics! (zk/zk-utils (get config/zookeeper "zookeeper.connect"))
                  topic-metadata)
  (let [consumer (-> (kc/consumer config/consumer
                                  {::serdes/key-serde (serdes/serde ::serdes/string)
                                   ::serdes/value-serde (serdes/serde ::serdes/string)})
                     (kc/subscribe (get topic-metadata :topic)))
        records (kc/log-messages consumer 1000)]
    (doseq [[k v] records]
      (println "Key:" k "Value:" v))))
