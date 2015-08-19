(in-ns 'user)

(require '[nsfw.reup :as nr])

(def reup (nr/setup
            {:start-app-sym 'zkimcom.gen/start-app
             :stop-app-sym 'zkimcom.gen/stop-app
             :tests-regex #"zkimcom.*-test"}))

(reup)
