(ns {{namespace}}.core
  (:require
    [{{namespace}}.repl]
    [zero-one.geni.core :as g]
    [zero-one.geni.ml :as ml])
  (:gen-class))

(defonce spark (delay (g/create-spark-session {})))

(def training-set
  (delay
    (g/table->dataset
      @spark
      [[0 "a b c d e spark"  1.0]
       [1 "b d"              0.0]
       [2 "spark f g h"      1.0]
       [3 "hadoop mapreduce" 0.0]]
      [:id :text :label])))

(def pipeline
  (ml/pipeline
    (ml/tokenizer {:input-col :text
                   :output-col :words})
    (ml/hashing-tf {:num-features 1000
                    :input-col :words
                    :output-col :features})
    (ml/logistic-regression {:max-iter 10
                             :reg-param 0.001})))

(def test-set
  (delay
    (g/table->dataset
      @spark
      [[4 "spark i j k"]
       [5 "l m n"]
       [6 "spark hadoop spark"]
       [7 "apache hadoop"]]
      [:id :text])))

(defn -main [& _]
  (-> @spark .sparkContext .getConf .toDebugString println)
  (let [model (ml/fit @training-set pipeline)]
    (-> @test-set
        (ml/transform model)
        (g/select :id :text :probability :prediction)
        g/show))
  ({{namespace}}.repl/launch-repl)
  (System/exit 0))
