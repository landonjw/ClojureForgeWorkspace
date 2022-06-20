(ns ca.landonjw.eval-command)

(defn execute [expr]
  (println (eval (read-string expr))))