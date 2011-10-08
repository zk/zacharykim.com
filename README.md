# zkimcom

My personal site, which can be found at
[http://zacharykim.com](http://zacharykim.com).

## Stack

Clojure, ring, moustache, hiccup, etc.  Deployed on Heroku.


## Usage

### Development

    git clone https://zkim@github.com/zkim/zacharykim.com.git
    cd ./zacharykim.com
    lein deps
    lein run -m zkimcom.heroku.web

### Production

    # http://devcenter.heroku.com/articles/quickstart
    
    heroku create --stack cedar
    git push heroku master


## Work Profile

Watch your threads, children!

20-odd threads synchronously serving threads (`zkimcom.heroku.web`),
plus 1 thread polling twitter and google reader for updates (`zkimcom.feeds`).


## License

Copyright (C) 2010 Zachary Kim

Distributed under the Eclipse Public License, the same as Clojure.
