(ns {{namespace}}.core
  (:require
    [zero-one.geni.core :as g])
  (:gen-class))

(defonce spark (g/create-spark-session {}))

(defn -main [& _]
  (let [dataframe (g/read-csv! spark "resources/dummy.csv")]
    (g/show dataframe)))
