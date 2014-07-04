(ns qml-cljs.qml
  (:require-macros [qml-cljs.macros :refer [qml-import]])
  (:require
   [qml-cljs.core :refer [parent]]))

(def ^:private id (atom 0))

(qml-import [QtQuick 2.3 QtObject])

(defn create-qml-component-fn [uri version comp-name]
  (let [txt (str "import " uri " " version "; " comp-name " { ")]
    (println txt)
    (with-meta
      (fn [parent & forms]
        (.createQmlObject
         js/Qt
         (str txt "}")
         parent
         (str "qml_cljs.qml.auto" (swap! id inc))))
      {:uri uri
       :version version
       :component comp-name})))

(defn qml-component [form])
