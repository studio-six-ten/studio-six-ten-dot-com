(defproject studio-six-ten-dot-com "0.1.0-SNAPSHOT"
  :description "Source repository of the official Studio Six-Ten I.D. Inc. web site."
  :url "http://studio-six-ten.com"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.8"]]
  :plugins [[lein-ring "0.8.11"]]
  :ring {:handler studio-six-ten-dot-com.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
