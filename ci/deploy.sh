echo yes > ssh -tt -i $1 $2 << EOF
sudo sh ./init.sh
EOF
exit
