(ns {{namespace}}.core
  (:require
    [zero-one.geni.core :as g])
  (:gen-class))

(defonce spark (delay (g/create-spark-session {})))

(defn -main [& _]
  (println "Spark master: " (-> @spark .sparkContext .master))
  (let [dataframe (g/read-csv! @spark "resources/dummy.csv")]
    (g/show dataframe)))
