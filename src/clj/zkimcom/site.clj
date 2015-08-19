(ns zkimcom.site
  (:require [hiccup.core :as hc]
            [hiccup.page :as hp]
            [garden.core :refer [css]]))

(def stylesheet
  [[:html :body {:height "100%" :width "100%"}]
   [:body {:font-family "'Lato', sans-serif"}
    [:.nowrap {:white-space 'nowrap}]
    [:.logo {:max-width "100%"
             :display 'block
             :margin-left 'auto
             :margin-right 'auto
             :margin-top "70px"
             :margin-bottom "40px"

             :border-radius "50%"
             :background-color "#79D6FD"
             :padding "10px"}]
    [:.heading {:font-weight 300
                :font-size "33px"
                :line-height "130%"
                :margin-top 0
                :margin-bottom "20px"}
     [:strong {:font-weight 500}]]
    [:.redact {:text-decoration 'line-through
               :color "#aaa"
               :white-space 'nowrap}]
    [:.above-fold
     {:padding-top "50px"
      :min-height "100%"}]
    [:.callout {:margin-bottom "60px"}]
    [:.contact
     {:text-align 'left}
     [:.contact-logo
      {:height "30px"
       :margin-left 0
       :margin "0 30px"}]]

    [:.nav
     {:margin-bottom "25px"}
     [:ul
      {:padding-left 0}
      [:li {:display 'inline-block
            :margin-right "20px"}
       [:a {:font-size "18px"
            :color "#ccc"}]]]]
    [:.section [:h2 {:text-align 'center
                     :padding "40px"
                     :color "white"}]]
    [:.about [:h2 {:background-color "#FF5722"}]]
    [:.featured
     [:h2 {:background-color "#9C27B0"
           :margin-bottom "50px"}]
     [:.featured-work-item
      [:&.light
       [:.header
        {:color 'black}
        [:.caption {:background "linear-gradient(rgba(255,255,255,0.6),rgba(255,255,255,1))"
                    :padding-top "20px"}]]]
      [:.header
       {:width "100%"
        :height "300px"
        :background-size 'cover
        :background-position 'center
        :color 'white
        :position 'relative
        :margin-bottom "30px"}
       [:&.border {:border "solid #e7b8ef 1px"}]
       [:.caption.nogradient {:background 'none}]
       [:.caption {:position 'absolute
                   :bottom 0
                   :left 0
                   :right 0
                   :margin 0
                   :padding "10px"
                   ;;:background-color "rgba(0,0,0,0.5)"
                   :background "linear-gradient(rgba(0,0,0,0),rgba(0,0,0,1))"}
        [:h3 :h4 {:margin 0
                  :line-height "140%"} ]
        [:h3 {:display 'inline-block}]
        [:h4 {:margin-top "5px"
              :text-transform 'uppercase
              :font-weight 300
              :font-size "16px"}]]]
      [:p {:font-size "16px"
           :text-align 'justify
           :text-justfy 'inter-word}]]
     [:.divider {:text-align 'center
                 :margin "60px 0"}]
     [:.mockdbs
      [:.header {:color "black"}
       [:.caption {:background "linear-gradient(rgba(255,255,255,0),rgba(255,255,255,1))"}]]]]]])

(defn featured-section []
  [:div.section.featured
   [:h2 "Featured Work / Talks"]
   [:div.container
    [:div.col-sm-12.col-md-10.col-md-offset-1.col-lg-8.col-lg-offset-2
     [:div.featured-work-item.cuba
      [:div.header {:style "background-image: url('/images/cuba2.jpg'); background-position: bottom;"}
       [:div.caption.nogradient
        [:h3.nowrap "Technology and Entrepreneurship"]
        [:br]
        [:h3.nowrap "in Silicon Valley"]
        [:h4 "Havana, July 2015"]]]
      [:p "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt."]]
     [:div.divider "---"]
     [:div.featured-work-item
      [:div.header {:style "background-image: url('/images/recent_projects/celltracker.png');"}
       [:div.caption
        [:h3 "Intra-Op 3D Visualizer for Deep Brain Stimulation"]
        [:h4 "Java, Eclipse RCP, DICOM"]]]
      [:div.row
       [:div.col-sm-6
        [:p "CellTracker is a 3D visualization program used during surgery to aid in the visualization of recorded data from a neurosurgical procedure called Deep Brain Stimulation. Three-dimensional microelectrode recordings are superimposed on the Schaltenbrand-Wahren atlas, allowing the surgical team to visualize microelectrode-target proximity."]]
       [:div.col-sm-6
        [:p
         "I had the honor of presenting "
         [:a {:href "http://dl.dropbox.com/u/43368535/Poster_3DSTN_2008_Final.pdf"} "our procedures and findings"]
         " at the 2008 International Movement Disorders Congress in Chicago, IL, which was chosen as one a handful of a total of roughly 900 abstracts to be specially featured."]]]]
     [:div.divider "---"]
     [:div.featured-work-item.vee.light
      [:div.header.border {:style "background-image: url('/images/clojuredocs.jpg'); background-position: bottom;"}
       [:div.caption
        [:h3.nowrap "ClojureDocs.org"]
        [:h4 "JVM, Clojure, ClojureScript, React"]]]
      [:div.row
       [:div.col-sm-6
        [:p
         [:a {:href "http://clojuredocs.org"} "ClojureDocs"]
         " is a community powered documentation and examples repository for the Clojure programming language. Supporting searchable documentation and user-submitted examples, the site is a popular destination for developers who are new to the language, receiving around 250k pageviews per month."]]
       [:div.col-sm-6
        [:p "Originally written in 2011 using Rails, the site was reimplemented in early 2015 using Clojure & ClojureScript to provide a more robust and dynamic user experience around searching and creating examples."]]]]
     [:div.divider "---"]
     [:div.featured-work-item.mockdbs
      [:div.header.border
       {:style "background-image: url('/images/mockdbs.jpg'); background-position: 0 20%"}
       [:div.caption
        [:h3 "Intra-Op Neuronal Signal Simulator"]
        [:h4 "Java, Swing, Piccolo2D, Processing"]]]
      [:div.row
       [:div.col-sm-6
        [:p "MockDBS is a simple program that simulates signals generated by neural features encountered during a Deep Brain Stimulation procedure. It allows the user to place simulated neurons along a test track, then descend a probe along that track."]
        [:p "It was originally created for use at the University of Colorado Health Sciences Center as a participatory site in a GAD2 viral vector study, to facilitate adherence to control group protocol."]]
       [:div.col-sm-6
        [:p "Audio output of simulated neurons is produced as a probe is descended, which is fed to standard feedback instrumentation thus simulating difficult to copy intra-operative phenomena which would otherwise be noticeably absent to the awake patient."]]]]
     [:div.divider "---"]
     [:div.featured-work-item.vee
      [:div.header {:style "background-image: url('/images/veedev2.jpg'); background-position: center;"}
       [:div.caption
        [:h3.nowrap "No-Strings Mobile App "]
        " "
        [:h3.nowrap "Development in Clojure"]
        [:h4 "Portland, March 2015"]]]
      [:div.row
       [:div.col-sm-6
        [:p "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt."]]]]
     [:div.divider "---"]
     [:div.featured-work-item.vee
      [:div.header {:style "background-image: url('/images/pairio.jpg'); background-position: 0 40%;"}
       [:div.caption
        [:h3.nowrap "pair.io"]
        [:h4 "Clojure, Ruby, Cljs, MongoDB"]]]
      [:p "On-demand, collaboration-friendly dev environments for your github repo."]]]]])

(def index
  (hp/html5
    [:head
     [:link {:rel "stylesheet" :href "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"}]
     [:link {:rel "stylesheet" :href "http://fonts.googleapis.com/css?family=Lato:400,700,300"}]
     [:link {:rel "stylesheet" :href "css/site.css"}]]
    [:body
     [:div.section.above-fold
      [:div.container
       [:div.row
        [:div.col-sm-12.col-md-10.col-md-offset-1
         [:div.row
          [:div.col-sm-4
           [:img.logo {:src "https://pbs.twimg.com/profile_images/532617885617627136/KEYfBh1r.png"}]]
          [:div.col-sm-8
           [:div.nav
            [:ul
             [:li [:a {:href "#"} "About"]]
             [:li [:a {:href "#"} "Featured Work & Talks"]]]]
           [:div.callout
            [:h1.heading "Hey there. "]
            [:h1.heading
             "This is the professional portfolio of " [:strong "Zachary Kim"] ", an Engineer and Entrepreneur located in "
             [:span.redact "Honolulu, HI,"]
             " "
             [:span.redact "Denver, CO,"]
             " "
             [:span.redact "Chicago, IL,"]
             " "
             [:span.nowrap "San Francisco, CA"]
             "."]]
           [:div.contact
            [:img.contact-logo {:src "images/email-logo.png"}]
            [:img.contact-logo {:src "https://cdn4.iconfinder.com/data/icons/iconsimple-logotypes/512/github-512.png"}]
            [:img.contact-logo {:src "images/twitter-logo.png"}]
            [:img.contact-logo {:src "images/linkedin-logo.png"}]
            ]]]]]]]
     [:div.section.about
      [:h2 "About Me"]
      [:div.container
       [:div.col-sm-12.col-md-10.col-md-offset-1
        ]]]
     (featured-section)
     (repeat 20 [:br])
     [:script {:type "text/javascript" :src "cljs/app.js"}]]))

(def pages
  {"index.html" index})
