(ns qml-cljs.macros)

(defmacro qml-import [& forms]
  (doseq [[uri version & cmps] forms]
    (doseq [cmp cmps]
      (println uri version cmp)
      `(def ~cmp (qml-cljs.qml/create-qml-component-fn ~(str uri) ~(str version) ~(str cmp))))))

