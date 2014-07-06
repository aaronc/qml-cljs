(ns qml-cljs.qml
  (:require-macros [qml-cljs.macros :refer [qml-import]]))

(def ^:private id (atom 0))

(qml-import [QtQml "2.0" QtObject])

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

(defn test-import [uri version comp-name]
  (create-qml-component-fn uri version comp-name))

(defn qml-component [form])

(defrecord QmlSingletonRef [uri version singleton-name]
  ILookup
  (-lookup [o k not-found]
    (println o k)
    ))

(defn import-singleton [uri version singleton-name]
  (fn [property]
    (let [obj
          (.createQmlObject
           js/Qt
           (str "import QtQml 2.0\r\n"
                "import " uri " " version "\r\n"
                "QtObject { property variant value; value: " singleton-name "." (name property) " }")
           js/global
           (str "qml_cljs.qml.auto" (swap! id inc)))
          value (.-value obj)]
      (.destroy obj)
      value)))
