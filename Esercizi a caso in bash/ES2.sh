#!/usr/bin/env bash

read -p "Inserire il proprio username: " x

if [ $x = $LOGNAME ]; then
	echo benvenuto $LOGNAME!
else
	echo bugiardo!
fi
