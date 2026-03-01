(ns myapp.mainproject)



(defn diff
  [x c]
  (cond

    (number? c) 0
    (symbol? c)
      (if (= x c)
        1
        0)
    (list? c) (let [operator (first c)
                    left-operand (second c)
                    right-operand (nth c 2)]
                (cond 
                  (= operator '+)
                    (list '+ (diff x left-operand ) (diff x right-operand ))
                  (= operator '-)
                    (list '- (diff x left-operand) (diff x right-operand))
                  (= operator '*)
                    (list '+ (list '* left-operand (diff x right-operand))(list '* right-operand (diff x left-operand)))
                  
                    :else 0))
                
    :else 0))

(defn simplify
  [exp]
  (cond
    (number? exp) exp
    (symbol? exp) exp

    (list? exp)
    (let [operator (first exp)
          left-operand (second exp)
          right-operand (nth exp 2)
          simplified-left (simplify left-operand)
          simplified-right (simplify right-operand)]

      (cond
        
        (= operator '+)
        (cond
          (and (number? simplified-left) (number? simplified-right))
          (+ simplified-left simplified-right)

          (= simplified-left 0)
          simplified-right

          (= simplified-right 0)
          simplified-left

          :else
          (list '+ simplified-left simplified-right))

        (= operator '*)
        (cond
          (and (number? simplified-left) (number? simplified-right))
          (* simplified-left simplified-right)

          (or (= simplified-left 0) (= simplified-right 0))
          0

          (= simplified-left 1)
          simplified-right

          (= simplified-right 1)
          simplified-left

          :else
          (list '* simplified-left simplified-right))

        
        (= operator '-)
        (cond
          (and (number? simplified-left) (number? simplified-right))
          (- simplified-left simplified-right)

          (= simplified-right 0)
          simplified-left
 
          :else
          (list '- simplified-left simplified-right))
        
        :else
        (list operator simplified-left simplified-right)))

    :else exp))

(defn testing 
  [y h]
  (println "diff-----> " (diff ))
  (simplify(diff y h)))