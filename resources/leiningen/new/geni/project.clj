(defproject {{raw-name}} "0.0.1-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[leiningen "2.9.3"]
                 [org.clojure/clojure "1.10.1"]
                 [reply "0.4.4"]
                 [zero.one/geni "0.0.13"]
                 ;; Spark
                 [org.apache.spark/spark-core_2.12 "3.0.0"]
                 [org.apache.spark/spark-hive_2.12 "3.0.0"]
                 [org.apache.spark/spark-mllib_2.12 "3.0.0"]
                 [org.apache.spark/spark-sql_2.12 "3.0.0"]
                 [org.apache.spark/spark-streaming_2.12 "3.0.0"]{{#dataproc?}}
                 ;; Dataproc
                 [org.apache.hadoop/hadoop-client "3.2.1"]
                 [com.google.guava/guava "27.0-jre"]{{/dataproc?}}{{#xgboost?}}
                 ;; Optional: Spark XGBoost
                 [ml.dmlc/xgboost4j-spark_2.12 "1.0.0"]
                 [ml.dmlc/xgboost4j_2.12 "1.0.0"]{{/xgboost?}}{{#gsheets?}}
                 ;; Optional: Google Sheets Integration
                 [com.google.api-client/google-api-client "1.30.9"]
                 [com.google.apis/google-api-services-drive "v3-rev197-1.25.0"]
                 [com.google.apis/google-api-services-sheets "v4-rev612-1.25.0"]
                 [com.google.oauth-client/google-oauth-client-jetty "1.30.6"]
                 [org.apache.hadoop/hadoop-client "2.7.3"]{{/gsheets?}}]
  :profiles {:uberjar {:aot :all}}
  :main ^:skip-aot {{namespace}}.core
  :target-path "target/%s")
