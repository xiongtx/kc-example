(ns kc-example.core
  (:require [kafka
             [client :as kc]
             [serdes :as serdes]]
            [kafka.admin
             [topic :refer [ensure-topics!]]
             [zk :as zk]]
            [kc-example.config :as config]))

(defn -main
  "Consume from `kc-example-1` topic and print results."
  [& args]
  (ensure-topics! (zk/zk-utils (get config/zookeeper "zookeeper.connect"))
                  {:kc-example-1 config/topic-metadata})
  (let [consumer (-> (kc/consumer config/consumer
                                  {::serdes/key-serde (serdes/serde ::serdes/string)
                                   ::serdes/value-serde (serdes/serde ::serdes/string)})
                     (kc/subscribe config/topic-metadata))
        records (kc/log-messages consumer 1000)]
    (doseq [[k v] records]
      (println "Key:" k "Value:" v))))
