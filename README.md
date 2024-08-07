![](src/resources/images/demo.png)

![](https://www.codefactor.io/repository/github/clairebearz32bit/xkcdapp/badge)
![[](https://www.gnu.org/licenses/gpl-3.0)](https://img.shields.io/badge/license-GPLv3-green)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/clairebearz32bit/xkcdApp)

<p align="center">
    <a href="https://github.com/clairebearz32bit/xkcdApp#features">Features</a> •
    <a href="https://github.com/clairebearz32bit/xkcdApp/releases">Download</a> •
    <a href="https://github.com/clairebearz32bit/xkcdApp#setup">Setup</a> •
    <a href="https://github.com/clairebearz32bit/xkcdApp#faq">FAQ</a>
</p>

# XkcdApp
**Desktop client for viewing [xkcd](http://xkcd.com) comics.**

I had previously made a different [client](https://github.com/clairebearz32bit/pyxkcd) for xkcd
using Python, though it was a command line tool. Because of that, I decided to create a better version 
with a UI using Java.

## Features
Below is a list of both present features and features I want to add later:

- [x] Getting the comic by number.
- [x] Using arrow keys to increase comic number.
- [x] Getting the most recent comic.
- [x] Create shortcuts and useful menu bar.

## Systems
Currently, the only system with an executable is Windows, but I intend to create
applications with UNIX and macOS support. It uses JDK 15.0.1 currently.

## Setup
I tried my best to make this as portable as possible, once you download the executable,
it's essentially plug-and-play.

* You can use the arrow keys to increment/decrement the comic number.
* Press enter to show the comic.
* If you know the comic number, you can enter it.
* You can also enter 0 to view the latest comic.

## FAQ
**Question**: Why did you make this?

**Answer**: I love xkcd and programming and thought this would be a fun project (it was).

---
**Question**: How did you make this?

**Answer**: For the UI, I used JavaFX and for JSON I used [this](https://github.com/stleary/JSON-java) library.
Other than that, all the libraries were standard. I also drank a lot of coffee.
