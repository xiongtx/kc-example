(ns kc-example.aero
  "Aero methods to work with environ, etc."
  (:require [aero.core :as aero]
            [clojure.string :as str]
            [environ.core :refer [env]]))

(defn keywordize [s]
  (-> (str/lower-case s)
      (str/replace "_" "-")
      (str/replace "." "-")
      (keyword)))

(defmethod aero/reader 'env
  [opts tag value]
  (get env (keywordize (str value))))
