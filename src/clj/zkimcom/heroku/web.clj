(ns zkimcom.heroku.web
  (:require [nsfw.server :as server]
            [zkimcom.boot :as boot]))

(defonce s (server/make (var boot/entry-handler)))

(server/start s :port (Integer/parseInt (or (System/getenv "PORT") "8080")) :max-threads 20)
