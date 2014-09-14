(defproject studio_six_ten_dot_com "0.1.0-SNAPSHOT"
  :description "Source repository of the official Studio Six-Ten I.D. Inc. web site."
  :url "http://studio-six-ten.com"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2322"]
                 [compojure "1.1.8"]
                 [ring/ring-devel "1.3.1"]
                 [ring/ring-core "1.3.1"]
                 [http-kit "2.1.16"]]
  :main studio_six_ten_dot_com.handler
  :plugins [[lein-cljsbuild "1.0.3"]]
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}}
  :cljsbuild {:builds [{:source-paths ["src-cljs"]
                        :compiler {:output-to "resources/public/main.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]})
