(defproject zkimcom "0.1"
  :description
  "The Developer's Portfolio of Zachary Kim.  This site can be found at zacharykim.com."
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/data.zip "0.1.0"]
                 [nsfw "0.9.0"]
                 [joda-time/joda-time "1.6"]
                 [garden "1.2.5"]
                 [org.clojure/clojurescript "0.0-3165"]
                 [vee "0.2.2"]
                 [reagent "0.5.0"]]
  :plugins [[lein-cljsbuild "1.0.6"]
            [lein-figwheel "0.3.7"]]
  :source-paths ["src/clj"]
  :repl-options {:init (load-file "reup.clj")}
  :min-lein-version "2.0.0"
  :cljsbuild
  {:builds {:dev
            {:source-paths ["src/cljs"]
             :figwheel {:on-jsload "zkimcom.site/reload-hook"}
             :compiler {:output-to "static/cljs/app.js"
                        :output-dir "static/cljs"
                        :optimizations :none
                        :source-map true
                        :main "zkimcom.site"
                        :asset-path "/cljs"}}
            :prod
            {:source-paths ["src/cljs"]
             :compiler {:output-to "static/cljs/app.js"
                        :optimizations :advanced
                        :main dutch.landing
                        :pretty-print false}}}}
  :figwheel {:http-server-root "static"
             :css-dirs ["static/css"]
             :repl false})
