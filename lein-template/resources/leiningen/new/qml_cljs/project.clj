(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "FIXME: choose"
            :url "http://example.com/FIXME"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2268"]
                 [org.clojars.aaronc/google-closure-library "0.0-20140704-67148ad7"]
                 [qml-cljs "0.0.0-SNAPSHOT"]]
  :plugins [[lein-cljsbuild "1.0.3"]]
  :source-paths ["src-cljs"]
  :cljsbuild
  {:builds [{:id "dev"
             :source-paths ["src-cljs"]
             :compiler
             {:output-to "resources/qml_cljs.js"
              :output-dir "out"
              :optimizations :whitespace
              :pretty-print true
              :preamble ["qml_cljs/qml_preamble.js"]
              :target :qml}}]}
  :profiles
  {:dev
   {:dependencies
    [[org.clojars.aaronc/weasel "0.3.0-SNAPSHOT"]
     [com.cemerick/piggieback "0.1.3"]]
    :repl-options
    {:init (do (require 'weasel.repl.websocket)
               (defn qml-cljs-repl [& {:keys [port] :or {port 9001}}]
                 (cemerick.piggieback/cljs-repl
                  :repl-env (weasel.repl.websocket/repl-env
                             :port port))))}}}) 
