const express = require("express");
const bodyParser = require('body-parser');
var cookieParser = require('cookie-parser');
const passport = require("passport");
var session = require('express-session');
var mongoose = require("mongoose");
var app = express();

mongoose.connect('mongodb://assafbz:jernag@ds057176.mlab.com:57176/loaner');

app.use(express.static('public'));
app.use(cookieParser());
app.use(bodyParser);
app.use(session({ secret: 'bettybettybam', resave: true }));
app.use(passport.initialize());
app.use(passport.session());

var User = require("./models/user");
var assaf = new User({ name: 'Zildjian' });
assaf.save(function(err) {
   console.log(err);
});

app.get('/', function (req, res) {
   res.send('Hello World');
})

var server = app.listen(process.env.PORT, function () {
   var host = server.address().address
   var port = server.address().port
   
   console.log("Example app listening at http://%s:%s", process.env.IP, process.env.PORT);
})