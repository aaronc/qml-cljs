(ns qml-cljs.macros
  (:require [cljs.compiler :as comp]
            [cljs.analyzer :as ana]))

(defn- eval-form [form]
  (comp/emit-str (ana/analyze {:context :expr :locals {}}
                              form)))

(defmacro qreify [& specs])

(defmacro qml-import [& forms]
  `(do
     ~@(for [[uri version & cmps] forms]
         `(do
            ~@(for [cmp cmps]
                (do
                  (println uri version cmp)
                  `(def ~cmp (qml-cljs.qml/create-qml-component-fn ~(str uri) ~(str version) ~(str cmp)))))))))
