(ns ca.landonjw.bootstrap
  (:require [clojure.java.io :as io]))

(defn start-nrepl-server []
  (let [port 7888]
    (println "Launching nREPL server on port " port)
    (@(requiring-resolve 'nrepl.server/start-server) :port port)))

(def fields-file (io/resource "mappings/fields.csv"))

(def methods-file (io/resource "mappings/methods.csv"))

(defn read-mappings!
  "Reads from a file and gets all mappings."
  [mappings-files]
  (let [contents (reduce #(str %1 (slurp %2)) "" mappings-files)]
    (->> contents
         (clojure.string/split-lines)
         (map #(clojure.string/split % #","))
         (reduce #(assoc %1 (nth %2 1) (nth %2 0)) {}))))

(def mappings (read-mappings! [fields-file methods-file]))

(defn get-searge-name
  [name]
  (->> name
       (str)
       (#(get mappings %1 %1))
       (symbol)))

(defn wrap-parens
  [input]
  (str "(" input ")"))

(defmacro searge
  [f]
  (-> f
      (str)
      (clojure.string/split #" |\(|\)")
      (->>
        (filter #(not (clojure.string/blank? %)))
        (map get-searge-name)
        (reduce #(str %1 %2 " ") "")
        (clojure.string/trim)
        (wrap-parens)
        (read-string))))