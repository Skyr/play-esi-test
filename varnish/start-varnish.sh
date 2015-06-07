#!/bin/bash

WORKPATH="`dirname $0`/work"
/usr/sbin/varnishd -F -a localhost:8000 -n "`realpath $WORKPATH`" -f varnish.cfg
