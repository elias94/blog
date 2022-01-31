---
layout: post
title:  Cljs interop with JS and NPM
date:   2021-12-08
categories: how
published: false
---

In Clojurescript you usually have to deal with vanilla Javascript and  
NPM modules. Below there's a quick reference on the interoperability
between the languages. See the end of the article for references with explanation.

### Access properties

```clojure
(. js/body -title)
```
```js
body.title
```

### Access nested properties

```clojure
(.. evt -target -value)
```
```js
evt.target.value
```

### Access functions

```clojure
(. js/document hasFocus)

(.create Editor #js{:value "Content"})
```
```js
document.hasFocus()

Editor.create({value: "Content"})
```

### Import NPM module

```clojure
(ns app.page
  (:require []))
```
```js
evt.target.value
```

## Reference

- Clojurescript interop with javascript - [lwhorton.github.io](https://lwhorton.github.io/2018/10/20/clojurescript-interop-with-javascript.html#js)
