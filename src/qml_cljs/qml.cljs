(ns qml-cljs.qml
  (:require-macros [qml-cljs.macros :refer [qml-import]]))

(defn- qt-type [x]
  (let [s (str x)
        i (.indexOf s "(")]
    (.substr s 0 i)))

(defn qtype [x]
  (when-not (nil? x)
    (if (.isQtObject js/Qt x)
      (qt-type x)
      (.-constructor x))))

(def ^:private id (atom 0))

(defn create-qml-component-fn [uri version comp-name]
  (let [txt (str "import " uri " " version "; " comp-name " { ")]
    (println txt)
    (with-meta
      (fn [parent & forms]
        (.createQmlObject
         js/Qt
         (str txt (apply str forms) "}")
         parent
         (str "qml_cljs.qml.auto" (swap! id inc))))
      {:uri uri
       :version version
       :component comp-name})))

(qml-import [QtQml "2.0" QtObject])


(defn test-import [uri version comp-name]
  ((create-qml-component-fn uri version comp-name) js/global))

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
