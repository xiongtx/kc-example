(ns kc-example.core-test
  (:require [clojure.test :refer :all]
            [kafka
             [client :as kc]
             [serdes :as serdes :refer [serde]]]
            [kc-example
             [config :as config]
             [fixture :as fix]
             [test-config :as test-config]]))

(use-fixtures :each (join-fixtures
                     (when (test-config/embedded-kafka?)
                       [(fix/kafka-fixture)
                        (fix/topics-fixture {:kc-example-1 config/topic-metadata} (test-config/test-zk))])))

(deftest producer-consumer-test
  (with-open
    [producer (kc/producer (test-config/test-producer)
                           {:kafka.serdes/key-serde (serde :kafka.serdes/string)
                            :kafka.serdes/value-serde (serde :kafka.serdes/string)})
     consumer (-> (kc/consumer (test-config/test-consumer)
                               {:kafka.serdes/key-serde (serde :kafka.serdes/string)
                                :kafka.serdes/value-serde (serde :kafka.serdes/string)})
                  (kc/subscribe config/topic-metadata))]

    (let [records (kc/timed-log-messages consumer
                                         (test-config/fuse-timeout-ms))]

      @(kc/send! producer (kc/producer-record
                           config/topic-metadata
                           "Example key"
                           "Example value"))

      (doseq [[k v] (take 1 records)]
        (testing "Message is sent and consumed"
        (is (= k "Example key"))
        (is (= v "Example value")))))))
