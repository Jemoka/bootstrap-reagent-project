(ns __app_name.core
    (:require
      ;; Reagent libraries
      [reagent.core :as r]
      [reagent.dom :as d]

      ;; The Preso
      [__app_name.presentation :as p]))

(set! *warn-on-infer* false)

;; -------------------------
;; The presentation mount root

(defn root []
  (into [:div.slides] p/presentation))

;; -------------------------
;; Configure Reveal

(def reveal-params #js 
  {:width 1280 ; Size
   :height 720
   :backgroundTransition "none" ; No transition
   :transition "none"
   :controls false ; No controls nor progressbar
   :progress false
   :center true ; Do center
   :disableLayout false ; Disable layout
   :plugins #js [js/RevealMarkdown js/RevealHighlight js/RevealNotes]})

;; -------------------------
;; Initialize app

(defn ^:export init! []
  ;; Mount root and start reveal
  (d/render [root] 
            (aget (.getElementsByClassName js/document "reveal") 0)
            #(.initialize js/Reveal reveal-params)))

