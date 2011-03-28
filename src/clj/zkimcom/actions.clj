(ns zkimcom.actions
  (:use [nsfw util render]
        [ring.util.response :only (file-response redirect)])
  (:require [net.cgrand.moustache :as mou]
            [zkimcom.templates :as tpls]
            [zkimcom.social :as social]))

(defn index [req]
  (render tpls/index (social/latest! 5)))

(defn recent-projects [req]
  (redirect "/featured-work"))

(defn four-oh-four [req]
  (file-response "resources/public/404.html"))

(defn featured-work [req]
  (render tpls/featured-work))

(def routes
  (mou/app
   [""] index
   ["recent-projects"] recent-projects
   ["featured-work"] featured-work
   [&] four-oh-four))
