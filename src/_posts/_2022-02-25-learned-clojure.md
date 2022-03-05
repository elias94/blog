---
layout: post
title: How I learned Clojure
date: 2022-02-25
categories: learn clojure
desc: ...
---

In the last few months I decided to learn Clojure, and has been a surprisingly pleasure journey. 
Here I collected a few resources to help you make your path learning the language. 

### Setup

First of all you need to [install Clojure](https://clojure.org/guides/getting_started#_clojure_installer_and_cli_tools) and one helpful tool for managing your project called [Leningen](https://leiningen.org/).
It takes care of dependencies and configuration. In alternative you can use [Clojure CLI](https://clojure.org/guides/deps_and_cli).

If you like visual editors like vscode, there’s a brilliant plugin called [Calva](https://marketplace.visualstudio.com/items?itemName=betterthantomorrow.calva) that has everything already included for you. 
I recommend it expecially for *beginners* because automates the process of building and connecting a REPL,
colors the parenthesis, autoformats your code, adds functions documentations and so on.

### Tour of the language

If you have never user Clojure before, I created [TryClojure.org](https://tryclojure.org) to give
you an idea of the Clojure syntax. Plus you can test Clojure by typing commands in the REPL
(ping me if you break it!).

Clojure offer a brilliant, **must have**, interactive tutorial for learn the basic. 
Is the [Clojure Koans](http://clojurekoans.com/), a series of uncompleted exercise that you’ve to fill them up to run the code. It covers all the core features of the language. I used [Clojure by Examples](http://kimh.github.io/clojure-by-example/#about) to initially understand the sytax of the koans.

Once gained familiarity with the language, the best summary has been [Clojure Distilled](https://yogthos.net/ClojureDistilled.html) so far. Is pretty much all the core concepts encoded in a brilliant article. To read more than once.

### Start coding

> The only way to learn a new programming language is by writing programs in it. - Kernighan and Ritchie

Open source is one of the biggest advantages of our generations. We can read, 
copy, edit and learn how to code just looking at the work of other more experienced developers.

Since my approach to learn a new language is more practical, I straight jumped
into write some small exercise applications. I wrote a Brainfuck and a BASIC 
interpeters plus some other example from rosetta code.

After I decided to jump into Clojurescript, using [Reagent](https://reagent-project.github.io/)
(React) to complete a web benchmark called [7GUIs](https://eugenkiss.github.io/7guis/) where I
took familiarity with the interop between Cljs code and Js.

### Use the REPL

One of the main advantage of Clojure, compared to other languages, is that you 
can evaluate the code that you’re writing directly in the REPL.
If you’re familiar with the browser console, the python or Ruby REPL, 
the Clojure one is pretty much the same concept on steroids. Once started in a 
separate window in vscode, I just evaluate my code and test it both on the server
and the client, interacting with the app and it's current state. It changed my 
development approach with the result of a much more linear code writing.

### Ask for help

Before asking help in the community, is usually a good practice in Clojure to 
follow a serie of steps.

1. Learn how to check on Google (I usually append the keyword `clj`).
2. Check the [Community documentation](https://clojuredocs.org/) for functions 
that always contains practical examples on how to use it.
3. Try it yourself in the REPL, since it's fast and you cannot break nothing.

In Clojure I found the most supportive and active community around a programming language, in my experience.
So asking for help 
-  [Clojurians Slack](https://clojurians.slack.com/messages)  
- [Clojure Reddit](https://www.reddit.com/r/Clojure/)
 

## Video Tutorial
I’m not a fan of video, I think reading is much more fast then listening/watching.  But I found that [this guy](https://www.youtube.com/channel/UCKlYSDBb1KBcZyCRbniW1ig) is uploading fresh Clojure tutorials.

## Books

Another alternative is the complete guided book [Clojure for the Brave and True](https://www.braveclojure.com/clojure-for-the-brave-and-true/).

- **The Joy of Clojure**: a really good book, not pedantic, well organized and complete. Teach you how to **think** in Clojure.
- **Elements of Clojure**: probably the most visually curated book that I’ve read until now. Not for beginners.

## Articles

The famous [Beating the Averages](http://www.paulgraham.com/avg.html) by Paul Graham will give you a good motivation on the advantages of functional languages.

## Videos
- [Clojure in a nutshell by James Trunk](https://www.youtube.com/watch?v=C-kF25fWTO8)
- [Effective Programs - 10 Years of Clojure - Rich Hickey](https://www.youtube.com/watch?v=2V1FtfBDsLU)
- and more [Rich Hickey talks](https://changelog.com/posts/rich-hickeys-greatest-hits)
