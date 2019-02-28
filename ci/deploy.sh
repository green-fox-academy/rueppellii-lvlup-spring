echo Starting Deploy Script
echo yes > ssh -tt -i $1 $2 << EOF
wall Mr Jenskins is here!
sudo sh ./init.sh
EOF
