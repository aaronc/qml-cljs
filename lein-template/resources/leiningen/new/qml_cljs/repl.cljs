(ns {{name}}.repl
    (:require [weasel.repl.qml :as repl]))

(defn ^:export init-repl
  [qml-parent port]
  (repl/connect qml-parent (str "ws://localhost:" port) :verbose true))
