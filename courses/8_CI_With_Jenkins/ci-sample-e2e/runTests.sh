#!/usr/bin/env bash
# Run E2E tests

#run  jasmine tests
./node_modules/.bin/wdio wdio.conf.js

#stop selenium server
ps -ef | grep node_modules/selenium-standalone | awk '{print $2}' | xargs kill -9

#stop bootRun server
ps -ef | grep 8_CI_With_Jenkins/ci-sample | awk '{print $2}' | xargs kill -9
