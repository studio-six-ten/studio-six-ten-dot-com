(ns studio_six_ten_dot_com.mainjs
  (:use [jayq.core :only [$ css html]]))

(def $document ($ js/document))

(.write js/document (str "document.height: "
                         (.height $document)
                         "px; document.width: "
                         (.width $document) "px."))
