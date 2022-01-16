(require '[reagent.core :as r]
         '[reagent.dom :as rdom]
         '[clojure.string :as string])

(defn editor
  [id state]
  (let [lines (string/split-lines @state)
        cm (.fromTextArea  js/CodeMirror
                           (.getElementById js/document id)
                           #js {:mode "clojure"
                                :theme "gruvbox"
                                :lineNumbers true
                                :smartIndent true
                                :tabSize 2})]
    (.on cm "change" (fn [_ _]
                       (reset! state (.getValue cm))))
    (.setSize cm "auto" "auto")))

(defn renderable-element?
  [elem]
  (and (vector? elem)
       (keyword? (first elem))
       (not= (str (first elem)) ":")
       (not (re-matches #"[0-9]+" (name (first elem))))
       (not (re-matches #".*[\W]+" (name (first elem))))))

(defn renderable?
  [elem]
  (when (or (renderable-element? elem) (seq? elem))
    (let [[k props content] elem
          [props content] (if (and (nil? content)
                                   (not (map? props)))
                            [nil props]
                            [props content])]
      (cond
        (seq? elem) (not (empty? (filter renderable? elem)))
        (seq? content) (not (empty? (filter renderable? content)))
        :else (or (renderable-element? content)
                  (renderable-element? elem)
                  (string? content)
                  (number? content))))))

(defn result-component
  [state]
  (fn [state]
    (let [res (try {:result (js/scittle.core.eval_string @state)}
                   (catch :default e
                     {:error (.-message e)}))
          error? (contains? res :error)]
      [:div.result
       [:pre
        (if (renderable? res)
          [:div res]
          (list
           [:div (if error? "Error:" "Output:")]
           [:code
            (str (if error? (:error res) (:result res)))]))]])))

(defn run-clj
  [elem]
  (let [id (gensym "src-")
        src-str (.-innerText elem)
        child (first (.-childNodes elem))
        state (r/atom (string/trim src-str))]
    (rdom/render [:textarea {:id id} src-str] child)
    (editor id state)
    (rdom/render [result-component state] child)))

(defn js-codeblock
  [id lines]
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
    (js-codeblock id (string/split-lines (string/trim src-str)))))

(defn run! []
  (let [clj-blocks (vec (.getElementsByClassName js/document
                                                 "language-clojure"))
        js-blocks  (vec (.getElementsByClassName js/document
                                                 "language-javascript"))]
    (mapv run-clj clj-blocks)
    (mapv show-js js-blocks)))

(run!)
