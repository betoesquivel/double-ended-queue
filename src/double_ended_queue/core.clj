(ns double-ended-queue.core
  (:require [double-ended-queue.deq :as deq])
  (:gen-class))

(def mydeq '(1 2 3))

"Testing my deque"
mydeq
(push-left mydeq 0)
(push-right mydeq 4)
(:deq (pop-right (:deq (pop-left mydeq)))) ; pop 1 and pop 3 '(2)

(defn push-left
  "Inserts val at the front of received queue"
  [deq val]
  (cons val deq))

(defn push-right
  "Inserts val at the back of received queue"
  [deq val]
  (reverse (push-left (reverse deq) val))
  ;(list (conj (vec deq) val))
  )

(defn read-left
  [deq]
  (first deq))

(defn read-right
  [deq]
  (last deq))

(defn pop-left
  "Returns a map with popped element and a new deq"
  [deq]
  (when (not (empty? deq))
    {:val (read-left deq) :deq (rest deq)}))

(defn pop-right
  "Returns a map with popped element and a new deq"
  [deq]
  (when (not (empty? deq))
    {:val (read-right deq) :deq (reverse (rest (reverse deq)))}
    ))

(defn find-val
  "Assuming the tree is in the form (root (tree)(tree))"
  [tree val]
  (cond
    (= val (first tree)) (first tree)
    (not (list? (second tree))) nil
    (< val (first tree)) (find-val (second tree) val)
    (> val (first tree)) (find-val (nth tree 2) val)
    ))

(def mytree '(5 (3 (1 () ()) (4 () ())) (8 (6 () ()) (9 () ()))))
(def anothertree '(5 (3 (1) (4)) (8 (6) (9))))

"Testing my tree"
mytree
(find-val mytree 5)
(find-val mytree 3)
(find-val mytree 1)
(find-val mytree 4)
(find-val mytree 8)
(find-val mytree 6)
(find-val mytree 9)
(find-val mytree 0)

anothertree
(find-val anothertree 5)
(find-val anothertree 3)
(find-val anothertree 1)
(find-val anothertree 4)
(find-val anothertree 8)
(find-val anothertree 6)
(find-val anothertree 9)
(find-val anothertree 0)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

