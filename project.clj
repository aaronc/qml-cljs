(defproject qml-cljs "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :exclusions
  [;;[org.clojure/clojurescript]
   [org.clojure/google-closure-library]]
  :profiles
  {:dev
   {:dependencies [[org.clojure/clojure "1.6.0"]
                   [org.clojars.aaronc/google-closure-library "0.0-20140704-67148ad7"]
 ;;                  [org.clojars.aaronc/clojurescript-with-qml-support "0.0-SNAPSHOT"]
                   [org.clojure/clojurescript "0.0-2261"]
                   [weasel "0.2.1"]
                   [com.cemerick/piggieback "0.1.3"]]

    :plugins [[lein-cljsbuild "1.0.3"
               :exclusions
               [;;[org.clojure/clojurescript]
                [org.clojure/google-closure-library]]]]

    :source-paths ["src"]

    :repl-options
    {:init (do (require 'weasel.repl.websocket)
               (defn init-repl [& {:keys [port] :or {port 9005}}]
                 (cemerick.piggieback/cljs-repl
                  :repl-env (weasel.repl.websocket/repl-env
                             :port port))))}

    :cljsbuild { 
                :builds [{:id "qml-cljs"
                          :source-paths ["src"]
                          :compiler {
                                     :output-to "qml_cljs.js"
                                     :output-dir "out"
                                     :optimizations :whitespace
                                     :pretty-print true
;;                                   :preamble ["qml_preamble.js"]
                                     :target :qml}}]}
    }})
