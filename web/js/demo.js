// 'use strict';
// var fs = require("fs");
// // TODO 这个路径?
// fs.readFile("sample.txt","utf-8",function (err,data) {
//     if (err) {
//         console.log(err);
//     }else {
//         console.log(data);
//     }
// });

'use strict';
var fs = require('fs'),
    url = require('url'),
    path = require('path'),
    http=require('http');
var root = path.resolve('/home/ximingren/Projects/ownProjects/java/SSH-shiro/web/js');
console.log("Static root dir: " + root);
var server = http.createServer(function (request, response) {
    var pathname = url.parse(request.url).pathname;
    var filepath = path.join(root, pathname);
    fs.stat(filepath, function (err, stats) {
        if (!err && stats.isFile()) {
            console.log("200 " + request.url);
            response.writeHead(200);
            fs.createReadStream(filepath).pipe(response);
        } else {
            console.log('404 ' + request.url);
            response.writeHead(404);
            response.end('404 Not Found');
        }
    });
});
server.listen(8082);