(ns studio_six_ten_dot_com.handler
  (:use org.httpkit.server
        hiccup.core
        hiccup.page)
  (:require [ring.middleware.reload :as reload]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]))

(defn splash "Splash page"
  [req]
  (html5
   [:html
    [:head
     [:title (str "Studio Six-Ten I.D. Inc.")]
     (include-css "main.css")]
    [:body
     [:div#splashScene]
     (include-js "//code.jquery.com/jquery-1.11.0.min.js"
                 "main.js")]]))

(defroutes app-routes
  (GET "/" [req] (splash req))
  (route/resources "/")
  (route/not-found "Not Found"))

;(def app
;  (handler/site app-routes))

(defn -main []
  (run-server (reload/wrap-reload (handler/site #'app-routes)) {:port 3000}))
