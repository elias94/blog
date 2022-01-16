#!/usr/bin/env bb

(require '[clojure.tools.cli :refer [parse-opts]])
(require '[clojure.string :as string])
(import 'java.time.format.DateTimeFormatter
        'java.time.LocalDateTime)

(defn post-template [title]
  (str "---\nlayout: post\ntitle:  "
       title
       "\ndate:   2021-12-08\ncategories: how"
       "\n---\n\n"))

(def cli-options
  [["-t" "--title TITLE" "New Post Title"
    :default "undefined"
    :validate [#(= (type %) java.lang.String) "Must be a string"]]
   ["-e" "--extension EXTENSION" "File extension"
    :default ".md"
    :validate [#(clojure.string/starts-with? % ".")
               "Must be a file extension starting with `.`"]]
   ["-h" "--help" "Display the help message"]])

(def date (LocalDateTime/now))
(def formatter (DateTimeFormatter/ofPattern "yyyy-MM-dd"))

(defn post-filename [title ext]
  (str
   "src/_posts/"
   (.format date formatter)
   "-"
   (string/lower-case (string/replace title #" " "-"))
   ext))

(defn create-post
  "Create new Jekyll post (markdown)."
  [{title :title ext :extension}]
  (spit
   (post-filename title ext)
   (post-template title)))

(let [{:keys [options summary errors] _ :arguments}
      (parse-opts *command-line-args* cli-options)]
  (when errors
    (println (string/join "\n" errors))
    (System/exit 1))
  (if (true? (:help options))
    (println (str "Usage: bb scripts/new-post.clj <title>\n\n"
                  "Create a new post named according to the following format:\n"
                  "YEAR-MONTH-DAY-title.MARKUP\n"
                  "\nFlags:\n" summary))
    (create-post options)))
