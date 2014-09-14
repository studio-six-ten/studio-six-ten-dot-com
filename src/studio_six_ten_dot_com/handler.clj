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
     [:title (str "Studio Six-Ten I.D. Inc.")]]
    [:body
     [:h1.title (str "Hello? This is a little different...")]
     [:div#splashScene]
     (include-js "main.js")]]))

(defroutes app-routes
  (GET "/" [req] (splash req))
  (route/resources "/")
  (route/not-found "Not Found"))

;(def app
;  (handler/site app-routes))

(defn -main []
  (run-server (reload/wrap-reload (handler/site #'app-routes)) {:port 3000}))
