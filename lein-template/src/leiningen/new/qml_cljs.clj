(ns leiningen.new.qml-cljs
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files
                                             sanitize-ns]]
            [leiningen.core.main :as main]))

(def render (renderer "qml-cljs"))

(defn qml-cljs
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' qml-cljs project.")
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["src-cljs/{{sanitized}}/repl.cljs" (render "repl.cljs" data)]
             ["src-cpp/main.cpp" (render "main.cpp" data)]
             ["resources/qml.qrc" (render "qml.qrc" data)]
             ["resources/main.qml" (render "main.qml" data)]
             ["{{sanitized}}.pro" (render "example.pro" data)]
             ["deployment.pri" (render "deployment.pri" data)]
             ["README.md" (render "README.md")])))
