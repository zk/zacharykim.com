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
            [nsfw.middleware :as nsfmw]
            [zkimcom.actions :as actions]))

(def session-store (atom {}))

(def routes
  (mou/app
   [""] actions/index
   ["recent-projects"] actions/recent-projects
   ["featured-work"] actions/featured-work
   [&] actions/four-oh-four))

(def entry-handler
  (-> (var routes)
      (wrap-session {:store (memory-store session-store)})
      (wrap-keyword-params)
      (wrap-nested-params)
      (wrap-params)
      (wrap-file "resources/public")
      (wrap-file-info)
      (nsfmw/wrap-log-request)
      (nsfmw/wrap-stacktrace)))

