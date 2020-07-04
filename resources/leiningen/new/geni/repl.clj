(ns {{namespace}}.repl
  (:require
    [clojure.java.io :as io]
    [nrepl.server]
    [reply.main]))

(def default-opts {:color true :history-file ".nrepl-history"})

(defn client [opts]
  (let [port (or (:port opts) (try
                                (slurp ".nrepl-port")
                                (catch Throwable _)))
        host (or (:host opts) "127.0.0.1")
        opts (assoc (merge default-opts opts) :attach (str host ":" port))]
    (assert (and host port) "host and/or port not specified for REPL client")
    (reply.main/launch-nrepl opts)))

(defn launch-repl []
  (let [port   65204
        server (nrepl.server/start-server :port port)]
    (doto (io/file ".nrepl-port") .deleteOnExit (spit port))
    (println (str "nREPL server started on port " port))
    (client {:port port :custom-eval '(ns {{namespace}}.core)})
    (nrepl.server/stop-server server)))
