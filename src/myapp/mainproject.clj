(ns myapp.mainproject)

(def c)

(defn diff
  [x c]
  (cond

    (number? c) 0
    (symbol? c)
      (if (= x c)
        1
        0)
    (list? c) (let [operator (first c)
                    left-operand(second c)
                    right-operand(nth c 3)])

    
    :else 0))