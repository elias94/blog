(require '[reagent.core :as r]
         '[reagent.dom :as rdom]
         '[clojure.string :as string])

(defn editor
  [id source]
  (let [cm (.fromTextArea  js/CodeMirror
                           (.getElementById js/document id)
                           #js {:mode "clojure"
                                :theme "gruvbox"
                                :lineNumbers true
                                :smartIndent true
                                :tabSize 2})]
    (.on cm "change" (fn [_ _] (reset! source (.getValue cm))))
    (.setSize cm "auto" "auto")))

(defn render-component [res]
  (let [id (random-uuid)]
    (r/create-class
     {:display-name "render-component"
      :component-did-mount
      (fn []
        (rdom/render [res] (.getElementById js/document (str id))))
      :reagent-render
      (fn []
        [:div {:id id}])})))

(defn result-component
  [source]
  (fn [source]
    (let [res (try (js/scittle.core.eval_string source)
                   (catch :default e
                     {:error (.-message e)}))
          error? (contains? res :error)]
      [:div.result
       [:pre
        (cond
          ;; To be rendered needs to be a function with meta :reagent true
          (and (fn? res) (-> res meta :reagent true?))
          [render-component res]

          :else [:<>
                 [:div (if error? "Error:" "Output:")]
                 [:code
                  (str (if error? (:error res) res))]])]])))

(defn run-clj
  [elem]
  (let [id (gensym "src-")
        src-str (.-innerText elem)
        child (first (.-childNodes elem))
        source (r/atom (string/trim-newline src-str))]
    (rdom/render [:textarea {:id id :rows 1} src-str] child)
    (editor id source)
    (rdom/render [result-component @source] child)))

(defn js-codeblock
  [id]
  (let [cm (.fromTextArea  js/CodeMirror
                           (.getElementById js/document id)
                           #js {:mode "javascript"
                                :theme "gruvbox"
                                :lineNumbers true
                                :smartIndent true
                                :tabSize 2
                                :readOnly "nocursor"})]
    (.setSize cm "auto" "auto")))

(defn show-js
  [elem]
  (let [id (gensym "src-")
        src-str (.-innerText elem)
        child (first (.-childNodes elem))]
    (rdom/render [:textarea {:id id} src-str] child)
    (js-codeblock id)))

(defn run-editor []
  (let [clj-blocks (vec (.getElementsByClassName js/document
                                                 "language-clojure"))
        js-blocks  (vec (.getElementsByClassName js/document
                                                 "language-javascript"))]
    (mapv run-clj clj-blocks)
    (mapv show-js js-blocks)))

;; Add a function render used to add a reagent meta to the parameter (function)
(js/scittle.core.eval_string "(defn render [f] (with-meta f {:reagent true}))")

(run-editor)
