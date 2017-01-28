(ns kc-example.config
  (:require [aero.core :as aero]
            [clojure.java.io :as io]))

(def config (aero/read-config (io/resource "config.edn")))

(def producer (get-in config [:kafka :producer]))

(def consumer (get-in config [:kafka :consumer]))

(def zookeeper (get-in config [:kafka :zookeeper]))
