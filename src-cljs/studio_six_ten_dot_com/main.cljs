(ns studio_six_ten_dot_com.mainjs
  (:use [jayq.core :only [$ css html]]
        [jayq.util :only [wait]]))

(def $document ($ js/document))

(def $scene ($ :#splashScene))

(def banner-width 80)

(def banner-height 15)

(def banner-text "#### ====================================================================== ####                                                                                       \"8P\"\"\"\"\"\"8                            e\"\"\"\"e    e88   e8\"\"\"\"e e\"\"8               \"8e      eeeee e   e eeeee e  eeeee  8    8      8   8    d8  ee8                 \"8e      8   8   8 8   8 8  8  88  8      88   8   8   8 8    88                  \"8e    8e  8e  8 8e  8 8e 8   8  8eeeee      8   8  8  8 eee88                 e8\"     88  88  8 88  8 88 8   8  8    8 88   8   8 8   8                     e8\"       88  88ee8 88ee8 88 8eee8  8    8    e8888 8P    8                   d8beeeeee8                            8eeee8    88888 8eeeee8                                                                                                     STUDIO SIX-TEN INTERACTIVE DEVELOPMENT INC --- TORONTO, CANADA                                                                                        \n#### ====================================================================== ####                                                                               \n#### ==== INTERACTIVE DEVELOPMENT IN COMMON LISP, CLOJURE, and PYTHON. ==== ####\n                                                                                \n")

(def keep-alive? true)

(defn draw-matrix []
  (let [cols (quot (.width $document) 12)
        rows (quot (.height $document) 21)
        offset-x (- (quot cols 2) (quot banner-width 2))
        offset-y (- (quot rows 2) (quot banner-height 2))
        banner-list (concat banner-text)]
    (loop [i 1]
      (if (<= i rows)
        (do (loop [j 1]
              (if (<= j cols)
                (let [id (str i "-" j)
                      banner-xy (+ (* (- i offset-y) banner-width) (- j offset-x))]
                  (if (and (>= j offset-x)
                           (< j (+ offset-x banner-width))
                           (>= i offset-y)
                           (< i (+ offset-y banner-height)))
                    (.append $scene (str "<div id='" id "' class='cell banner'>" (nth banner-list banner-xy "1") "</div>"))
                    (.append $scene (str "<div id='" id "' class='cell bg'>" (rand-int 2) "</div>")))
                  (recur (+ j 1)))))
            (.append $scene (str "<br>"))
            (recur (+ i 1)))))))

(defn animate-bg []
  (let [opacity 0]
    (.each ($ :.cell.bg)
           (fn [i]
             (this-as this
                      (.text ($ this) (str (rand-int 2))))))
    (.each ($ :.cell.banner)
           (fn [i]
             (this-as this
                      (.css ($ this) "opacity" (+ 0.82 (mod (rand) 0.18))))))
    (when keep-alive?
        (wait 90 animate-bg))
    false))

(.addEventListener js/window "load" draw-matrix false)

(.addEventListener js/window "load" animate-bg false)

(.addEventListener js/window "resize" (fn []
                                        (.removeEventListener js/window "load" draw-matrix)
                                        (.removeEventListener js/window "load" animate-bg)
                                        (set! keep-alive? false)
                                        (.html $scene "")
                                        (draw-matrix)
                                        (set! keep-alive? true)
                                        (animate-bg)))
