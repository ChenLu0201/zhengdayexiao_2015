#!/usr/bin/env bash
# Run E2E tests

#install packages
npm install

#clear log
rm -rf ss.log

#start selenium server
./node_modules/.bin/selenium-standalone start > ss.log &
sleep 3s

#run  jasmine tests
./node_modules/.bin/wdio wdio.conf.js

#stop selenium server
ps -ef | grep node_modules/selenium-standalone | awk '{print $2}' | xargs kill -9
