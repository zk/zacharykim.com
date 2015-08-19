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

(defn routes [latest-social-content-atom]
  (mou/app
   [""] (actions/index latest-social-content-atom)
   ["recent-projects"] actions/recent-projects
   ["featured-work"] actions/featured-work
   [&] actions/four-oh-four))

(defn entry-handler [latest-social-content-atom]
  (-> (routes latest-social-content-atom)
      (wrap-session {:store (memory-store session-store)})
      (wrap-keyword-params)
      (wrap-nested-params)
      (wrap-params)
      (wrap-file "resources/public")
      (wrap-file-info)
      (nsfmw/wrap-stacktrace)))

