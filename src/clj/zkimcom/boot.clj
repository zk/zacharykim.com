(ns zkimcom.boot
  (:use (ring.middleware file
                         file-info
                         params
                         nested-params
                         keyword-params
                         multipart-params
                         session)
        [ring.middleware.session.memory])
  (:require [net.cgrand.moustache :as mou]
            [somnium.congomongo :as mongo]
            [nsfw.middleware :as nsfwm]
            [zkimcom.actions :as actions]
            [nsfw.server :as server]))


(def session-store (atom {}))

(mongo/mongo! :db :zkimcom)

(def entry-handler
  (-> (var actions/routes)
      (wrap-session {:store (memory-store session-store)})
      (wrap-keyword-params)
      (wrap-nested-params)
      (wrap-params)
      (wrap-file "resources/public")
      (wrap-file-info)
      (nsfwm/wrap-log-request)
      (nsfwm/wrap-stacktrace)))

(defn start-server [port]
  (server/start (var entry-handler) port))





