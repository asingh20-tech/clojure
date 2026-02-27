(ns myapp.core)

(defn invert-addition [data]
  (list '- (second data) (list '- 0 (nth data 2))))

(defn testing 
  [x]
  (str
   x  "'S INVERSE -->" (invert-addition x) "\n"
   (eval x ) " == " (eval (invert-addition x)))
  )