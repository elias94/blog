---
layout: post
title: Clojure interactive
date: 2022-02-23
categories: clojure
desc: I published [tryclojure.org](https://tryclojure.org) this week and I would like to discuss the potential of having a REPL what allows you to interact with the webpages.
---

{% include editor.html %}
{% include tailwind.html %}

> Go to the article on Logo, can you write and execute Logo programs? Are there examples? No. The Wikipedia people didn’t even imagine that, in spite of the fact that they’re on a computer.<br/>[Alan Kay](https://link.springer.com/content/pdf/bbm%3A978-3-319-90008-7%2F1.pdf)

Alan Kay here is referring to how code is, in general, not editable and executable on the web.
The web pages are composed by static content and code blocks are treated in the same way as text.

I feel that something is missing, like when I put in my hands one of those old chess manuals,
with one image and a series of chess notation text. If you are not a pro, you probably have to use
a chessboard or a chess app to follow the game and learn.

![chess book](/assets/img/cn6780_lasker.jpg)
_Chess book example_

Fortunately the web offers modern UIs like [lichess.org](https://lichess.org/) where I
can replay and see comments of grandmasters on famous games and go backward or 
forward is the game.

But isAlan Kay has more...

> Go to a blog, go to any Wiki, and find one that’s WYSIWYG like Microsoft Word is. […] The Web was done after that, but it was done by people who had no imagination. They were just trying to satisfy an immediate need. Because what you definitely don’t want in a Web browser is any features. […] You want it to be a
> mini-operating system.<br/>[Alan Kay](https://link.springer.com/content/pdf/bbm%3A978-3-319-90008-7%2F1.pdf)

Okay, here he's probably imaging an old school programmable interface a la Emacs
([here an example](https://nyxt.atlas.engineer/)) for navige the network. But
what we can extract from those quotes is that Alan Kay would like to have a interactive
version of the web, far away from HTML and CSS.

## The Clojure way

I published [TryClojure.org](https://tryclojure.org) this week and and I would like,
in this article, to show you some live code.

Clojure is a **Lisp** dialect, and as its predecessors, is able to
help the programmers getting software written faster and better compared to
other languages.

In Clojure it’s faster developing projects because the language offers multiple
benefits. Its designed to be _hosted_, so uses the **Java** VM on the desktop 
 and **Javascript** VM on the web side. This means that, 
other than the large set of libraries created for Clojure, you can use the Java
 libraries and all the entire **NPM** ecosystem. Clojure can be used to develop
 for desktop, for Node.js, all modern mobile OS and all web browsers.

Adding a REPL to the page has been done in around _300 lines_ well formatted
and commented. Some bugs has been fixed quickly and seems now pretty reliable.
The code executed in a sandbox environment with some custom function to interact
with the application state, like changing the prompt color. Has been easy to
implement thanks to [SCI](https://github.com/borkdude/sci), a Clojure interpreter
_smaller_ than the original Clojurescript and ready to be embedded.

It's easy to have Clojure editable code inside a webpage, like this Jekyll blog. For example we can
try to calculate the **wonderland-number** from [wonderland-clojure-katas](https://github.com/gigasquid/wonderland-clojure-katas). It's a special number described as:

- It has six digits
- If you multiply it by 2,3,4,5, or 6, the resulting number has all the same digits in at as the original number. The only difference is the position that they are in.

```clojure
(def wonder-range (range 100000 1000000))

(defn create-set [n]
  (-> n str set))

(defn check-multi [n]
  (let [set-n (create-set n)
        r (range 2 7)]
    (every?
     (fn [m] ; inline function to compare the sets
       (= set-n (create-set (* n m))))
     r)))

(->> wonder-range
    (filter check-multi)
    first)
```

We are taking all the 6 digits numbers (so in the range 100K-1M), and comparing the digits of each of them to their
multiplication for the factors 2,3,4,5,6. If all those comparison are true, that's the wonderland number.
We're using a set that in Clojure gives back the distinc elements that are composing
the collection.

There's [**Reagent**](https://reagent-project.github.io/) library to use **React.js**
with the Clojure syntax. Instead of JSX, reagent uses _hiccup_ that means we can
use vectors and keywords to describe the UI. Let's see an example of a simple
counter.

```clojure
(def counter (r/atom 0))

(defn view []
  [:div
   [:p {:class ["text-sm" "text-gray-300" "mb-2"]}
    (str "Clicks: " @counter)]
   [:button
    {:class ["cursor-pointer"
             "border"
             "border-gray-500"
             "py-1"
             "px-2"
             "rounded-md"]
     :on-click #(swap! counter inc)}
    "Click me"]])

(render view) ; Custom render function
```

Is pretty simple, instead of declaring via JSX `<button className=[] onClick={}>`
we can write `[:button {:class [] :on-click #()}]` using the Clojure syntax.

What’s happening in the code? We declared an atom with `r/atom`. It's a special data type that allow the state to be updated using functions as `swap!` or `reset!`. The state modification are synchronous . You have to create a sort of transaction like in a db. So you can change the application state using a function.

## How to add a Clojure REPL
