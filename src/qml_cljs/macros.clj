(ns qml-cljs.macros)

(defmacro qml-import [& forms]
  (doseq [[uri version & cmps] forms]
    (doseq [cmp cmps]
      `(def ~(symbol cmp) (qml.cljs/create-qml-component-fn ~(str uri) ~(str version) ~(str cmp))))))

