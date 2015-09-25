#!/usr/bin/env bash
# Run E2E tests

#install packages
npm install

#clear log
rm -rf ss.log

#start selenium server
./node_modules/.bin/selenium-standalone start > ss.log &
# sleep 5s