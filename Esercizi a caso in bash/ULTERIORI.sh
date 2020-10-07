#!/usr/bin/env bash

zap() {
	for x in $@; do
		pkill $x
	done
}
archieve() {
	if [ ! -d backup ]; then
		mkdir backup
	fi
	for x in $@; do
		tar -cvf backup/$x.tar $x
	done
}
watchfor() {
	for x in $@; do
		if  who | grep -q $x ; then
			who | grep $x;
		else
			last $x
		fi
	done
}

printf "Scrivere \"help\" per una lista dei comandi, \"exit\" per uscire
> "
read -a s
while [ ! $s = exit ]; do
	case $s in
	"help")
		echo comandi: $'\n'   zap: termina forzatamente uno o più processi$'\n'   help: lista dei comandi$'\n'	archieve: crea una cartella backup e vi salva i files in .tar$'\n'	watchfor: fornisce le informazioni di login su uno o più utenti
		;;
	"zap")
		zap ${s[@]:1}
		;;
	"archieve")
		archieve ${s[@]:1}
		;;
	"watchfor")
		watchfor ${s[@]:1}
		;;
	*)
		printf "Scrivere \"help\" per una lista dei comandi, \"exit\" per uscire"
		;;
	esac

	printf "> "
	read -a s
done

exit 0
