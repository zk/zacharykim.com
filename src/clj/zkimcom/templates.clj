(ns zkimcom.templates
  (:use [hiccup core [page-helpers :only (doctype)]]
        [nsfw util html])
  (:require [clojure.string :as str]))

(defn ganalytics []
  [:script {:type "text/javascript"}
   "var gaJsHost = ((\"https:\" == document.location.protocol) ? \"https://ssl.\" : \"http://www.\");document.write(unescape(\"%3Cscript src='\" + gaJsHost + \"google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E\"));"]
  [:script {:type "text/javascript"}
   "try {var pageTracker = _gat._getTracker(\"UA-15848550-1\");pageTracker._trackPageview();} catch(err) {}"])

(defn nav []
  (html
   [:nav
    [:a {:href "/"} "Home"]
    [:a {:href "/featured-work"} "Featured Work"]]))

(defn landing-header []
  [:header
   [:div {:class "container_16"}
    [:div {:class "grid_6"}
     [:h1
      [:a {:href "/"}
       [:img {:src "/images/site_logo_big.png"}]]]]
    [:div {:class "grid_10"}
     (nav)
     [:h1 "zacharykim.com"]
     [:p "The professional portfolio of Zachary Kim, a software developer in "
      [:span.redact "Honolulu,"]
      " "
      [:span.redact "Denver,"]
      " "
      "San Francisco, CA"
      "."]]
    [:div {:class "grid_16"}
     [:hr]]]
   [:div {:class "clear"}]])

(defn contact-me []
  [:div {:class "contact_me"}
   [:h3 "Contact Me"]
   [:ul
    [:li {:class "email"}
     [:a {:href "mailto:zack@zacharykim.com"} "zack@zacharykim.com"]]
    [:li {:class "github"}
     [:a {:href "http://github.com/zkim"} "github"]]
    [:li {:class "twitter"}
     [:a {:href "http://twitter.com/_zkim"} "@_zkim"]]
    [:li {:class "linkedin"}
     [:a {:href "http://www.linkedin.com/pub/zachary-kim/3/a48/456"} "Linked In Profile"]]
    [:li {:class "google_reader"}
     [:a {:href "http://www.google.com/reader/shared/14149351528493136073"}
      "Shared Google Reader"]]]
   [:p "Contact me for a copy of my resume."]])

(defmulti render-social-content :type :default :default)

(defmethod render-social-content :default [sc])

(defmethod render-social-content :reader [sc]
  [:li {:class "google_reader"}
   [:a {:href (:href sc)} (:title sc)]])

(def href-regex #"(?i)\b((?:https?:\/\/|www\d{0,3}[.]|[a-z0-9.\-]+[.][a-z]{2,4}\/)(?:[^\s()<>]+|\(([^\s()<>]+|(\([^\s()<>]+\)))*\))+(?:\(([^\s()<>]+|(\([^\s()<>]+\)))*\)|[^\s`!()\[\]{};:\".,<>]))")
(def user-regex #"((^|\s)@[a-zA-Z0-9_-]*)")
(def hash-regex #"((^|\s)#[a-zA-Z0-9_-]*)")

(defmethod render-social-content :twitter [sc]
  [:li {:class "twitter"}
   (-> (:content sc)
       (str/replace
        href-regex
        #(html [:a {:href (first %)} (first %)]))
       (str/replace
        user-regex
        #(html
          [:a {:href (str "http://twitter.com/" (str/trim (str/replace (first %) "@" "")))}
                (first %)]))
       (str/replace
        hash-regex
        #(html [:a {:href (str "http://twitter.com/#search?q=" (str/trim (str/replace (first %) "#" "")))}
                (first %)])))
   " - "
   [:a {:href (:href sc)} "link"]
   ""])

(defn latest-content [social-content]
  [:div {:class "latest_content"}
   [:h3 "Latest Social Content"]
   [:ul
    (map render-social-content social-content)]])

(defn work-exp []
  [:div {:class "work_experience"}
   [:h3 "Work"]
   [:ul
    [:li
     [:h4 "1/12 - Present"]
     [:h5 "Director of Platform"]
     [:p [:a {:href "https://zaarly.com"} "Zaarly"]]]
    [:li
     [:h4 "10/10 - 10/11"]
     [:p "Contract"]]
    [:li
     [:h4 "3/10 - 10/10"]
     [:h5 "Founder"]
     [:p "Security Blanket"]]
    [:li
     [:h4 "10/08 - 2/10"]
     [:h5 "Chief Architect"]
     [:p [:a {:href "http://csgresults.com"} "Customer Solutions Group"]]]
    [:li
     [:h4 "4/07 - 10/08"]
     [:h5 "Software Programmer Analyst"]
     [:p
      "RapidACE & "
      [:a {:href "http://geneseeacademy.com"}
       "Genesee Academy"]]]
    [:li
     [:h4 "9/05 - 4/07"]
     [:p "Contract"]]
    [:li
     [:hr]
     [:h4 "5/05 - 10/11"]
     [:h5 "Research, Neurosurgery"]
     [:p
      [:a {:href "http://cuneurosurgery.com/"}
       "University of Colorado Health Sciences Center"]]]]])

(defn footer []
  (html
   (container-16
    (grid-16
     [:div {:class "footer"}
      (href "/" "Home")
      " | "
      (href "/featured-work" "Featured Work")]))))

(defn index [social-content]
  (html
   (doctype :html5)
   [:html
    [:head
     [:title "Zachary Kim's Portfolio"]
     (meta-tag "Content-Type" "text/html;charset=utf-8")
     (meta-tag "google-site-verification" "8YA4jEQCCmmF3PZriyv6oErL3IPaMJgI0TCipMYSfLk")
     [:link {:rel "icon" :href "favicon.ico" :type "image/x-icon"}]
     [:link {:rel "shortcut icon" :href "favicon.ico" :type "image/x-icon"}]
     (include-css :reset :text :960 :app)
     (ganalytics)
     [:title "Zachary Kim's Portfolio"]]
    [:body
     [:div {:class "header_spacer"}]
     (landing-header)
     [:div {:class "container_16"}
      [:div {:class "grid_5 prefix_1"}
       (contact-me)]
      [:div {:class "grid_4 suffix_2"}
       (latest-content social-content)]
      [:div {:class "grid_4"}
       (work-exp)]]
     (footer)]]))

(defn project [& opts]
  ":href :name :tech :image :image-thumb :image-title :content :thumb-size"
  (let [opts (apply hash-map opts)]
    (html
     [:div {:class "project"}
      (if (:href opts)
        [:a {:href (:href opts)}
         [:h2 (:name opts)]]
        [:h2 (:name opts)])
      [:div {:class "links"}
       (when (:github opts)
         (href (:github opts)
               "github"))]
      [:div.tech (:tech opts)]
      (href (image-url (str "recent_projects/" (:image opts) ".png"))
            (image (str "recent_projects/" (:image opts) "_thumb.png")
                   :class "project_image lightbox"
                   :alt (:image-title opts)
                   :width (first (:thumb-size opts))
                   :height (second (:thumb-size opts)))
            :class "lightbox"
            :title (:image-title opts))
      [:div.content
       

       (:content opts)]
      [:div {:class "clear"}]])))


(defn mockdbs []
  (project
   :href "http://mockdbs.com"
   :name "MockDBS"
   :tech "Java, Swing, Piccolo2D, Processing"
   :image "mockdbs"
   :image-title "MockDBS running on OS X"
   :thumb-size [281 195]
   :content [:div
             [:p "MockDBS is a simple program that simulates signals generated by neural features encountered during a Deep Brain Stimulation procedure. It allows the user to place simulated neurons along a test track, then descend a probe along that track."]
             [:p
              "It was originally created for use at the "
              (href "http://cuneurosurgery.com"
                    "University of Colorado Health Sciences Center")
              " as a participatory site in a GAD2 "
              (href "http://en.wikipedia.org/wiki/Viral_vector"
                    "viral vector")
              " study, to facilitate adherence to control group protocol. Audio output of simulated neurons is produced as the simulated probe is descended, thus simulating difficult to copy intra-operative phenomena which would otherwise be noticeably absent to the awake patient."]]))

(defn clojuredocs []
  (project
   :href "http://clojuredocs.org"
   :name "ClojureDocs"
   :tech "Ruby/Rails, Clojure"
   :image "clojuredocs_screenshot"
   :image-title "ClojureDocs Home Page"
   :thumb-size [281 203]
   :content [:p 
             "A community-powered documentation and examples repository for the "
             (href "http://clojuredocs.org" "Clojure programming language.")]))

(defn jotting []
  (project
   :href "http://jotting.zacharykim.com"
   :name "Jotting"
   :tech "Ruby/Rails, Clojure, XMPP-clj"
   :image "jotting_screenshot"
   :image-title "Jotting Home Page"
   :thumb-size [281 213]
   :content [:div
             [:p "Jotting allows you to record quick thoughts or notes to yourself throughout your day. Interaction with Jotting is done through two separate channels: web and instant messaging."]
             [:p "The web side is implemented using Rails, the instant messaging side is implemented in Clojure, and the two are integrated through a MySQL database."]]))

(defn celltracker []
  (project
   :href "http://vimeo.com/1223005"
   :name [:span "CellTracker " [:span {:class "tag"} "( video )"]]
   :tech "Java, Eclipse RCP, JMonkey3D, DICOM Toolkit"
   :image "celltracker"
   :image-title "Microelectrode Events against a STN-specific SW Atlas"
   :thumb-size [281 201]
   :content [:div
             [:p "CellTracker is a pseudo-3D visualization program used to aid in the visualization of recorded data from a neurosurgical procedure called Deep Brain Stimulation. Micro-electrode recordings (MERs) are superimposed on a brain map called the Schaltenbrand-Wahren atlas, which is displayed as 2D slices in 3D space. This allows the surgeon to visualize previously recorded data in context."]
             [:p "A "
              (href "http://dl.dropbox.com/u/43368535/Poster_3DSTN_2008_Final.pdf"
                    "poster")
              " of our procedures and findings was presented at the 2008 International Movement Disorders Congress in Chicago, IL. I was part of the team that was invited to present there, and was chosen as one a handful of a total of roughly 900 abstracts to be specially featured."]]))

(defn rapidace []
  (project
   :name "RapidACE 3D Data Modeling Visualization"
   :tech "Java, Swing, WilmaScope 3D"
   :image "rapidace"
   :image-title "DDL Visualization"
   :thumb-size [281 201]
   :content [:div
             [:p "At RapidACE I designed and integrated a 3D DDL visualization tool that was embedded into our flagship software product, a schema-aware database modelling tool. I used WilmaScope, a graph visualization library to accomplish this."]
             [:p "Soon after implementation, our requirements outgrew the stock capabilities of the library, and I replaced the provided force-directed layout engine with a custom one that allowed more control over the clustering and steady-state behaviors of visualized graphs."]]))

(defn cljs []
  (project
    :href "http://github.com/zkim/cljs"
    :name "cljs"
    :tech "Clojure"
    :image "cljspad"
    :image-title "CljsPad - A Live-Console For Cljs"
    :thumb-size [281 224]
    :content [:div
              [:p "An experimental Clojure(ish)-to-JavaScript compiler with compile-on-save, automatic dependency management, and Leiningen integration. "]]))

(defn pairio []
  (project
    :href "https://pair.io"
    :name "pair.io"
    :tech "Clojure, Ruby, Cljs, MongoDB, Heroku"
    :image "pairio"
    :image-title "Pair.io Session Page"
    :thumb-size [281 215]
    :content [:div
              [:p "On-demand, collaboration-friendly dev environments for your github repo."]
              [:ul
               [:li [:a {:href "http://www.youtube.com/watch?v=YbQb_8EdfU8"}
                     "5 min overview video."]]]]))

(defn featured-work []
  (html
   (doctype :html5)
   [:html
    [:head
     [:title "Zachary Kim's Portfolio"]
     (meta-tag "Content-Type" "text/html;charset=utf-8")
     (meta-tag "google-site-verification" "8YA4jEQCCmmF3PZriyv6oErL3IPaMJgI0TCipMYSfLk")
     [:link {:rel "icon" :href "favicon.ico" :type "image/x-icon"}]
     [:link {:rel "shortcut icon" :href "favicon.ico" :type "image/x-icon"}]
     (include-css :reset :text :960 :app :jquery.lightbox-0.5)
     [:script {:type "text/javascript" :src "/js/jquery-1.4.4.min.js"}]
     [:script {:type "text/javascript" :src "/js/jquery.lightbox-0.5.pack.js"}]
     [:script {:type "text/javascript"}
      "$(document).ready(function() { $('a.lightbox').lightBox(); });"]
     (ganalytics)
     [:title "Zachary Kim's Portfolio"]]
    [:body
     [:div {:class "header_spacer"}]
     (container-16
      (grid 6
            [:div {:class "header_wrapper"}
             (href "/"
                   (image "site_logo_big.png" :class "site_logo" :width 296 :height 296))
             [:hr]
             [:div {:class "grid_5 prefix_1 alpha omega contact_me"}
              (contact-me)]])
      (grid 10
            (nav)
            [:div {:class "featured_work"}
             [:h1 "Featured Work"]
             (celltracker)
             (pairio)
             (clojuredocs)
             (mockdbs)
             (cljs)
             (jotting)
             (rapidace)]))
     (footer)]]))


