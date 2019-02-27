echo yes > ssh -tt -i $1 $2
chmod 700 ./init.sh
sudo sh ./init.sh
exit
